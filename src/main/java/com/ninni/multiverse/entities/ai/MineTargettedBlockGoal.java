package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class MineTargettedBlockGoal extends Goal {
    private final CobblestoneGolemEntity cobblestoneGolemEntity;
    private BlockPos pos;

    public MineTargettedBlockGoal(CobblestoneGolemEntity cobblestoneGolemEntity) {
        this.cobblestoneGolemEntity = cobblestoneGolemEntity;
    }

    @Override
    public boolean canUse() {
        this.pos = this.cobblestoneGolemEntity.getMinePos();
        return this.pos != null && !this.cobblestoneGolemEntity.level.getBlockState(this.pos).isAir();
    }

    @Override
    public void tick() {
        Vec3 vec3 = Vec3.atCenterOf(this.pos);
        if (this.pos.closerToCenterThan(this.cobblestoneGolemEntity.position(), 2)) {
            this.cobblestoneGolemEntity.level.destroyBlock(this.pos, true);
        } else {
            if (this.pos.getY() > this.cobblestoneGolemEntity.getY()) {
                this.cobblestoneGolemEntity.setJumping(true);
            }
            this.cobblestoneGolemEntity.getNavigation().moveTo(vec3.x(), vec3.y(), vec3.z(), 1.4D);
        }
    }

    @Override
    public void stop() {
        this.cobblestoneGolemEntity.setMinePos(null);
    }

    public BlockState getState() {
        BlockPos pos = this.cobblestoneGolemEntity.getMinePos();
        return this.cobblestoneGolemEntity.level.getBlockState(pos);
    }


}
