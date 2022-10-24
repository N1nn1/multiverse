package com.ninni.multiverse.entities;

import com.ninni.multiverse.MultiverseTags;
import com.ninni.multiverse.entities.ai.DrinkWaterGoal;
import com.ninni.multiverse.entities.ai.RainbowSheepHopAwayGoal;
import com.ninni.multiverse.item.MultiverseItems;
import com.ninni.multiverse.loot.MultiverseBuiltInLootTables;
import com.ninni.multiverse.sound.MultiverseSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class RainbowSheep extends Animal implements Shearable {
    private static final EntityDataAccessor<Boolean> DATA_SHEARING_ID = SynchedEntityData.defineId(RainbowSheep.class, EntityDataSerializers.BOOLEAN);
    private static final Predicate<Entity> AVOID_PLAYERS = EntitySelector.NO_CREATIVE_OR_SPECTATOR;
    private int eatAnimationTick;
    private int drinkingCooldown;
    private EatBlockGoal eatBlockGoal;
    public boolean isHydrated;

    protected RainbowSheep(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.eatBlockGoal = new EatBlockGoal(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(1, new RainbowSheepHopAwayGoal<>(this, Player.class, 16.0f, 1.6, 1.4, livingEntity -> AVOID_PLAYERS.test(livingEntity) && !(livingEntity.getMainHandItem().is(MultiverseTags.RAINBOW_SHEEP_LOVED) || livingEntity.getOffhandItem().is(MultiverseTags.RAINBOW_SHEEP_LOVED) || livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(MultiverseTags.RAINBOW_SHEEP_BYPASSES) || livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(MultiverseTags.RAINBOW_SHEEP_BYPASSES) || livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(MultiverseTags.RAINBOW_SHEEP_BYPASSES) || livingEntity.getItemBySlot(EquipmentSlot.FEET).is(MultiverseTags.RAINBOW_SHEEP_BYPASSES))));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.of(MultiverseTags.RAINBOW_SHEEP_LOVED), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1));
        this.goalSelector.addGoal(5, new DrinkWaterGoal(this));
        this.goalSelector.addGoal(6, this.eatBlockGoal);
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    public static boolean checkRainbowSheepSpawnRules(EntityType<? extends RainbowSheep> entityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return (levelAccessor.getLevel().isThundering() || levelAccessor.getLevel().isRaining()) && levelAccessor.getBlockState(blockPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && Animal.isBrightEnoughToSpawn(levelAccessor, blockPos);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0).add(Attributes.MOVEMENT_SPEED, 0.23f);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.GOLDEN_APPLE);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.is(Items.SHEARS)) {
            if (!this.level.isClientSide && this.readyForShearing() && this.canPlayerShear(player)) {
                this.shear(SoundSource.PLAYERS);
                this.gameEvent(GameEvent.SHEAR, player);
                this.setHydrated(false);
                itemStack.hurtAndBreak(1, player, player2 -> player2.broadcastBreakEvent(interactionHand));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        return super.mobInteract(player, interactionHand);
    }

    public boolean canPlayerShear(Player player) {
        boolean flag = false;
        for (ItemStack stack : player.getArmorSlots()) {
            if (!stack.is(MultiverseTags.RAINBOW_SHEEP_BYPASSES)) continue;
            flag = true;
        }
        for (InteractionHand interactionHand : InteractionHand.values()) {
            if (!player.getItemInHand(interactionHand).is(MultiverseTags.RAINBOW_SHEEP_LOVED)) continue;
            flag = true;
        }
        return flag;
    }

    @Override
    public void shear(SoundSource soundSource) {
        this.level.playSound(null, this, SoundEvents.SHEEP_SHEAR, soundSource, 1.0f, 1.0f);
        this.setSheared(true);
        int i = 1 + this.random.nextInt(3);
        for (int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.spawnAtLocation(MultiverseItems.RAINBOW_WOOL, 1);
            if (itemEntity == null) continue;
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1f, this.random.nextFloat() * 0.05f, (this.random.nextFloat() - this.random.nextFloat()) * 0.1f));
        }
    }

    @Override
    public boolean readyForShearing() {
        return this.isAlive() && !this.isSheared() && !this.isBaby();
    }

    @Override
    public ResourceLocation getDefaultLootTable() {
        if (this.isSheared()) {
            return this.getType().getDefaultLootTable();
        }
        return MultiverseBuiltInLootTables.RAINBOW_SHEEP_RAINBOW;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SHEARING_ID, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("Sheared", this.isSheared());
        compoundTag.putBoolean("Hydrated", this.isHydrated());
        compoundTag.putInt("DrinkingCooldown", this.getDrinkingCooldown());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setSheared(compoundTag.getBoolean("Sheared"));
        this.setHydrated(compoundTag.getBoolean("Hydrated"));
        this.setDrinkingCooldown(compoundTag.getInt("DrinkingCooldown"));
    }

    public void setDrinkingCooldown(int drinkingCooldown) {
        this.drinkingCooldown = drinkingCooldown;
    }

    public int getDrinkingCooldown() {
        return this.drinkingCooldown;
    }

    public void setHydrated(boolean hydrated) {
        this.isHydrated = hydrated;
    }

    public boolean isHydrated() {
        return this.isHydrated;
    }

    @Override
    protected void customServerAiStep() {
        this.eatAnimationTick = this.eatBlockGoal.getEatAnimationTick();
        super.customServerAiStep();
    }

    @Override
    public void aiStep() {
        if (this.level.isClientSide) {
            this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        }
        if (this.readyForShearing() && this.random.nextInt(60) == 0) {
            for (int i = 0; i < this.random.nextInt(1) + 1; ++i) {
                this.level.addParticle(ParticleTypes.WAX_OFF, this.getRandomX(0.6), this.getRandomY() + 1, this.getRandomZ(0.6), 0.0, this.random.nextFloat() * 5, 0.0);
            }
        }
        if (!this.level.isClientSide) {
            if (this.getDrinkingCooldown() > 0) {
                this.setDrinkingCooldown(this.getDrinkingCooldown() - 1);
            }
        }
        super.aiStep();
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 10) {
            this.eatAnimationTick = 40;
        }
        else if (b == 11) {
            this.eatAnimationTick = 36;
        }
        else {
            super.handleEntityEvent(b);
        }
    }

    public float getHeadEatPositionScale(float f) {
        if (this.eatAnimationTick <= 0) {
            return 0.0f;
        }
        if (this.eatAnimationTick >= 4 && this.eatAnimationTick <= 36) {
            return 1.0f;
        }
        if (this.eatAnimationTick < 4) {
            return ((float)this.eatAnimationTick - f) / 4.0f;
        }
        return -((float)(this.eatAnimationTick - 40) - f) / 4.0f;
    }

    public float getHeadEatAngleScale(float f) {
        if (this.eatAnimationTick > 4 && this.eatAnimationTick <= 36) {
            float g = ((float)(this.eatAnimationTick - 4) - f) / 32.0f;
            return 0.62831855f + 0.21991149f * Mth.sin(g * 28.7f);
        }
        if (this.eatAnimationTick > 0) {
            return 0.62831855f;
        }
        return this.getXRot() * ((float)Math.PI / 180);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.95f * entityDimensions.height;
    }

    @Override
    public void ate() {
        super.ate();
        if (this.isHydrated() && this.getLevel().canSeeSky(this.blockPosition())) {
            this.setSheared(false);
        }
        if (this.isBaby()) {
            this.ageUp(60);
        }
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return MultiverseEntityTypes.RAINBOW_SHEEP.create(serverLevel);
    }

    @Override
    public int getAmbientSoundInterval() {
        return 240;
    }

    public boolean isSheared() {
        return (this.entityData.get(DATA_SHEARING_ID));
    }

    public void setSheared(boolean bl) {
        this.entityData.set(DATA_SHEARING_ID, bl);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return MultiverseSoundEvents.ENTITY_RAINBOW_SHEEP_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return MultiverseSoundEvents.ENTITY_RAINBOW_SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return MultiverseSoundEvents.ENTITY_RAINBOW_SHEEP_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        this.playSound(SoundEvents.SHEEP_STEP, 0.15f, 1.0f);
    }

}
