package com.ninni.multiverse.entities;

import com.google.common.collect.Maps;
import com.ninni.multiverse.entities.ai.FindNearestItemGoal;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Map;

public class Gorb extends PathfinderMob {
    public final Map<Enchantment, Integer> storedEnchantments = Maps.newHashMap();

    protected Gorb(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("StoredEnchantments", 9)) {
            ListTag listTag = compoundTag.getList("StoredEnchantments", 10);
            for (int i = 0; i < listTag.size(); ++i) {
                CompoundTag compoundTag2 = listTag.getCompound(i);
                ResourceLocation enchantmentId = EnchantmentHelper.getEnchantmentId(compoundTag2);
                this.storedEnchantments.put(Registry.ENCHANTMENT.get(enchantmentId), EnchantmentHelper.getEnchantmentLevel(compoundTag2));
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        if (!this.storedEnchantments.isEmpty()) {
            ListTag listTag = new ListTag();
            for (Map.Entry<Enchantment, Integer> entry : this.storedEnchantments.entrySet()) {
                Enchantment enchantment = entry.getKey();
                if (enchantment == null) continue;
                int i = entry.getValue();
                listTag.add(EnchantmentHelper.storeEnchantment(EnchantmentHelper.getEnchantmentId(enchantment), i));
            }
            compoundTag.put("StoredEnchantments", listTag);
        }
    }

    public void setStoredEnchantments(Enchantment enchantment, int level) {
        this.storedEnchantments.put(enchantment, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.KNOCKBACK_RESISTANCE, 0.5).add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FindNearestItemGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 16, false, true, Gorb::hasEnchantments));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.getTarget() != null && !Gorb.hasEnchantments(this.getTarget())) {
            this.setTarget(null);
        }
    }

    public static boolean hasEnchantments(LivingEntity livingEntity) {
        boolean flag = false;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (!livingEntity.getItemBySlot(equipmentSlot).isEnchanted()) continue;
            flag = true;
        }
        return flag;
    }

}
