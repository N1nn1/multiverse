package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.MultiversePose;
import com.ninni.multiverse.entities.RainbowSheep;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class RainbowSheepHopAwayGoal extends PanicGoal {
    private final RainbowSheep sheep;

    public RainbowSheepHopAwayGoal(RainbowSheep sheep, double d) {
        super(sheep, d);
        this.sheep = sheep;
    }

    @Override
    public void start() {
        this.sheep.setPose(MultiversePose.HOP.get());
        super.start();
    }

    @Override
    public void stop() {
        this.sheep.setPose(Pose.STANDING);
        super.stop();
    }
}
