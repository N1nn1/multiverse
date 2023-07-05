package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.Gorb;
import com.ninni.multiverse.entities.MultiversePose;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class DigGoal extends Goal {
    private final Gorb gorb;

    public DigGoal(Gorb gorb) {
        this.gorb = gorb;
    }

    @Override
    public boolean canUse() {
        return this.gorb.getRandom().nextInt(200) == 0 && this.gorb.getMainHandItem().isEmpty() && this.gorb.getTarget() == null && this.getNearestPlayer().isEmpty() && this.gorb.getPose() != MultiversePose.HIDDEN.get() && this.gorb.getPose() == Pose.STANDING && this.gorb.onGround();
    }

    @Override
    public void start() {
        this.gorb.setPose(Pose.DIGGING);
        this.gorb.playSound(SoundEvents.WARDEN_DIG, 1.0f, 1.5f);
    }

    @Override
    public void stop() {
        this.gorb.setPose(MultiversePose.HIDDEN.get());
    }

    public Optional<ServerPlayer> getNearestPlayer() {
        return this.gorb.level().getEntitiesOfClass(Player.class, this.gorb.getBoundingBox().inflate(8.0D)).stream().filter(Gorb::hasEnchantments).filter(ServerPlayer.class::isInstance).map(ServerPlayer.class::cast).filter(serverPlayer -> serverPlayer.gameMode.isSurvival()).toList().stream().findAny();
    }


}
