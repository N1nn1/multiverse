package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.Gorb;
import com.ninni.multiverse.entities.MultiversePose;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.List;
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
        Optional<ServerPlayer> nearestPlayer = this.getNearestPlayer();
        LivingEntity lastHurtByMob = this.gorb.getLastHurtByMob();
        if (nearestPlayer.isPresent()) {
            this.target = nearestPlayer.get();
        }
        else if (lastHurtByMob != null) {
            this.target = lastHurtByMob;
        }
        return this.gorb.getPose() == MultiversePose.HIDDEN.get() && (this.target != null && (this.target.isAlive() || (this.target instanceof Player player && !player.getAbilities().instabuild)));
    }

    @Override
    public boolean canContinueToUse() {
        return this.hopTicks > 0 && super.canContinueToUse();
    }

    @Override
    public void start() {
        this.hopTicks = this.gorb.getLastDamageSource() != null ? 1 : 5;
    }

    @Override
    public void tick() {
        if (this.hopTicks > 0) {
            if (this.target != null && this.target instanceof Player player && player.getAbilities().instabuild) {
                return;
            }
            this.hopTicks--;
            this.gorb.getNavigation().stop();
            if (this.hopTicks == 1) {
                this.gorb.setPose(Pose.LONG_JUMPING);
            }
        }
    }

    @Override
    public void stop() {
        this.gorb.setPose(Pose.STANDING);
        this.gorb.setTarget(this.target);
        this.target = null;
        this.hopTicks = 0;
    }

    public Optional<ServerPlayer> getNearestPlayer() {
        for (ServerPlayer player : this.gorb.level.getEntitiesOfClass(ServerPlayer.class, this.gorb.getBoundingBox().inflate(8.0D)).stream().filter(serverPlayer -> serverPlayer.gameMode.isSurvival()).toList()) {
            List<ServerPlayer> entitiesOfClass = this.gorb.level.getEntitiesOfClass(ServerPlayer.class, this.gorb.getBoundingBox().inflate(0.5D, 1.0D, 0.5D));
            return !entitiesOfClass.isEmpty() ? entitiesOfClass.stream().findFirst() : (Gorb.hasEnchantments(player) ? Optional.of(player) : Optional.empty());
        }
        return Optional.empty();
    }

}
