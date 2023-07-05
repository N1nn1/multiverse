package com.ninni.multiverse.mixin;

import com.ninni.multiverse.entities.CobblestoneGolem;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {
    @Nullable
    private BlockPattern cobblestoneGolemFull;
    @Nullable
    private BlockPattern cobblestoneGolemBase;
    private static final Predicate<BlockState> PUMPKINS_PREDICATE = blockState -> blockState != null && (blockState.is(Blocks.CARVED_PUMPKIN) || blockState.is(Blocks.JACK_O_LANTERN));

    @Inject(at = @At("HEAD"), method = "canSpawnGolem", cancellable = true)
    private void M$canSpawnGolem(LevelReader levelReader, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (this.getOrCobblestoneGolemBase().find(levelReader, blockPos) != null) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "trySpawnGolem", cancellable = true)
    private void M$trySpawnGolem(Level world, BlockPos pos, CallbackInfo ci) {
        BlockPattern.BlockPatternMatch pattern = this.getOrCreateCobblestoneGolemFull().find(world, pos);
        ServerPlayer player;
        if (pattern != null) {
            for (int i = 0; i < this.getOrCreateCobblestoneGolemFull().getWidth(); ++i) {
            for (int k = 0; k < this.getOrCreateCobblestoneGolemFull().getHeight(); ++k) {
                BlockInWorld blockInWorld = pattern.getBlock(i, k, 0);
                world.setBlock(blockInWorld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(2001, blockInWorld.getPos(), Block.getId(blockInWorld.getState()));
            }
        }
            CobblestoneGolem golem = MultiverseEntityTypes.COBBLESTONE_GOLEM.create(world);
            BlockPos cachedBlockPosition = pattern.getBlock(1, 2, 0).getPos();
            assert golem != null;
            golem.moveTo((double) cachedBlockPosition.getX() + 0.5D, (double) cachedBlockPosition.getY() + 1D, (double) cachedBlockPosition.getZ() + 0.5D, 0.0F, 0.0F);
            world.addFreshEntity(golem);

            for (ServerPlayer serverPlayer : world.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0D))) {
                player = serverPlayer;
                CriteriaTriggers.SUMMONED_ENTITY.trigger(player, golem);
            }

            for (int j = 0; j < this.getOrCreateCobblestoneGolemFull().getWidth(); j++) {
                for (int l = 0; l < this.getOrCreateCobblestoneGolemFull().getHeight(); ++l) {
                    BlockInWorld position = pattern.getBlock(j, l, 0);
                    world.blockUpdated(position.getPos(), Blocks.AIR);
                }
            }
            ci.cancel();
        }
    }

    private BlockPattern getOrCobblestoneGolemBase() {
        if (this.cobblestoneGolemBase == null) {
            this.cobblestoneGolemBase = BlockPatternBuilder.start().aisle("~ ~", "C#C").where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COBBLESTONE))).where('C', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COBBLESTONE_WALL))).where('~', blockInWorld -> blockInWorld.getState().isAir()).build();
        }
        return this.cobblestoneGolemBase;
    }

    private BlockPattern getOrCreateCobblestoneGolemFull() {
        if (this.cobblestoneGolemFull == null) {
            this.cobblestoneGolemFull = BlockPatternBuilder.start().aisle("~^~", "C#C").where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COBBLESTONE))).where('C', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COBBLESTONE_WALL))).where('~', blockInWorld -> blockInWorld.getState().isAir()).build();
        }
        return this.cobblestoneGolemFull;
    }

}
