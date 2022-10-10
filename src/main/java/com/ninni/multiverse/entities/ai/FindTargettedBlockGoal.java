package com.ninni.multiverse.entities.ai;

import com.google.common.collect.Lists;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FindTargettedBlockGoal extends Goal {
    private final CobblestoneGolemEntity golemEntity;
    private BlockPos targetPos;

    public FindTargettedBlockGoal(CobblestoneGolemEntity golemEntity) {
        this.golemEntity = golemEntity;
    }

    @Override
    public boolean canUse() {
        this.targetPos = this.getMiningPos();
        return this.golemEntity.getMinePos() == null && this.targetPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        return !this.golemEntity.getNavigation().isDone() && !this.targetPos.closerToCenterThan(this.golemEntity.position(), 1.2D) && super.canContinueToUse();
    }

    @Override
    public void start() {
        Vec3 vec3 = Vec3.atCenterOf(this.targetPos);
        this.golemEntity.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, 1.4D);
    }

    @Override
    public void tick() {
    }

    @Override
    public void stop() {
        this.golemEntity.setMinePos(this.targetPos);
        this.golemEntity.setJumping(false);
    }

    public BlockPos getMiningPos() {
        List<BlockPos> list = Lists.newArrayList();
        int range = 4;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -1; y <= 1; y++) {
                    BlockPos pos = new BlockPos(this.golemEntity.getX() + x, this.golemEntity.getY() + y, this.golemEntity.getZ() + z);
                    BlockState blockState = this.golemEntity.level.getBlockState(pos);
                    boolean flag = this.golemEntity.getMiningBlock() != null && blockState.getBlock() == this.golemEntity.getMiningBlock().getBlock();
                    if (!flag) continue;
                    list.add(pos);
                }
            }
        }
        if (list.isEmpty()) return null;

        return list.stream().findFirst().get();
    }


}
