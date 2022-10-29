package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.Gorb;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FindNearestItemGoal extends Goal {
    private final Gorb gorb;
    private ItemEntity itemEntity;

    public FindNearestItemGoal(Gorb gorb) {
        this.gorb = gorb;
    }

    @Override
    public boolean canUse() {
        if (this.getNearestItem().isPresent()) {
            this.itemEntity = this.getNearestItem().get();
            return this.itemEntity.isAlive();
        }
        return false;
    }

    @Override
    public void tick() {
        if (this.itemEntity != null) {
            this.gorb.getNavigation().moveTo(this.itemEntity.getX(), this.itemEntity.getY(), this.itemEntity.getZ(), 1.0D);
            this.gorb.getLookControl().setLookAt(this.itemEntity);
            if (this.gorb.distanceToSqr(this.itemEntity) <= 2) {
                this.gorb.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);
                EnchantmentHelper.getEnchantments(this.itemEntity.getItem());
                for (Enchantment enchantment : EnchantmentHelper.getEnchantments(this.itemEntity.getItem()).keySet()) {
                    if (enchantment != null) {
                        ListTag listTag = this.itemEntity.getItem().getEnchantmentTags();
                        for (int i = 0; i < listTag.size(); ++i) {
                            CompoundTag compoundTag = listTag.getCompound(i);
                            this.gorb.setStoredEnchantments(enchantment, EnchantmentHelper.getEnchantmentLevel(compoundTag));
                        }
                    }
                }
                this.itemEntity.discard();
            }
        }
    }

    public Optional<ItemEntity> getNearestItem() {
        List<ItemEntity> list = this.gorb.level.getEntitiesOfClass(ItemEntity.class, this.gorb.getBoundingBox().inflate(32.0D, 16.0D, 32.0D), itemEntity -> itemEntity.getItem().isEnchanted());
        list.sort(Comparator.comparingDouble(this.gorb::distanceToSqr));
        return list.stream().filter(this.gorb::hasLineOfSight).filter(itemEntity -> itemEntity.closerThan(this.gorb, 32.0D)).findFirst();
    }

}