package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.Gorb;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MergeBookGoal extends Goal {
    private final Gorb gorb;
    private ItemEntity itemEntity;

    public MergeBookGoal(Gorb gorb) {
        this.gorb = gorb;
    }

    @Override
    public boolean canUse() {
        if (this.getNearestItem().isPresent()) {
            this.itemEntity = this.getNearestItem().get();
            return !this.gorb.getMainHandItem().isEmpty() && this.itemEntity.isAlive();
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
                ItemStack gorbStack = this.gorb.getMainHandItem();
                ItemStack spitStack = new ItemStack(Items.ENCHANTED_BOOK);
                for (Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.getEnchantments(gorbStack).entrySet()) {
                    Enchantment enchantment = entry.getKey();
                    if (EnchantmentHelper.getItemEnchantmentLevel(enchantment, spitStack) != 0) continue;
                    EnchantedBookItem.addEnchantment(spitStack, new EnchantmentInstance(enchantment, entry.getValue()));
                }
                this.gorb.spawnAtLocation(spitStack);
                this.itemEntity.discard();
            }
        }
    }

    @Override
    public void stop() {
        this.gorb.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
    }

    public Optional<ItemEntity> getNearestItem() {
        List<ItemEntity> list = this.gorb.level.getEntitiesOfClass(ItemEntity.class, this.gorb.getBoundingBox().inflate(32.0D, 16.0D, 32.0D), itemEntity -> itemEntity.getItem().is(Items.BOOK));
        list.sort(Comparator.comparingDouble(this.gorb::distanceToSqr));
        return list.stream().filter(this.gorb::hasLineOfSight).filter(itemEntity -> itemEntity.closerThan(this.gorb, 32.0D)).findFirst();
    }

}
