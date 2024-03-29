package com.ninni.multiverse.entities.ai;

import com.google.common.collect.Lists;
import com.ninni.multiverse.entities.MultiversePose;
import com.ninni.multiverse.entities.RainbowSheep;
import com.ninni.multiverse.init.MultiverseSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class DrinkWaterGoal extends Goal {
    private final RainbowSheep rainbowSheep;
    private BlockPos waterPos;
    private int drinkingTicks = 60;
    private final Direction[] DIRECTIONS = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    public DrinkWaterGoal(RainbowSheep rainbowSheep) {
        this.rainbowSheep = rainbowSheep;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        this.waterPos = this.findWaterPos();
        if (this.waterPos != null) {
            return this.rainbowSheep.getDrinkingCooldown() == 0 && !this.rainbowSheep.isHydrated() && this.waterPos != null;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.drinkingTicks == 0) {
            return false;
        }
        return super.canContinueToUse();
    }

    @Override
    public void tick() {
        if (this.waterPos != null) {
            double distance = this.rainbowSheep.distanceToSqr(Vec3.atCenterOf(this.waterPos));
            if (distance > 1.0D) {
                this.rainbowSheep.getNavigation().moveTo(this.waterPos.getX(), this.waterPos.getY(), this.waterPos.getZ(), 1.0D);
            } else {
                if (this.drinkingTicks > 0) {
                    this.drinkingTicks--;
                }
                if (this.drinkingTicks % 5 == 0) {
                    this.rainbowSheep.playSound(MultiverseSoundEvents.ENTITY_RAINBOW_SHEEP_DRINK, 1.5F, this.rainbowSheep.getRandom().nextFloat() / 2 + 0.5f);
                }
                if (this.drinkingTicks == 59) {
                    this.rainbowSheep.level().broadcastEntityEvent(this.rainbowSheep, (byte) 10);
                }
                if (this.drinkingTicks > 5 && this.drinkingTicks < 54 && this.drinkingTicks % 10 == 0) {
                    this.rainbowSheep.level().broadcastEntityEvent(this.rainbowSheep, (byte) 11);
                }
                this.rainbowSheep.getNavigation().stop();
                this.rainbowSheep.setPose(MultiversePose.DRINKING.get());
            }
        }
    }

    @Override
    public void stop() {
        if (!this.rainbowSheep.isHydrated()) {
            this.rainbowSheep.setHydrated(true);
        }
        this.drinkingTicks = 60;
        this.rainbowSheep.setDrinkingCooldown(UniformInt.of(200, 1200).sample(this.rainbowSheep.getRandom()));
    }

    public BlockPos findWaterPos() {
        BlockPos blockPos = this.rainbowSheep.blockPosition();
        List<BlockPos> poses = Lists.newArrayList();
        int range = 8;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                    if (!(this.isConnected(pos) && this.rainbowSheep.level().getBlockState(pos).is(Blocks.WATER) && this.rainbowSheep.level().getBlockState(pos.above()).isAir())) continue;
                    poses.add(pos);
                }
            }
        }
        if (!poses.isEmpty()) {
            poses.sort(Comparator.comparingDouble(blockPos::distSqr));
            for (BlockPos pos : poses) {
                return pos;
            }
        }
        return null;
    }

    public boolean isConnected(BlockPos blockPos) {
        for (Direction direction : DIRECTIONS) {
            BlockPos relative = blockPos.relative(direction);
            if (this.rainbowSheep.level().getFluidState(relative).isEmpty() && this.rainbowSheep.level().getFluidState(relative.above()).isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
