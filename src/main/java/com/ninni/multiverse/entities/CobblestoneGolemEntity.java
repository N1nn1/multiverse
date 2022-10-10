package com.ninni.multiverse.entities;

import com.ninni.multiverse.MultiverseTags;
import com.ninni.multiverse.api.CrackableEntity;
import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.entities.ai.FindTargettedBlockGoal;
import com.ninni.multiverse.entities.ai.MineTargettedBlockGoal;
import com.ninni.multiverse.sound.MultiverseSoundEvents;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CobblestoneGolemEntity extends AbstractGolem implements CrackableEntity {
    public static final EntityDataAccessor<Integer> CRACKINESS = SynchedEntityData.defineId(CobblestoneGolemEntity.class, EntityDataSerializers.INT);
    @Nullable
    private BlockState miningBlock;
    @Nullable
    private BlockPos minePos;

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
        this.setCrackiness(compoundTag.getInt("crackiness"));
        if (compoundTag.contains("miningState")) {
            this.setMiningBlock(NbtUtils.readBlockState(compoundTag.getCompound("miningState")));
        }
        if (compoundTag.contains("minePos")) {
            this.setMinePos(NbtUtils.readBlockPos(compoundTag.getCompound("minePos")));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("crackiness", this.getCrackiness().getId());
        if (this.getMiningBlock() != null) {
            compoundTag.put("miningState", NbtUtils.writeBlockState(this.getMiningBlock()));
        }
        if (this.getMinePos() != null) {
            compoundTag.put("minePos", NbtUtils.writeBlockPos(this.getMinePos()));
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FindTargettedBlockGoal(this));
        this.goalSelector.addGoal(1, new MineTargettedBlockGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
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

        if (itemStack.is(Items.COBBLESTONE) && this.getCrackiness() != Crackiness.NONE) {
            int crackiness = this.getCrackiness().getId();
            this.setCrackiness(crackiness - 1);
            this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.2f);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else if (this.getMiningBlock() == null && !itemStack.isEmpty()) {
            for (Holder<Block> holder : Registry.BLOCK.getTagOrEmpty(BlockTags.MINEABLE_WITH_PICKAXE)) {
                boolean flag = !itemStack.is(holder.value().asItem());
                boolean flag1 = holder.is(BlockTags.NEEDS_IRON_TOOL) || holder.is(BlockTags.NEEDS_DIAMOND_TOOL);
                if (flag) continue;
                if (flag1) continue;
                this.setMiningBlock(holder.value().defaultBlockState());
                this.playSound(MultiverseSoundEvents.BLOCK_STONE_TILES_STEP, 1.0F, 1.0F);
                if (!player.getAbilities().instabuild) itemStack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        if (this.getMiningBlock() != null && itemStack.isEmpty()) {
            this.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
            this.spawnAtLocation(this.getMiningBlock().getBlock().asItem().getDefaultInstance(), 0.5f);
            this.setMiningBlock(null);
            for (Holder<Block> holder : Registry.BLOCK.getTagOrEmpty(MultiverseTags.COBBLESTONE_GOLEM_BREAKABLES)) {
                boolean flag = !itemStack.is(holder.value().asItem());
                boolean flag1 = holder.is(BlockTags.NEEDS_IRON_TOOL) || holder.is(BlockTags.NEEDS_DIAMOND_TOOL);
                if (flag) continue;
                if (flag1) continue;
                this.setMiningBlock(holder.value().defaultBlockState());
                this.playSound(MultiverseSoundEvents.BLOCK_STONE_TILES_STEP, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }

    public <T extends ExhaustedCobblestoneGolemEntity> void becomeExhausted(EntityType<T> entityType) {
        if (!this.isRemoved()) {
            T exhaustedGolem = entityType.create(this.level);
            assert exhaustedGolem != null;
            exhaustedGolem.copyPosition(this);
            exhaustedGolem.lookAt(EntityAnchorArgument.Anchor.EYES, this.getLookAngle());
            exhaustedGolem.setYBodyRot(this.getYRot());
            exhaustedGolem.setYHeadRot(this.getYHeadRot());
            exhaustedGolem.setInvulnerable(this.isInvulnerable());
            exhaustedGolem.setCrackiness(this.getCrackiness().getId());
            if (this.getMiningBlock() != null) this.spawnAtLocation(this.getMiningBlock().getBlock().asItem().getDefaultInstance(), 0.5f);

            if (this.hasCustomName()) exhaustedGolem.setCustomName(this.getCustomName());
            this.level.addFreshEntity(exhaustedGolem);
            if (this.isPassenger()) {
                Entity entity = this.getVehicle();
                this.stopRiding();
                if (entity != null) exhaustedGolem.startRiding(entity, true);
            }

            this.discard();
        }
    }

    public void setMiningBlock(@Nullable BlockState block) {
        this.miningBlock = block;
    }

    @Nullable
    public BlockState getMiningBlock() {
        return this.miningBlock;
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
}
