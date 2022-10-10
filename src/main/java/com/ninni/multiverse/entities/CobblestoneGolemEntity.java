package com.ninni.multiverse.entities;

import com.ninni.multiverse.api.CrackableEntity;
import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.sound.MultiverseSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CobblestoneGolemEntity extends AbstractGolem implements CrackableEntity {
    public static final EntityDataAccessor<Integer> CRACKINESS = SynchedEntityData.defineId(CobblestoneGolemEntity.class, EntityDataSerializers.INT);

    public CobblestoneGolemEntity(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CRACKINESS, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setCrackiness(Crackiness.BY_ID[compoundTag.getInt("crackiness")]);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("crackiness", this.getCrackiness().getId());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createCobblestoneGolemAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0).add(Attributes.MOVEMENT_SPEED, 0.2).add(Attributes.KNOCKBACK_RESISTANCE, 1.0).add(Attributes.ATTACK_DAMAGE, 15.0);
    }

    @Override
    protected int decreaseAirSupply(int i) {
        return i;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MultiverseSoundEvents.ENTITY_COBBLESTONE_GOLEM_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return MultiverseSoundEvents.ENTITY_COBBLESTONE_GOLEM_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MultiverseSoundEvents.ENTITY_COBBLESTONE_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(MultiverseSoundEvents.BLOCK_STONE_TILES_STEP, 0.15f, 1.0f);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        return super.hurt(damageSource, f);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.is(Items.COBBLESTONE)) {
            float f = this.getHealth();
            this.heal(5.0f);
            if (this.getHealth() == f) {
                return InteractionResult.PASS;
            }
            float g = 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.2f;
            this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0f, g);
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
        }
        return InteractionResult.sidedSuccess(this.level.isClientSide);
    }

    @Override
    public void setCrackiness(Crackiness crackiness) {
        this.entityData.set(CRACKINESS, crackiness.getId());
    }

    @Override
    public Crackiness getCrackiness() {
        return Crackiness.BY_ID[this.entityData.get(CRACKINESS)];
    }
}
