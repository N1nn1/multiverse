package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.entities.CobblestoneGolem;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import com.ninni.multiverse.entities.MultiversePose;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class MineTargettedBlockGoal extends Goal {
    private final CobblestoneGolem golem;
    private int miningTicks;
    private BlockPos pos;
    private int idlingTicks;
    private int soundDelayTicks;

    public MineTargettedBlockGoal(CobblestoneGolem golem) {
        this.golem = golem;
        this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        this.pos = this.golem.getMinePos();
        return this.golem.getMiningBlock().isPresent() && this.golem.getMiningCooldown() == 0 && this.pos != null && !this.golem.level().getBlockState(this.pos).isAir();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.miningTicks < 0) {
            return false;
        }
        if (this.idlingTicks > 80) {
            return false;
        }
        if (this.golem.getMiningBlock().isPresent() && this.golem.level().getBlockState(this.pos).getBlock() != this.golem.getMiningBlock().get().getBlock()) {
            return false;
        }
        if (this.golem.getMiningBlock().isPresent() && this.golem.getMinePos() != null && this.golem.level().getBlockState(this.pos).is(this.golem.getMiningBlock().get().getBlock())) {
            this.golem.level().destroyBlockProgress(this.golem.getId(), this.pos, -1);
        }
        return super.canContinueToUse();
    }

    @Override
    public void start() {
        this.soundDelayTicks = 5;
        if (this.pos != null) {
            float speed = this.golem.level().getBlockState(this.pos).getDestroySpeed(this.golem.level(), this.pos);
            float adjustedValue = speed < 1 ? 1 : speed;
            this.miningTicks = (int) adjustedValue * 10;
        }
    }

    @Override
    public void tick() {
        Vec3 vec3 = Vec3.atCenterOf(this.pos);
        Crackiness crackiness = this.golem.getCrackiness();
        if ((this.pos.getY() > this.golem.getY() && this.pos.closerToCenterThan(this.golem.position(), 3)) || this.pos.closerToCenterThan(this.golem.position(), 2)) {
            this.golem.getNavigation().stop();
            this.golem.getLookControl().setLookAt(vec3);
            Pose pose;
            if (this.pos.getY() > this.golem.getY()) {
                pose = MultiversePose.MINING_UPWARDS.get();
            } else if (this.pos.getY() < this.golem.getY()) {
                pose = MultiversePose.MINING_DOWNWARDS.get();
            } else {
                pose = MultiversePose.MINING_FORWARDS.get();
            }
            this.golem.setPose(pose);
            this.miningTicks--;
            if (this.miningTicks >= 1) {
                int var4 = Math.min(30 / this.miningTicks, 9);
                this.golem.level().destroyBlockProgress(this.golem.getId(), this.pos, var4);
                this.soundDelayTicks--;
                if (this.soundDelayTicks == 0) {
                    this.golem.playSound(this.golem.getMiningBlock().get().getSoundType().getHitSound());
                    this.soundDelayTicks = 5;
                }
            }
            if (this.miningTicks == 0) {
                this.golem.level().destroyBlock(this.pos, true);
                this.golem.level().destroyBlockProgress(this.golem.getId(), this.pos, -1);
                float bound = this.golem.getRandom().nextFloat();
                if (this.golem.getMinedCount() > 0 && bound < this.golem.getMinedCount() / 100.0F) {
                    if (crackiness.getId() == 3) {
                        this.golem.becomeExhausted(MultiverseEntityTypes.EXHAUSTED_COBBLESTONE_GOLEM);
                    } else {
                        this.golem.setCrackiness(crackiness.getId() + 1);
                    }
                }
            }
        } else {
            this.golem.setPose(Pose.STANDING);
            BlockHitResult result = this.golem.level().clip(new ClipContext(this.golem.position(), vec3, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.golem));
            BlockPos walkPos = BlockPos.containing(vec3.relative(result.getDirection(), 0.51F));
            vec3 = new Vec3(walkPos.getX(), walkPos.getY(), walkPos.getZ());
            Path path = this.golem.getNavigation().createPath(walkPos, 0);
            if (path != null && path.canReach()) {
                this.golem.getNavigation().moveTo(path, 1.4D);
                this.golem.getLookControl().setLookAt(vec3);
            }
            if (this.golem.getNavigation().isDone()) {
                this.idlingTicks++;
            }
        }
    }

    @Override
    public void stop() {
        this.golem.setPose(Pose.STANDING);
        this.golem.setMinePos(null);
        this.golem.setMinedCount(this.golem.getMinedCount() + 1);
        this.golem.setMiningCooldown(Mth.nextInt(this.golem.getRandom(), 100, 200));
        this.idlingTicks = 0;
    }

}
