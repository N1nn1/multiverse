package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.Gorb;
import com.ninni.multiverse.entities.MultiversePose;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;

public class DigGoal extends Goal {
    private final Gorb gorb;

    public DigGoal(Gorb gorb) {
        this.gorb = gorb;
    }

    @Override
    public boolean canUse() {
        return this.gorb.getRandom().nextInt(25) == 0 && this.gorb.getPose() != MultiversePose.HIDDEN.get() && this.gorb.getPose() == Pose.STANDING && this.gorb.isOnGround();
    }

    @Override
    public void start() {
        this.gorb.setPose(Pose.DIGGING);
        this.gorb.playSound(SoundEvents.WARDEN_DIG, 5.0f, 1.0f);
    }

    @Override
    public void stop() {
        this.gorb.setPose(MultiversePose.HIDDEN.get());
    }
}
