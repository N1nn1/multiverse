package com.ninni.multiverse.entities.ai;

import com.ninni.multiverse.entities.MultiversePose;
import com.ninni.multiverse.entities.RainbowSheep;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.function.Predicate;

public class RainbowSheepHopAwayGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final RainbowSheep sheep;
    protected final Class<T>  avoidedClass;

    public RainbowSheepHopAwayGoal(RainbowSheep sheep, Class<T> class_, float f, double d, double e, Predicate<LivingEntity> predicate) {
        super(sheep, class_, f, d, e, predicate);
        this.sheep = sheep;
        this.avoidedClass = class_;
    }

    @Override
    public void start() {
        this.sheep.setPose(MultiversePose.HOP.get());
        super.start();
    }

    @Override
    public void stop() {
        this.sheep.setPose(Pose.STANDING);
        super.stop();
    }
}
