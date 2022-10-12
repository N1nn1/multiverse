package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.CobblestoneGolem;
import com.ninni.multiverse.entities.MultiversePoses;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class FollowLikedPlayerGoal extends Goal {
    private final CobblestoneGolem cobblestoneGolemEntity;

    public FollowLikedPlayerGoal(CobblestoneGolem cobblestoneGolemEntity) {
        this.cobblestoneGolemEntity = cobblestoneGolemEntity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return this.cobblestoneGolemEntity.getMiningBlock().isPresent() && this.getLikedPlayer().isPresent() && this.cobblestoneGolemEntity.getMinePos() == null && this.cobblestoneGolemEntity.distanceTo(this.getLikedPlayer().get()) > 10;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.getLikedPlayer().isPresent()) {
            if (this.cobblestoneGolemEntity.distanceTo(this.getLikedPlayer().get()) > 2) {
                return true;
            }
        }
        return super.canContinueToUse();
    }

    @Override
    public void tick() {
        this.cobblestoneGolemEntity.getOptionalUUID().map(this.cobblestoneGolemEntity.level::getPlayerByUUID).ifPresent(this::followPlayer);
    }

    @Override
    public void stop() {
        this.cobblestoneGolemEntity.setPose(Pose.STANDING);
    }

    private void followPlayer(Player player) {
        this.cobblestoneGolemEntity.setPose(MultiversePoses.RUN.get());
        this.cobblestoneGolemEntity.getNavigation().moveTo(player, 1.8D);
        this.cobblestoneGolemEntity.getLookControl().setLookAt(player);
    }

    public Optional<ServerPlayer> getLikedPlayer() {
        Level level = this.cobblestoneGolemEntity.getLevel();
        if (!level.isClientSide() && level instanceof ServerLevel) {
            Optional<UUID> optional = this.cobblestoneGolemEntity.getOptionalUUID();
            if (optional.isPresent()) {
                Player player = this.cobblestoneGolemEntity.level.getPlayerByUUID(optional.get());
                if (player instanceof ServerPlayer serverPlayer && (serverPlayer.gameMode.isSurvival() || serverPlayer.gameMode.isCreative()) && serverPlayer.closerThan(this.cobblestoneGolemEntity, 16)) {
                    return Optional.of(serverPlayer);
                }
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
