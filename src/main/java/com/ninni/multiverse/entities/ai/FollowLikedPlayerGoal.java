package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.CobblestoneGolem;
import com.ninni.multiverse.entities.MultiversePose;
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
    private final CobblestoneGolem golem;

    public FollowLikedPlayerGoal(CobblestoneGolem golem) {
        this.golem = golem;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return this.golem.getMiningBlock().isPresent() && this.getLikedPlayer().isPresent() && this.golem.getMinePos() == null && this.golem.distanceTo(this.getLikedPlayer().get()) > 10;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.getLikedPlayer().isPresent()) {
            if (this.golem.distanceTo(this.getLikedPlayer().get()) > 2) {
                return true;
            }
        }
        return super.canContinueToUse();
    }

    @Override
    public void tick() {
        this.golem.getOptionalUUID().map(this.golem.level()::getPlayerByUUID).ifPresent(this::followPlayer);
    }

    @Override
    public void stop() {
        this.golem.setPose(Pose.STANDING);
    }

    private void followPlayer(Player player) {
        this.golem.setPose(MultiversePose.RUN.get());
        this.golem.getNavigation().moveTo(player, 1.8D);
        this.golem.getLookControl().setLookAt(player);
    }

    public Optional<ServerPlayer> getLikedPlayer() {
        Level level = this.golem.level();
        if (!level.isClientSide() && level instanceof ServerLevel) {
            Optional<UUID> optional = this.golem.getOptionalUUID();
            if (optional.isPresent()) {
                Player player = this.golem.level().getPlayerByUUID(optional.get());
                if (player instanceof ServerPlayer serverPlayer && (serverPlayer.gameMode.isSurvival() || serverPlayer.gameMode.isCreative()) && serverPlayer.closerThan(this.golem, 16)) {
                    return Optional.of(serverPlayer);
                }
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
