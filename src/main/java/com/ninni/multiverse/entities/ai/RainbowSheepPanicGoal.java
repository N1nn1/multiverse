package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.MultiversePose;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class RainbowSheepPanicGoal extends PanicGoal {

    public RainbowSheepPanicGoal(PathfinderMob pathfinderMob, double d) {
        super(pathfinderMob, d);
    }

    @Override
    public void start() {
        super.start();
        this.mob.setPose(MultiversePose.HOP.get());
    }
}
