package com.ninni.multiverse.entities;

import com.ninni.multiverse.entities.ai.FindNearestItemGoal;
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
import net.minecraft.world.level.Level;

public class Gorb extends PathfinderMob {

    protected Gorb(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0).add(Attributes.MOVEMENT_SPEED, 0.2).add(Attributes.KNOCKBACK_RESISTANCE, 1.0).add(Attributes.ATTACK_DAMAGE, 2.0D);
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

    private static boolean hasEnchantments(LivingEntity livingEntity) {
        boolean flag = false;
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (!livingEntity.getItemBySlot(equipmentSlot).isEnchanted()) continue;
            flag = true;
        }
        return flag;
    }

}
