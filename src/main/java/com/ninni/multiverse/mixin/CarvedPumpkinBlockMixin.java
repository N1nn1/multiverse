package com.ninni.multiverse.mixin;

import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import com.ninni.multiverse.init.MultiverseEntityTypes;
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
import net.minecraft.world.level.block.state.predicate.BlockMaterialPredicate;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumpkinBlockMixin {
    @Nullable
    private BlockPattern cobblestoneGolemFull;
    private static final Predicate<BlockState> PUMPKINS_PREDICATE = blockState -> blockState != null && (blockState.is(Blocks.CARVED_PUMPKIN) || blockState.is(Blocks.JACK_O_LANTERN));

    @Inject(at = @At("HEAD"), method = "canSpawnGolem", cancellable = true)
    private void M$canSpawnGolem(LevelReader levelReader, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (this.getOrCreateCobblestoneGolemFull().find(levelReader, blockPos) != null) {
            cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("HEAD"), method = "trySpawnGolem", cancellable = true)
    private void GE$trySpawnGolem(Level world, BlockPos pos, CallbackInfo ci) {
        BlockPattern.BlockPatternMatch result = this.getOrCreateCobblestoneGolemFull().find(world, pos);
        int i;
        ServerPlayer serverPlayerEntity;
        int j;
        if (result != null) {
            for (i = 0; i < this.getOrCreateCobblestoneGolemFull().getHeight(); ++i) {
                BlockInWorld cachedBlockPosition = result.getBlock(0, i, 0);
                world.setBlock(cachedBlockPosition.getPos(), Blocks.AIR.defaultBlockState(), 2);
                world.levelEvent(2001, cachedBlockPosition.getPos(), Block.getId(cachedBlockPosition.getState()));
            }

            CobblestoneGolemEntity e = MultiverseEntityTypes.COBBLESTONE_GOLEM.create(world);
            BlockPos cachedBlockPosition = result.getBlock(0, 2, 0).getPos();
            assert e != null;
            e.moveTo((double) cachedBlockPosition.getX() + 0.5D, (double) cachedBlockPosition.getY() + 0.05D, (double) cachedBlockPosition.getZ() + 0.5D, 0.0F, 0.0F);
            world.addFreshEntity(e);

            for (ServerPlayer serverPlayer : world.getEntitiesOfClass(ServerPlayer.class, e.getBoundingBox().inflate(5.0D))) {
                serverPlayerEntity = serverPlayer;
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayerEntity, e);
            }

            for (int width = 0; width < this.getOrCreateCobblestoneGolemFull().getWidth(); width++) {
                for (j = 0; j < this.getOrCreateCobblestoneGolemFull().getHeight(); ++j) {
                    BlockInWorld position = result.getBlock(width, j, 0);
                    world.blockUpdated(position.getPos(), Blocks.AIR);
                }
            }
            ci.cancel();
        }
    }

    private BlockPattern getOrCreateCobblestoneGolemFull() {
        if (this.cobblestoneGolemFull == null) {
            this.cobblestoneGolemFull = BlockPatternBuilder.start().aisle("~^~", "C#C").where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COBBLESTONE))).where('C', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COBBLESTONE_WALL))).where('~', BlockInWorld.hasState(BlockMaterialPredicate.forMaterial(Material.AIR))).build();
        }
        return this.cobblestoneGolemFull;
    }

}
