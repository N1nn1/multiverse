package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class MineTargettedBlockGoal extends Goal {
    private final CobblestoneGolemEntity golem;
    private int miningTicks;
    private BlockPos pos;

    public MineTargettedBlockGoal(CobblestoneGolemEntity golem) {
        this.golem = golem;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        this.pos = this.golem.getMinePos();
        return this.pos != null && !this.golem.level.getBlockState(this.pos).isAir();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.miningTicks < 0) {
            return false;
        }
        if (this.golem.getMiningBlock().isPresent() && this.golem.level.getBlockState(this.pos).getBlock() != this.golem.getMiningBlock().get().getBlock()) {
            return false;
        }
        return super.canContinueToUse();
    }

    @Override
    public void start() {
        if (this.pos != null) {
            float speed = this.golem.level.getBlockState(this.pos).getDestroySpeed(this.golem.level, this.pos);
            this.miningTicks = (int) speed * 10;
            System.out.println(this.miningTicks);
        }
    }

    @Override
    public void tick() {
        Vec3 vec3 = Vec3.atCenterOf(this.pos);
        Crackiness crackiness = this.golem.getCrackiness();
        if (this.miningTicks >= 0) {
            this.miningTicks--;
        }
        if (this.miningTicks > 2) {
            int var3 = (30 / (this.miningTicks - 2));
            int var4 = Math.min(var3, 9);
            this.golem.level.destroyBlockProgress(this.golem.getId(), this.pos, var4);
        }
        if (this.pos.closerToCenterThan(this.golem.position(), 2) && this.miningTicks == 0) {
            this.golem.level.destroyBlock(this.pos, true);
            this.golem.level.destroyBlockProgress(this.golem.getId(), this.pos, -1);
            if (crackiness.getId() == 3) {
                if (golem.getRandom().nextInt(8) == 0)
                    this.golem.becomeExhausted(MultiverseEntityTypes.EXHAUSTED_COBBLESTONE_GOLEM);
            } else {
                if (golem.getRandom().nextInt(8) == 0) this.golem.setCrackiness(crackiness.getId() + 1);
            }
        } else {
            if (this.pos.getY() > this.golem.getY()) {
                this.golem.setJumping(true);
            }
            this.golem.getNavigation().moveTo(vec3.x(), vec3.y(), vec3.z(), 1.4D);
            this.golem.getLookControl().setLookAt(vec3);
        }
    }

    @Override
    public void stop() {
        this.golem.setMinePos(null);
    }

}
