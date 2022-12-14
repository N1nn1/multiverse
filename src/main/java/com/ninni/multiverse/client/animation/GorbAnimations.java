package com.ninni.multiverse.client.animation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

import static com.ninni.multiverse.client.models.GorbModel.*;
import static net.minecraft.client.model.geom.PartNames.*;

@Environment(EnvType.CLIENT)
public class GorbAnimations {

    public static final AnimationDefinition DIG = AnimationDefinition.Builder.withLength(2f)
            .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.5f, KeyframeAnimations.posVec(0f, -8f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.degreeVec(17.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LOWER_JAW, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.5f, KeyframeAnimations.posVec(0f, -10f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.6666666666666667f, KeyframeAnimations.posVec(0f, -10f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2f, KeyframeAnimations.posVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LOWER_JAW, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.degreeVec(25f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.6666666666666667f, KeyframeAnimations.degreeVec(65f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(UPPER_JAW, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(1.6666666666666667f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(UPPER_JAW, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.7916666666666666f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.0833333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1.6666666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(2f, KeyframeAnimations.degreeVec(-180f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.125f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.375f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.625f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.posVec(0f, -3f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(62.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.posVec(0f, -3f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(62.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.posVec(0f, -3f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.125f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.375f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.625f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(ALL, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.7916666666666666f, KeyframeAnimations.posVec(0f, -1f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(1f, KeyframeAnimations.posVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .build();


    public static final AnimationDefinition JUMP = AnimationDefinition.Builder.withLength(0.25f)
            .addAnimation(ALL, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(ALL, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, -3f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, -3f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, -3f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(35f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LOWER_JAW, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, -7.5f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(LOWER_JAW, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(UPPER_JAW, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(UPPER_JAW, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0f, KeyframeAnimations.degreeVec(-180f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.POSITION,
                    new Keyframe(0f, KeyframeAnimations.posVec(0f, -8f, 0f), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
            )
            .build();

}
