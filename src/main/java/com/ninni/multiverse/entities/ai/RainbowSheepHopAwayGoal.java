package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.RainbowSheep;
import com.ninni.multiverse.init.MultiverseSoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

import java.util.function.Predicate;

public class RainbowSheepHopAwayGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final RainbowSheep sheep;
    protected final Class<T> avoidedClass;

    public RainbowSheepHopAwayGoal(RainbowSheep sheep, Class<T> class_, float f, double d, double e, Predicate<LivingEntity> predicate) {
        super(sheep, class_, f, d, e, predicate);
        this.sheep = sheep;
        this.avoidedClass = class_;
    }

    @Override
    public void start() {
        if (!this.sheep.isSilent()) this.sheep.playSound(MultiverseSoundEvents.ENTITY_RAINBOW_SHEEP_SCARED);
        super.start();
    }
}
