package com.ninni.multiverse.entities;

import com.ninni.multiverse.api.CrackableEntity;
import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.item.MultiverseItems;
import net.minecraft.core.Rotations;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ExhaustedCobblestoneGolemEntity extends LivingEntity implements CrackableEntity {
    private static final List<ItemStack> EMPTY_LIST = Collections.emptyList();
    private static final Rotations DEFAULT_HEAD_POSE = new Rotations(0.0f, 0.0f, 0.0f);
    private static final Rotations DEFAULT_BODY_POSE = new Rotations(0.0f, 0.0f, 0.0f);
    private static final Rotations DEFAULT_LEFT_ARM_POSE = new Rotations(0.0f, 0.0f, 0.0f);
    private static final Rotations DEFAULT_RIGHT_ARM_POSE = new Rotations(0.0f, 0.0f, 0.0f);
    private static final Rotations DEFAULT_LEFT_LEG_POSE = new Rotations(0.0f, 0.0f, 0.0f);
    private static final Rotations DEFAULT_RIGHT_LEG_POSE = new Rotations(0.0f, 0.0f, 0.0f);
    public static final EntityDataAccessor<Byte> DATA_CLIENT_FLAGS = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<Rotations> DATA_HEAD_POSE = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.ROTATIONS);
    public static final EntityDataAccessor<Rotations> DATA_BODY_POSE = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.ROTATIONS);
    public static final EntityDataAccessor<Rotations> DATA_LEFT_ARM_POSE = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.ROTATIONS);
    public static final EntityDataAccessor<Rotations> DATA_RIGHT_ARM_POSE = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.ROTATIONS);
    public static final EntityDataAccessor<Rotations> DATA_LEFT_LEG_POSE = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.ROTATIONS);
    public static final EntityDataAccessor<Rotations> DATA_RIGHT_LEG_POSE = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.ROTATIONS);
    public static final EntityDataAccessor<Integer> CRACKINESS = SynchedEntityData.defineId(ExhaustedCobblestoneGolemEntity.class, EntityDataSerializers.INT);
    private static final Predicate<Entity> RIDABLE_MINECARTS = entity -> entity instanceof AbstractMinecart && ((AbstractMinecart)entity).getMinecartType() == AbstractMinecart.Type.RIDEABLE;
    private boolean invisible;
    public long lastHit;
    private int disabledSlots;
    private Rotations headPose = DEFAULT_HEAD_POSE;
    private Rotations bodyPose = DEFAULT_BODY_POSE;
    private Rotations leftArmPose = DEFAULT_LEFT_ARM_POSE;
    private Rotations rightArmPose = DEFAULT_RIGHT_ARM_POSE;
    private Rotations leftLegPose = DEFAULT_LEFT_LEG_POSE;
    private Rotations rightLegPose = DEFAULT_RIGHT_LEG_POSE;

    public ExhaustedCobblestoneGolemEntity(EntityType<? extends ExhaustedCobblestoneGolemEntity> entityType, Level level) {
        super(entityType, level);
        this.maxUpStep = 0.0f;
    }

    public ExhaustedCobblestoneGolemEntity(Level level, double d, double e, double f) {
        this(MultiverseEntityTypes.EXHAUSTED_COBBLESTONE_GOLEM, level);
        this.setPos(d, e, f);
    }

    @Override
    public void refreshDimensions() {
        double d = this.getX();
        double e = this.getY();
        double f = this.getZ();
        super.refreshDimensions();
        this.setPos(d, e, f);
    }

    private boolean hasPhysics() {
        return !this.isMarker() && !this.isNoGravity();
    }

    @Override
    public boolean isEffectiveAi() {
        return super.isEffectiveAi() && this.hasPhysics();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CLIENT_FLAGS, (byte)0);
        this.entityData.define(DATA_HEAD_POSE, DEFAULT_HEAD_POSE);
        this.entityData.define(DATA_BODY_POSE, DEFAULT_BODY_POSE);
        this.entityData.define(DATA_LEFT_ARM_POSE, DEFAULT_LEFT_ARM_POSE);
        this.entityData.define(DATA_RIGHT_ARM_POSE, DEFAULT_RIGHT_ARM_POSE);
        this.entityData.define(DATA_LEFT_LEG_POSE, DEFAULT_LEFT_LEG_POSE);
        this.entityData.define(DATA_RIGHT_LEG_POSE, DEFAULT_RIGHT_LEG_POSE);
        this.entityData.define(CRACKINESS, 0);
    }

    @Override
    public boolean canTakeItem(ItemStack itemStack) {
        EquipmentSlot equipmentSlot = Mob.getEquipmentSlotForItem(itemStack);
        return this.getItemBySlot(equipmentSlot).isEmpty() && !this.isDisabled(equipmentSlot);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        ListTag listTag = new ListTag();
        compoundTag.put("ArmorItems", listTag);
        ListTag listTag2 = new ListTag();
        compoundTag.put("HandItems", listTag2);
        compoundTag.putBoolean("Invisible", this.isInvisible());
        compoundTag.putBoolean("Small", this.isSmall());
        compoundTag.putBoolean("ShowArms", this.isShowArms());
        compoundTag.putInt("DisabledSlots", this.disabledSlots);
        compoundTag.putBoolean("NoBasePlate", this.isNoBasePlate());
        if (this.isMarker()) {
            compoundTag.putBoolean("Marker", this.isMarker());
        }
        compoundTag.put("Pose", this.writePose());
        compoundTag.putInt("crackiness", this.getCrackiness().getId());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setInvisible(compoundTag.getBoolean("Invisible"));
        this.setSmall(compoundTag.getBoolean("Small"));
        this.setShowArms(compoundTag.getBoolean("ShowArms"));
        this.disabledSlots = compoundTag.getInt("DisabledSlots");
        this.setNoBasePlate(compoundTag.getBoolean("NoBasePlate"));
        this.setMarker(compoundTag.getBoolean("Marker"));
        this.noPhysics = !this.hasPhysics();
        CompoundTag compoundTag2 = compoundTag.getCompound("Pose");
        this.readPose(compoundTag2);
        this.setCrackiness(Crackiness.BY_ID[compoundTag.getInt("crackiness")]);
    }

    private void readPose(CompoundTag compoundTag) {
        ListTag listTag = compoundTag.getList("Head", 5);
        this.setHeadPose(listTag.isEmpty() ? DEFAULT_HEAD_POSE : new Rotations(listTag));
        ListTag listTag2 = compoundTag.getList("Body", 5);
        this.setBodyPose(listTag2.isEmpty() ? DEFAULT_BODY_POSE : new Rotations(listTag2));
        ListTag listTag3 = compoundTag.getList("LeftArm", 5);
        this.setLeftArmPose(listTag3.isEmpty() ? DEFAULT_LEFT_ARM_POSE : new Rotations(listTag3));
        ListTag listTag4 = compoundTag.getList("RightArm", 5);
        this.setRightArmPose(listTag4.isEmpty() ? DEFAULT_RIGHT_ARM_POSE : new Rotations(listTag4));
        ListTag listTag5 = compoundTag.getList("LeftLeg", 5);
        this.setLeftLegPose(listTag5.isEmpty() ? DEFAULT_LEFT_LEG_POSE : new Rotations(listTag5));
        ListTag listTag6 = compoundTag.getList("RightLeg", 5);
        this.setRightLegPose(listTag6.isEmpty() ? DEFAULT_RIGHT_LEG_POSE : new Rotations(listTag6));
    }

    private CompoundTag writePose() {
        CompoundTag compoundTag = new CompoundTag();
        if (!DEFAULT_HEAD_POSE.equals(this.headPose)) {
            compoundTag.put("Head", this.headPose.save());
        }
        if (!DEFAULT_BODY_POSE.equals(this.bodyPose)) {
            compoundTag.put("Body", this.bodyPose.save());
        }
        if (!DEFAULT_LEFT_ARM_POSE.equals(this.leftArmPose)) {
            compoundTag.put("LeftArm", this.leftArmPose.save());
        }
        if (!DEFAULT_RIGHT_ARM_POSE.equals(this.rightArmPose)) {
            compoundTag.put("RightArm", this.rightArmPose.save());
        }
        if (!DEFAULT_LEFT_LEG_POSE.equals(this.leftLegPose)) {
            compoundTag.put("LeftLeg", this.leftLegPose.save());
        }
        if (!DEFAULT_RIGHT_LEG_POSE.equals(this.rightLegPose)) {
            compoundTag.put("RightLeg", this.rightLegPose.save());
        }
        return compoundTag;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {
    }

    @Override
    protected void pushEntities() {
        List<Entity> list = this.level.getEntities(this, this.getBoundingBox(), RIDABLE_MINECARTS);
        for (int i = 0; i < list.size(); ++i) {
            Entity entity = list.get(i);
            if (!(this.distanceToSqr(entity) <= 0.2)) continue;
            entity.push(this);
        }
    }

    private boolean isDisabled(EquipmentSlot equipmentSlot) {
        return (this.disabledSlots & 1 << equipmentSlot.getFilterFlag()) != 0 || equipmentSlot.getType() == EquipmentSlot.Type.HAND && !this.isShowArms();
    }

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if (this.level.isClientSide || this.isRemoved()) {
            return false;
        }
        if (DamageSource.OUT_OF_WORLD.equals(damageSource)) {
            this.kill();
            return false;
        }
        if (this.isInvulnerableTo(damageSource) || this.invisible || this.isMarker()) {
            return false;
        }
        if (damageSource.isExplosion()) {
            this.brokenByAnything(damageSource);
            this.kill();
            return false;
        }
        if (DamageSource.IN_FIRE.equals(damageSource)) {
            if (this.isOnFire()) {
                this.causeDamage(damageSource, 0.15f);
            } else {
                this.setSecondsOnFire(5);
            }
            return false;
        }
        if (DamageSource.ON_FIRE.equals(damageSource) && this.getHealth() > 0.5f) {
            this.causeDamage(damageSource, 4.0f);
            return false;
        }
        boolean bl = damageSource.getDirectEntity() instanceof AbstractArrow;
        boolean bl2 = bl && ((AbstractArrow)damageSource.getDirectEntity()).getPierceLevel() > 0;
        boolean bl3 = "player".equals(damageSource.getMsgId());
        if (!bl3 && !bl) {
            return false;
        }
        if (damageSource.getEntity() instanceof Player && !((Player)damageSource.getEntity()).getAbilities().mayBuild) {
            return false;
        }
        if (damageSource.isCreativePlayer()) {
            this.playBrokenSound();
            this.showBreakingParticles();
            this.kill();
            return bl2;
        }
        long l = this.level.getGameTime();
        if (l - this.lastHit <= 5L || bl) {
            this.brokenByPlayer(damageSource);
            this.showBreakingParticles();
            this.kill();
        } else {
            this.level.broadcastEntityEvent(this, (byte)32);
            this.gameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getEntity());
            this.lastHit = l;
        }
        return true;
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 32) {
            if (this.level.isClientSide) {
                this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_HIT, this.getSoundSource(), 0.3f, 1.0f, false);
                this.lastHit = this.level.getGameTime();
            }
        } else {
            super.handleEntityEvent(b);
        }
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return EMPTY_LIST;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot equipmentSlot) {
        return new ItemStack(Items.AIR);
    }

    @Override
    public void setItemSlot(EquipmentSlot equipmentSlot, ItemStack itemStack) {
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double d) {
        double e = this.getBoundingBox().getSize() * 4.0;
        if (Double.isNaN(e) || e == 0.0) {
            e = 4.0;
        }
        return d < (e *= 64.0) * e;
    }

    private void showBreakingParticles() {
        if (this.level instanceof ServerLevel) {
            ((ServerLevel)this.level).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.COBBLESTONE.defaultBlockState()), this.getX(), this.getY(0.6666666666666666), this.getZ(), 10, this.getBbWidth() / 4.0f, this.getBbHeight() / 4.0f, this.getBbWidth() / 4.0f, 0.05);
        }
    }

    private void causeDamage(DamageSource damageSource, float f) {
        float g = this.getHealth();
        if ((g -= f) <= 0.5f) {
            this.brokenByAnything(damageSource);
            this.kill();
        } else {
            this.setHealth(g);
            this.gameEvent(GameEvent.ENTITY_DAMAGE, damageSource.getEntity());
        }
    }

    private void brokenByPlayer(DamageSource damageSource) {
        ItemStack itemStack = new ItemStack(MultiverseItems.EXHAUSTED_COBBLESTONE_GOLEM);
        itemStack.getOrCreateTag().putInt("crackiness", this.getCrackiness().getId());
        itemStack.setHoverName(this.getCustomName());
        Block.popResource(this.level, this.blockPosition(), itemStack);
        this.brokenByAnything(damageSource);
    }

    private void brokenByAnything(DamageSource damageSource) {
        this.playBrokenSound();
        this.dropAllDeathLoot(damageSource);
    }

    private void playBrokenSound() {
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_BREAK, this.getSoundSource(), 1.0f, 1.0f);
    }

    @Override
    protected float tickHeadTurn(float f, float g) {
        this.yBodyRotO = this.yRotO;
        this.yBodyRot = this.getYRot();
        return 0.0f;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return entityDimensions.height * (this.isBaby() ? 0.5f : 0.9f);
    }

    @Override
    public double getMyRidingOffset() {
        return this.isMarker() ? 0.0 : (double)0.1f;
    }

    @Override
    public void travel(Vec3 vec3) {
        if (!this.hasPhysics()) {
            return;
        }
        super.travel(vec3);
    }

    @Override
    public void setYBodyRot(float f) {
        this.yBodyRotO = this.yRotO = f;
        this.yHeadRotO = this.yHeadRot = f;
    }

    @Override
    public void setYHeadRot(float f) {
        this.yBodyRotO = this.yRotO = f;
        this.yHeadRotO = this.yHeadRot = f;
    }

    @Override
    public void tick() {
        Rotations rotations6;
        Rotations rotations5;
        Rotations rotations4;
        Rotations rotations3;
        Rotations rotations2;
        super.tick();
        Rotations rotations = this.entityData.get(DATA_HEAD_POSE);
        if (!this.headPose.equals(rotations)) {
            this.setHeadPose(rotations);
        }
        if (!this.bodyPose.equals(rotations2 = this.entityData.get(DATA_BODY_POSE))) {
            this.setBodyPose(rotations2);
        }
        if (!this.leftArmPose.equals(rotations3 = this.entityData.get(DATA_LEFT_ARM_POSE))) {
            this.setLeftArmPose(rotations3);
        }
        if (!this.rightArmPose.equals(rotations4 = this.entityData.get(DATA_RIGHT_ARM_POSE))) {
            this.setRightArmPose(rotations4);
        }
        if (!this.leftLegPose.equals(rotations5 = this.entityData.get(DATA_LEFT_LEG_POSE))) {
            this.setLeftLegPose(rotations5);
        }
        if (!this.rightLegPose.equals(rotations6 = this.entityData.get(DATA_RIGHT_LEG_POSE))) {
            this.setRightLegPose(rotations6);
        }
    }

    @Override
    protected void updateInvisibilityStatus() {
        this.setInvisible(this.invisible);
    }

    @Override
    public void setInvisible(boolean bl) {
        this.invisible = bl;
        super.setInvisible(bl);
    }

    @Override
    public boolean isBaby() {
        return this.isSmall();
    }

    @Override
    public void kill() {
        this.remove(Entity.RemovalReason.KILLED);
        this.gameEvent(GameEvent.ENTITY_DIE);
    }

    @Override
    public boolean ignoreExplosion() {
        return this.isInvisible();
    }

    @Override
    public PushReaction getPistonPushReaction() {
        if (this.isMarker()) {
            return PushReaction.IGNORE;
        }
        return super.getPistonPushReaction();
    }

    private void setSmall(boolean bl) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 1, bl));
    }

    public boolean isSmall() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 1) != 0;
    }

    private void setShowArms(boolean bl) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 4, bl));
    }

    public boolean isShowArms() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 4) != 0;
    }

    private void setNoBasePlate(boolean bl) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 8, bl));
    }

    public boolean isNoBasePlate() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 8) != 0;
    }

    private void setMarker(boolean bl) {
        this.entityData.set(DATA_CLIENT_FLAGS, this.setBit(this.entityData.get(DATA_CLIENT_FLAGS), 16, bl));
    }

    public boolean isMarker() {
        return (this.entityData.get(DATA_CLIENT_FLAGS) & 0x10) != 0;
    }

    private byte setBit(byte b, int i, boolean bl) {
        b = bl ? (byte)(b | i) : (byte)(b & ~i);
        return b;
    }

    public void setHeadPose(Rotations rotations) {
        this.headPose = rotations;
        this.entityData.set(DATA_HEAD_POSE, rotations);
    }

    public void setBodyPose(Rotations rotations) {
        this.bodyPose = rotations;
        this.entityData.set(DATA_BODY_POSE, rotations);
    }

    public void setLeftArmPose(Rotations rotations) {
        this.leftArmPose = rotations;
        this.entityData.set(DATA_LEFT_ARM_POSE, rotations);
    }

    public void setRightArmPose(Rotations rotations) {
        this.rightArmPose = rotations;
        this.entityData.set(DATA_RIGHT_ARM_POSE, rotations);
    }

    public void setLeftLegPose(Rotations rotations) {
        this.leftLegPose = rotations;
        this.entityData.set(DATA_LEFT_LEG_POSE, rotations);
    }

    public void setRightLegPose(Rotations rotations) {
        this.rightLegPose = rotations;
        this.entityData.set(DATA_RIGHT_LEG_POSE, rotations);
    }

    public Rotations getHeadPose() {
        return this.headPose;
    }

    public Rotations getBodyPose() {
        return this.bodyPose;
    }

    public Rotations getLeftArmPose() {
        return this.leftArmPose;
    }

    public Rotations getRightArmPose() {
        return this.rightArmPose;
    }

    public Rotations getLeftLegPose() {
        return this.leftLegPose;
    }

    public Rotations getRightLegPose() {
        return this.rightLegPose;
    }

    @Override
    public boolean isPickable() {
        return super.isPickable() && !this.isMarker();
    }

    @Override
    public boolean skipAttackInteraction(Entity entity) {
        return entity instanceof Player && !this.level.mayInteract((Player)entity, this.blockPosition());
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public LivingEntity.Fallsounds getFallSounds() {
        return new LivingEntity.Fallsounds(SoundEvents.ARMOR_STAND_FALL, SoundEvents.ARMOR_STAND_FALL);
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ARMOR_STAND_HIT;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.ARMOR_STAND_BREAK;
    }

    @Override
    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (DATA_CLIENT_FLAGS.equals(entityDataAccessor)) {
            this.refreshDimensions();
            this.blocksBuilding = !this.isMarker();
        }
        super.onSyncedDataUpdated(entityDataAccessor);
    }

    @Override
    public boolean attackable() {
        return false;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(MultiverseItems.EXHAUSTED_COBBLESTONE_GOLEM);
    }

    @Override
    public boolean canBeSeenByAnyone() {
        return !this.isInvisible() && !this.isMarker();
    }

    @Override
    public void setCrackiness(Crackiness crackiness) {
        this.entityData.set(CRACKINESS, crackiness.getId());
    }

    @Override
    public Crackiness getCrackiness() {
        return Crackiness.BY_ID[this.entityData.get(CRACKINESS)];
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.isAlive();
    }

}
