package com.ninni.multiverse.entities;

import com.ninni.multiverse.init.MultiverseTags;
import com.ninni.multiverse.api.CrackableEntity;
import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.entities.ai.FindTargettedBlockGoal;
import com.ninni.multiverse.entities.ai.FollowLikedPlayerGoal;
import com.ninni.multiverse.entities.ai.MineTargettedBlockGoal;
import com.ninni.multiverse.init.MultiverseSoundEvents;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class CobblestoneGolem extends AbstractGolem implements CrackableEntity {
    public static final EntityDataAccessor<Integer> CRACKINESS = SynchedEntityData.defineId(CobblestoneGolem.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Optional<BlockState>> MINING_STATE = SynchedEntityData.defineId(CobblestoneGolem.class, EntityDataSerializers.OPTIONAL_BLOCK_STATE);
    private static final EntityDataAccessor<Optional<UUID>> LIKED_PLAYER = SynchedEntityData.defineId(CobblestoneGolem.class, EntityDataSerializers.OPTIONAL_UUID);
    @Nullable
    private BlockPos minePos;
    private int minedCount;
    private int miningCooldown;
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();
    public final AnimationState forwardsMiningAnimationState = new AnimationState();
    public final AnimationState downwardsMiningAnimationState = new AnimationState();
    public final AnimationState upwardsMiningwalkAnimationState = new AnimationState();

    //TODO Sounds
    // -Placing/getting back block sounds
    // -fixing/cracking sounds
    // -footsteps
    // -ambient when it has a mining block

    public CobblestoneGolem(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CRACKINESS, 0);
        this.entityData.define(MINING_STATE, Optional.empty());
        this.entityData.define(LIKED_PLAYER, Optional.empty());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setCrackiness(compoundTag.getInt("crackiness"));
        if (compoundTag.contains("miningState")) {
            this.setMiningBlock(NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compoundTag.getCompound("miningState")));
        }
        if (compoundTag.contains("minePos")) {
            this.setMinePos(NbtUtils.readBlockPos(compoundTag.getCompound("minePos")));
        }
        UUID uUID;
        if (compoundTag.hasUUID("LikedPlayer")) {
            uUID = compoundTag.getUUID("LikedPlayer");
        } else {
            String string = compoundTag.getString("LikedPlayer");
            uUID = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), string);
        }
        if (uUID != null) {
            this.setLikedPlayer(uUID);
        }
        this.miningCooldown = compoundTag.getInt("MiningCooldown");
        this.setMinedCount(compoundTag.getInt("MinedCount"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("crackiness", this.getCrackiness().getId());
        compoundTag.putInt("MiningCooldown", this.getMiningCooldown());
        if (this.getMiningBlock().isPresent()) {
            compoundTag.put("miningState", NbtUtils.writeBlockState(this.getMiningBlock().get()));
        }
        if (this.getMinePos() != null) {
            compoundTag.put("minePos", NbtUtils.writeBlockPos(this.getMinePos()));
        }
        if (this.getLikedPlayer() != null) {
            compoundTag.putUUID("LikedPlayer", this.getLikedPlayer());
        }
        if (this.getMinedCount() > 0) {
            compoundTag.putInt("MinedCount", this.getMinedCount());
        }
    }

    public int getMinedCount() {
        return this.minedCount;
    }

    public void setMinedCount(int minedCount) {
        this.minedCount = minedCount;
    }

    public void setMiningCooldown(int miningCooldown) {
        this.miningCooldown = miningCooldown;
    }

    public int getMiningCooldown() {
        return this.miningCooldown;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FindTargettedBlockGoal(this));
        this.goalSelector.addGoal(1, new MineTargettedBlockGoal(this));
        this.goalSelector.addGoal(2, new FollowLikedPlayerGoal(this));
        this.goalSelector.addGoal(3, new GolemRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (DATA_POSE.equals(entityDataAccessor)) {
            if (this.getPose() == MultiversePose.RUN.get() && this.isMovingOnLand()) {
                this.runAnimationState.start(this.tickCount);
            } else {
                this.runAnimationState.stop();
            }
            if (this.getPose() == MultiversePose.MINING_FORWARDS.get()) {
                this.forwardsMiningAnimationState.start(this.tickCount);
            } else {
                this.forwardsMiningAnimationState.stop();
            }
            if (this.getPose() == MultiversePose.MINING_UPWARDS.get()) {
                this.upwardsMiningwalkAnimationState.start(this.tickCount);
            } else {
                this.upwardsMiningwalkAnimationState.stop();
            }
            if (this.getPose() == MultiversePose.MINING_DOWNWARDS.get()) {
                this.downwardsMiningAnimationState.start(this.tickCount);
            } else {
                this.downwardsMiningAnimationState.stop();
            }
        }
        super.onSyncedDataUpdated(entityDataAccessor);
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            if (this.isMovingOnLand()) {
                this.walkAnimationState.startIfStopped(this.tickCount);
            } else {
                this.walkAnimationState.stop();
            }
        }
        super.tick();
    }

    private boolean isMovingOnLand() {
        return this.onGround() && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && !this.isInWaterOrBubble();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            if (this.getMiningCooldown() > 0) {
                this.setMiningCooldown(this.getMiningCooldown() - 1);
            }
            Optional.ofNullable(this.getMinePos()).flatMap(blockPos -> this.getMiningBlock().map(BlockBehaviour.BlockStateBase::getBlock).filter(block -> !this.level().getBlockState(blockPos).is(block))).ifPresent(block -> this.setMinePos(null));
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
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
        if (itemStack.is(Items.COBBLESTONE) && this.getCrackiness() != Crackiness.NONE) {
            int crackiness = this.getCrackiness().getId();
            this.setCrackiness(crackiness - 1);
            this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.2f);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else if (this.getMiningBlock().isEmpty() && !itemStack.isEmpty()) {
            for (Holder<Block> holder : BuiltInRegistries.BLOCK.getTagOrEmpty(MultiverseTags.COBBLESTONE_GOLEM_BREAKABLES)) {
                boolean flag = !itemStack.is(holder.value().asItem());
                boolean flag1 = holder.is(BlockTags.NEEDS_IRON_TOOL) || holder.is(BlockTags.NEEDS_DIAMOND_TOOL);
                if (flag) continue;
                if (flag1) continue;
                this.setMiningBlock(holder.value().defaultBlockState());
                this.setLikedPlayer(player.getUUID());
                this.playSound(MultiverseSoundEvents.BLOCK_STONE_TILES_STEP, 1.0F, 1.0F);
                if (!player.getAbilities().instabuild) itemStack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        if (this.getMiningBlock().isPresent() && itemStack.isEmpty()) {
            this.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
            this.spawnAtLocation(this.getMiningBlock().get().getBlock().asItem().getDefaultInstance(), 0.5f);
            this.setMiningBlock(null);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }

    public <T extends ExhaustedCobblestoneGolem> void becomeExhausted(EntityType<T> entityType) {
        if (!this.isRemoved()) {
            T exhaustedGolem = entityType.create(this.level());
            assert exhaustedGolem != null;
            exhaustedGolem.copyPosition(this);
            exhaustedGolem.lookAt(EntityAnchorArgument.Anchor.EYES, this.getLookAngle());
            exhaustedGolem.setYBodyRot(this.getYRot());
            exhaustedGolem.setYHeadRot(this.getYHeadRot());
            exhaustedGolem.setInvulnerable(this.isInvulnerable());
            exhaustedGolem.setCrackiness(this.getCrackiness().getId());

            this.getMiningBlock().ifPresent(state -> this.spawnAtLocation(state.getBlock().asItem().getDefaultInstance(), 0.5F));

            if (this.hasCustomName()) exhaustedGolem.setCustomName(this.getCustomName());
            this.level().addFreshEntity(exhaustedGolem);
            if (this.isPassenger()) {
                Entity entity = this.getVehicle();
                this.stopRiding();
                if (entity != null) exhaustedGolem.startRiding(entity, true);
            }

            this.discard();
        }
    }

    public void setMiningBlock(@Nullable BlockState block) {
        this.entityData.set(MINING_STATE, Optional.ofNullable(block));
    }

    @Override
    protected void dropEquipment() {
        if (this.getMiningBlock().isPresent()) {
            this.spawnAtLocation(this.getMiningBlock().get().getBlock());
        }
        super.dropEquipment();
    }

    public Optional<BlockState> getMiningBlock() {
        return this.entityData.get(MINING_STATE);
    }

    public void setMinePos(@Nullable BlockPos blockPos) {
        this.minePos = blockPos;
    }

    @Nullable
    public BlockPos getMinePos() {
        return this.minePos;
    }

    @Override
    public void setCrackiness(int crackiness) {
        this.entityData.set(CRACKINESS, crackiness);
    }

    @Override
    public Crackiness getCrackiness() {
        return Crackiness.BY_ID[this.entityData.get(CRACKINESS)];
    }

    @Nullable
    public UUID getLikedPlayer() {
        return getOptionalUUID().orElse(null);
    }

    public Optional<UUID> getOptionalUUID() {
        return this.entityData.get(LIKED_PLAYER);
    }

    public void setLikedPlayer(@Nullable UUID uUID) {
        this.entityData.set(LIKED_PLAYER, Optional.ofNullable(uUID));
    }

    public static class GolemRandomStrollGoal extends RandomStrollGoal {

        public GolemRandomStrollGoal(PathfinderMob pathfinderMob, double d) {
            super(pathfinderMob, d);
        }

        @Override
        @Nullable
        protected Vec3 getPosition() {
            return DefaultRandomPos.getPos(this.mob, 4, 3);
        }
    }
}
