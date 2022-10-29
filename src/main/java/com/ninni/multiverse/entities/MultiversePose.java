package com.ninni.multiverse.entities;

import net.minecraft.world.entity.Pose;

public enum MultiversePose {
    RUN,
    MINING_FORWARDS,
    MINING_UPWARDS,
    MINING_DOWNWARDS,
    DRINKING,
    HIDDEN;

    public Pose get() {
        return Pose.valueOf(this.name());
    }
}
