package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class MineTargettedBlockGoal extends Goal {
    private final CobblestoneGolemEntity golem;
    private BlockPos pos;

    public MineTargettedBlockGoal(CobblestoneGolemEntity golem) {
        this.golem = golem;
    }

    @Override
    public boolean canUse() {
        this.pos = this.golem.getMinePos();
        return this.pos != null && !this.golem.level.getBlockState(this.pos).isAir();
    }

    @Override
    public void tick() {
        Vec3 vec3 = Vec3.atCenterOf(this.pos);
        Crackiness crackiness = this.golem.getCrackiness();

        if (this.pos.closerToCenterThan(this.golem.position(), 2)) {
            this.golem.level.destroyBlock(this.pos, true);
            if (crackiness.getId() == 3) {
                if (golem.getRandom().nextInt(8) == 1) this.golem.becomeExhausted(MultiverseEntityTypes.EXHAUSTED_COBBLESTONE_GOLEM);
            } else {
                if (golem.getRandom().nextInt(8) == 1) this.golem.setCrackiness(crackiness.getId() + 1);
            }
        } else {
            if (this.pos.getY() > this.golem.getY()) {
                this.golem.setJumping(true);
            }
            this.golem.getNavigation().moveTo(vec3.x(), vec3.y(), vec3.z(), 1.4D);
        }
    }

    @Override
    public void stop() {
        this.golem.setMinePos(null);
    }

}
