package com.ninni.multiverse.entities;

import net.minecraft.world.entity.Pose;

public enum MultiversePose {
    HOP,
    RUN,
    MINING_FORWARDS,
    MINING_UPWARDS,
    MINING_DOWNWARDS;

    public Pose get() {
        return Pose.valueOf(this.name());
    }
}
