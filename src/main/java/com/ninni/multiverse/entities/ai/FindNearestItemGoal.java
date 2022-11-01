package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.Gorb;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;

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
        if (this.getNearestItem().isPresent() && this.gorb.getMainHandItem().isEmpty()) {
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
            if (this.gorb.distanceToSqr(this.itemEntity) <= 3) {
                this.gorb.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);
                this.gorb.setItemInHand(InteractionHand.MAIN_HAND, this.itemEntity.getItem());
                if (!this.gorb.getMainHandItem().isEmpty()) {
                    this.itemEntity.discard();
                }
            }
        }
    }

    @Override
    public void stop() {
        this.gorb.setTarget(null);
    }

    public Optional<ItemEntity> getNearestItem() {
        List<ItemEntity> list = this.gorb.level.getEntitiesOfClass(ItemEntity.class, this.gorb.getBoundingBox().inflate(32.0D, 16.0D, 32.0D), itemEntity -> itemEntity.getItem().isEnchanted());
        list.sort(Comparator.comparingDouble(this.gorb::distanceToSqr));
        return list.stream().filter(this.gorb::hasLineOfSight).filter(itemEntity -> itemEntity.closerThan(this.gorb, 32.0D)).findFirst();
    }

}
