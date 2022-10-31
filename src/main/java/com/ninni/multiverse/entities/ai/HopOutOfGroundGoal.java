package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.Gorb;
import com.ninni.multiverse.entities.MultiversePose;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class HopOutOfGroundGoal extends Goal {
    private final Gorb gorb;
    private LivingEntity target;
    private int hopTicks;

    public HopOutOfGroundGoal(Gorb gorb) {
        this.gorb = gorb;
    }

    @Override
    public boolean canUse() {
        if (this.getNearestPlayer().isPresent()) {
            this.target = this.getNearestPlayer().get();
            return this.gorb.getPose() == MultiversePose.HIDDEN.get() && this.target != null;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.hopTicks > 0 && super.canContinueToUse();
    }

    @Override
    public void start() {
        this.hopTicks = 50;
    }

    @Override
    public void tick() {
        if (this.hopTicks > 0) {
            this.hopTicks--;
            this.gorb.getNavigation().stop();
            if (this.hopTicks == 20) {
                this.gorb.setPose(Pose.LONG_JUMPING);
            }
        }
    }

    @Override
    public void stop() {
        this.gorb.setPose(Pose.STANDING);
        this.gorb.setTarget(this.target);
    }

    public Optional<ServerPlayer> getNearestPlayer() {
        return this.gorb.level.getEntitiesOfClass(Player.class, this.gorb.getBoundingBox().inflate(8.0D)).stream().filter(Gorb::hasEnchantments).filter(ServerPlayer.class::isInstance).map(ServerPlayer.class::cast).filter(serverPlayer -> serverPlayer.gameMode.isSurvival()).toList().stream().findAny();
    }

}
