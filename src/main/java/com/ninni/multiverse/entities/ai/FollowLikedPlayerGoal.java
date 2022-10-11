package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class FollowLikedPlayerGoal extends Goal {
    private final CobblestoneGolemEntity cobblestoneGolemEntity;

    public FollowLikedPlayerGoal(CobblestoneGolemEntity cobblestoneGolemEntity) {
        this.cobblestoneGolemEntity = cobblestoneGolemEntity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return this.cobblestoneGolemEntity.getMiningBlock().isPresent() && this.getLikedPlayer().isPresent() && this.cobblestoneGolemEntity.getMinePos() == null;
    }

    @Override
    public void tick() {
        this.cobblestoneGolemEntity.getOptionalUUID().map(this.cobblestoneGolemEntity.level::getPlayerByUUID).ifPresent(this::followPlayer);
    }

    private void followPlayer(Player player) {
        this.cobblestoneGolemEntity.getNavigation().moveTo(player, 1.4D);
        this.cobblestoneGolemEntity.getLookControl().setLookAt(player);
    }

    public Optional<ServerPlayer> getLikedPlayer() {
        Level level = this.cobblestoneGolemEntity.getLevel();
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
            Optional<UUID> optional = this.cobblestoneGolemEntity.getOptionalUUID();
            if (optional.isPresent()) {
                Entity entity = serverLevel.getEntity(optional.get());
                if (entity instanceof ServerPlayer serverPlayer) {
                    if ((serverPlayer.gameMode.isSurvival() || serverPlayer.gameMode.isCreative()) && serverPlayer.closerThan(this.cobblestoneGolemEntity, 64.0)) {
                        return Optional.of(serverPlayer);
                    }
                }
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
