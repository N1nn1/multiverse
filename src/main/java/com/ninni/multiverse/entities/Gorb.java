package com.ninni.multiverse.entities;

import com.ninni.multiverse.entities.ai.DigGoal;
import com.ninni.multiverse.entities.ai.FindNearestItemGoal;
import com.ninni.multiverse.entities.ai.HopOutOfGroundGoal;
import com.ninni.multiverse.entities.ai.MergeBookGoal;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Gorb extends PathfinderMob {
    public final AnimationState digAnimationState = new AnimationState();
    public final AnimationState hopAnimationState = new AnimationState();
    private static final EntityDimensions HIDDEN_DIMENSIONS = EntityDimensions.scalable(1.2F, 0.4F);

    protected Gorb(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.lookControl = new GorbLookControl(this, 20);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FindNearestItemGoal(this));
        this.goalSelector.addGoal(1, new MergeBookGoal(this));
        this.goalSelector.addGoal(2, new HopOutOfGroundGoal(this));
        this.goalSelector.addGoal(2, new GorbAttackGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(4, new GorbLookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(5, new GorbRandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new DigGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 16, false, true, Gorb::validTarget));
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.getPose() == MultiversePose.HIDDEN.get() ? HIDDEN_DIMENSIONS : super.getDimensions(pose);
    }

    @Override
    public boolean isPushable() {
        return !this.isDigging() && super.isPushable();
    }

    @Override
    protected void doPush(Entity entity) {
        if (this.getPose() == MultiversePose.HIDDEN.get()) {
            return;
        }
        super.doPush(entity);
    }

    private void clientDiggingParticles(AnimationState animationState) {
        if ((float)animationState.getAccumulatedTime() < 4500.0f) {
            RandomSource randomSource = this.getRandom();
            BlockState blockState = this.getBlockStateOn();
            if (blockState.getRenderShape() != RenderShape.INVISIBLE) {
                for (int i = 0; i < 30; ++i) {
                    double d = this.getX() + (double) Mth.randomBetween(randomSource, -0.7f, 0.7f);
                    double e = this.getY();
                    double f = this.getZ() + (double)Mth.randomBetween(randomSource, -0.7f, 0.7f);
                    this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockState), d, e, f, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            if (this.getPose() == Pose.DIGGING) {
                this.clientDiggingParticles(this.digAnimationState);
            }
        }
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (DATA_POSE.equals(entityDataAccessor)) {
            if (this.getPose() == Pose.DIGGING || this.getPose() == MultiversePose.HIDDEN.get()) {
                this.digAnimationState.start(this.tickCount);
            }  else {
                this.digAnimationState.stop();
            }
            if (this.getPose() == Pose.LONG_JUMPING) {
                this.hopAnimationState.start(this.tickCount);
            } else {
                this.hopAnimationState.stop();
            }
        }
        super.onSyncedDataUpdated(entityDataAccessor);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (this.isDigging() && !damageSource.isBypassInvul()) {
            return true;
        }
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public void travel(Vec3 vec3) {
        if (this.isDigging() || this.getPose() == MultiversePose.HIDDEN.get()) {
            return;
        }
        super.travel(vec3);
    }

    private boolean isDigging() {
        return this.hasPose(Pose.DIGGING);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 35.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.KNOCKBACK_RESISTANCE, 0.5).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return entityDimensions.height;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.getTarget() != null && !Gorb.hasEnchantments(this.getTarget())) {
            this.setTarget(null);
        }
    }

    public static boolean validTarget(LivingEntity livingEntity) {
        return livingEntity.getType() != MultiverseEntityTypes.GORB && Gorb.hasEnchantments(livingEntity);
    }

    public static boolean hasEnchantments(LivingEntity livingEntity) {
        boolean flag = false;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (!livingEntity.getItemBySlot(equipmentSlot).isEnchanted()) continue;
            flag = true;
        }
        return flag;
    }

    public class GorbLookControl extends SmoothSwimmingLookControl {

        public GorbLookControl(Gorb gorb, int i) {
            super(gorb, i);
        }

        @Override
        public void tick() {
            if (Gorb.this.getPose() != MultiversePose.HIDDEN.get()) {
                super.tick();
            }
        }

    }

    public class GorbAttackGoal extends MeleeAttackGoal {

        public GorbAttackGoal(PathfinderMob pathfinderMob) {
            super(pathfinderMob, 1.25, false);
        }

        @Override
        public boolean canUse() {
            return Gorb.this.getPose() != MultiversePose.HIDDEN.get() && super.canUse();
        }

    }

    public class GorbLookAtPlayerGoal extends LookAtPlayerGoal {

        public GorbLookAtPlayerGoal(Mob mob, Class<? extends LivingEntity> class_, float f) {
            super(mob, class_, f);
        }

        @Override
        public boolean canUse() {
            return Gorb.this.getPose() != MultiversePose.HIDDEN.get() && super.canUse();
        }

    }

    public class GorbRandomLookAroundGoal extends RandomLookAroundGoal {

        public GorbRandomLookAroundGoal(Mob mob) {
            super(mob);
        }

        @Override
        public boolean canUse() {
            return Gorb.this.getPose() != MultiversePose.HIDDEN.get() && super.canUse();
        }
    }

}
