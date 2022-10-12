package com.ninni.multiverse.entities.ai;

import com.google.common.collect.Lists;
import com.ninni.multiverse.entities.CobblestoneGolem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FindTargettedBlockGoal extends Goal {
    private final CobblestoneGolem golemEntity;
    private BlockPos targetPos;

    public FindTargettedBlockGoal(CobblestoneGolem golemEntity) {
        this.golemEntity = golemEntity;
    }

    @Override
    public boolean canUse() {
        if (this.golemEntity.isPathFinding()) return false;
        this.targetPos = this.getMiningPos();
        return this.golemEntity.getMiningCooldown() == 0 && this.golemEntity.getMinePos() == null && this.targetPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.targetPos != null) {
            Vec3 vec3 = Vec3.atCenterOf(this.targetPos);
            this.golemEntity.getNavigation().moveTo(vec3.x(), vec3.y(), vec3.z(), 1.2);
            this.golemEntity.getLookControl().setLookAt(vec3);
            return !this.golemEntity.blockPosition().closerToCenterThan(vec3, 2) && super.canContinueToUse();
        } else {
            return false;
        }
    }

    @Override
    public void start() {
        Vec3 vec3 = Vec3.atCenterOf(this.targetPos);
        this.golemEntity.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, 1.4D);
        this.golemEntity.getLookControl().setLookAt(vec3);
        if (this.targetPos != null && this.golemEntity.blockPosition().closerThan(this.targetPos, 2)) {
            this.golemEntity.setMinePos(this.targetPos);
        }
    }

    @Override
    public void tick() {
        if (this.targetPos != null) {
            Vec3 vec3 = Vec3.atCenterOf(this.targetPos);
            this.golemEntity.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, 1.4D);
            this.golemEntity.getLookControl().setLookAt(vec3);
        }
    }

    @Override
    public void stop() {
        this.golemEntity.setMinePos(this.targetPos);
        this.golemEntity.setJumping(false);
    }

    public BlockPos getMiningPos() {
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> priorityList = Lists.newArrayList();
        List<BlockPos> unpreferredPositions = Lists.newArrayList();
        BlockPos mobPos = this.golemEntity.blockPosition();
        int range = 4;
        int yLevel = 1;
        for (BlockPos blockPos : BlockPos.betweenClosedStream(mobPos.offset(-range, -yLevel, -range), mobPos.offset(range, yLevel, range)).map(BlockPos::immutable).toList()) {
            if (this.golemEntity.getMiningBlock().isPresent() && this.golemEntity.level.getBlockState(blockPos).is(this.golemEntity.getMiningBlock().get().getBlock()) && this.isAirOrWater(blockPos)) {
                list.add(blockPos);
            }
        }

        if (!list.isEmpty()) {
            list.sort(new DistanceComparator(this.golemEntity.blockPosition()));
            for (BlockPos blockPos : list) {
                Vec3 vec3 = new Vec3(this.golemEntity.getX(), this.golemEntity.getEyeY(), this.golemEntity.getZ());
                Vec3 vec31 = Vec3.atCenterOf(blockPos);
                BlockHitResult blockHitResult = this.golemEntity.level.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.golemEntity));
                if (!blockHitResult.getBlockPos().equals(blockPos)) continue;
                if (this.golemEntity.getY() > blockHitResult.getBlockPos().getY()) {
                    unpreferredPositions.add(blockPos);
                } else {
                    priorityList.add(blockPos);
                }
            }
        }

        if (!priorityList.isEmpty()) {
            for (BlockPos blockPos : priorityList) {
                return blockPos;
            }
        } else {
            for (BlockPos blockPos : unpreferredPositions) {
                return blockPos;
            }
        }

        return null;
    }

    public boolean isAirOrWater(BlockPos blockPos) {
        for (Direction direction : Direction.values()) {
            if (this.golemEntity.level.isStateAtPosition(blockPos.relative(direction), DripstoneUtils::isEmptyOrWater)) {
                return true;
            }
        }
        return false;
    }


}
