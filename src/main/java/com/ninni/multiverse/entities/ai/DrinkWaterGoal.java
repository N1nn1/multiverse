package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.RainbowSheep;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class DrinkWaterGoal extends Goal {
    private final RainbowSheep rainbowSheep;
    private BlockPos pos;

    public DrinkWaterGoal(RainbowSheep rainbowSheep) {
        this.rainbowSheep = rainbowSheep;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return false;
    }

    public BlockPos findWater() {
        return null;
    }

}
