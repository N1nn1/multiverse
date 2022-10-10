package com.ninni.multiverse.item;

import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.entities.ExhaustedCobblestoneGolemEntity;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import com.ninni.multiverse.sound.MultiverseSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Rotations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ExhaustedCobblestoneGolemItem extends Item {

    public ExhaustedCobblestoneGolemItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Direction direction = useOnContext.getClickedFace();
        if (direction == Direction.DOWN) {
            return InteractionResult.FAIL;
        }
        Level level = useOnContext.getLevel();
        BlockPlaceContext blockPlaceContext = new BlockPlaceContext(useOnContext);
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        ItemStack itemStack = useOnContext.getItemInHand();
        Vec3 vec3 = Vec3.atBottomCenterOf(blockPos);
        AABB aabb = MultiverseEntityTypes.EXHAUSTED_COBBLESTONE_GOLEM.getDimensions().makeBoundingBox(vec3.x(), vec3.y(), vec3.z());
        if (!level.noCollision(null, aabb) || !level.getEntities(null, aabb).isEmpty()) {
            return InteractionResult.FAIL;
        }
        if (level instanceof ServerLevel serverLevel) {
            ExhaustedCobblestoneGolemEntity golem = MultiverseEntityTypes.EXHAUSTED_COBBLESTONE_GOLEM.create(serverLevel, itemStack.getTag(), null, useOnContext.getPlayer(), blockPos, MobSpawnType.SPAWN_EGG, true, true);
            if (golem == null) {
                return InteractionResult.FAIL;
            }
            float f = (float) Mth.floor((Mth.wrapDegrees(useOnContext.getRotation() - 180.0f) + 22.5f) / 45.0f) * 45.0f;
            golem.moveTo(golem.getX(), golem.getY(), golem.getZ(), f, 0.0f);
            this.randomizePose(golem, level.random);
            if (itemStack.getTag() != null) {
                golem.setCrackiness(Crackiness.BY_ID[itemStack.getTag().getInt("crackiness")]);
            }
            if (itemStack.hasCustomHoverName()) {
                golem.setCustomName(itemStack.getHoverName());
            }
            serverLevel.addFreshEntityWithPassengers(golem);
            level.playSound(null, golem.getX(), golem.getY(), golem.getZ(), MultiverseSoundEvents.BLOCK_STONE_TILES_FALL, SoundSource.BLOCKS, 0.75f, 0.8f);
            golem.gameEvent(GameEvent.ENTITY_PLACE, useOnContext.getPlayer());
        }
        itemStack.shrink(1);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        Optional.ofNullable(itemStack.getTag()).filter(this::hasCrackiness).ifPresent(tag -> list.add(Component.translatable("item.multiverse.exhausted_cobblestone_golem." + String.valueOf(Crackiness.BY_ID[tag.getInt("crackiness")]).toLowerCase(Locale.ROOT)).withStyle(ChatFormatting.GRAY)));
    }

    private boolean hasCrackiness(CompoundTag tag) {
        return tag.getInt("crackiness") > 0;
    }

    private void randomizePose(ExhaustedCobblestoneGolemEntity golem, RandomSource randomSource) {
        Rotations rotations = golem.getHeadPose();
        float f = randomSource.nextFloat() * 5.0f;
        float g = randomSource.nextFloat() * 20.0f - 10.0f;
        Rotations rotations2 = new Rotations(rotations.getX() + f, rotations.getY() + g, rotations.getZ());
        golem.setHeadPose(rotations2);
        rotations = golem.getBodyPose();
        f = randomSource.nextFloat() * 10.0f - 5.0f;
        rotations2 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
        golem.setBodyPose(rotations2);
    }

}
