package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.RainbowSheep;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;

import java.util.EnumSet;

public class DrinkWaterGoal extends Goal {
    private final RainbowSheep rainbowSheep;
    private BlockPos waterPos;
    private int range = 8;
    private int drinkingTicks;

    public DrinkWaterGoal(RainbowSheep rainbowSheep) {
        this.rainbowSheep = rainbowSheep;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.waterPos = this.findWaterPos();
        return !this.rainbowSheep.isHydrated() && this.waterPos != null;
    }

    @Override
    public void tick() {
        if (this.waterPos != null) {
            if (this.rainbowSheep.blockPosition().closerThan(this.waterPos, 2)){
                this.drinkingTicks++;
            }
            this.rainbowSheep.getNavigation().moveTo(this.waterPos.getX(), this.waterPos.getY(), this.waterPos.getZ(), 1.0F);
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (this.rainbowSheep.level.getFluidState(this.waterPos.relative(direction)).is(Fluids.WATER)) {
                    this.rainbowSheep.getLookControl().setLookAt(Vec3.atCenterOf(this.waterPos.relative(direction)));
                }
            }
            if (this.drinkingTicks > 200) {
                this.rainbowSheep.playSound(SoundEvents.GENERIC_DRINK);
                this.rainbowSheep.setHydrated(true);
            }
        }
    }

    public BlockPos findWaterPos() {
        CollisionContext collisionContext = CollisionContext.of(this.rainbowSheep);
        BlockPos blockPos = this.rainbowSheep.blockPosition();
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (BlockPos blockPos2 : BlockPos.withinManhattan(blockPos, this.range, this.range, this.range)) {
            if (blockPos2.getX() == blockPos.getX() && blockPos2.getZ() == blockPos.getZ() || !this.rainbowSheep.level.getBlockState(blockPos2).getCollisionShape(this.rainbowSheep.level, blockPos2, collisionContext).isEmpty() || this.rainbowSheep.level.getBlockState(mutableBlockPos.setWithOffset((Vec3i)blockPos2, Direction.DOWN)).getCollisionShape(this.rainbowSheep.level, blockPos2, collisionContext).isEmpty()) continue;
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                mutableBlockPos.setWithOffset(blockPos2, direction);
                if (!this.rainbowSheep.level.getBlockState(mutableBlockPos).isAir() || !this.rainbowSheep.level.getBlockState(mutableBlockPos.move(Direction.DOWN)).is(Blocks.WATER)) continue;
                return blockPos2;
            }
        }
        return null;
    }

}
