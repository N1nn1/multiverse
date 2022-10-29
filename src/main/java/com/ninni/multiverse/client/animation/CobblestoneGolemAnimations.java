package com.ninni.multiverse.client.animation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

import static net.minecraft.client.model.geom.PartNames.*;

@Environment(EnvType.CLIENT)
public class CobblestoneGolemAnimations {

    public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1f).looping()
                                                                              .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                  new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.posVec(0f, -0.5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.3333333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.75f, KeyframeAnimations.posVec(0f, -0.5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.8333333333333334f, KeyframeAnimations.posVec(0f, -0.5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                  new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.2916666666666667f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.7916666666666666f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.75f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                  new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.2916666666666667f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.7916666666666666f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.75f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                  new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.2916666666666667f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.7916666666666666f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.75f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                  new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.2916666666666667f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.7916666666666666f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.75f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .build();

    public static final AnimationDefinition RUN = AnimationDefinition.Builder.withLength(0.5f).looping()

                                                                             .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                 new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.125f, KeyframeAnimations.posVec(0f, 2f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.375f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                 new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.125f, KeyframeAnimations.degreeVec(10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.375f, KeyframeAnimations.degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                 new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.041666666666666664f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.16666666666666666f, KeyframeAnimations.posVec(0f, 0 - 3f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.2916666666666667f, KeyframeAnimations.posVec(0f, 0 - 3f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.4166666666666667f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                 new Keyframe(0f, KeyframeAnimations.degreeVec(30f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.041666666666666664f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.125f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.20833333333333334f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.375f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.4583333333333333f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.degreeVec(30f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                 new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.125f, KeyframeAnimations.posVec(0f, 0 - 3f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0 - 3f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.375f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                 new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.08333333333333333f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.4166666666666667f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                 new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.041666666666666664f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.16666666666666666f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.2916666666666667f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.4166666666666667f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                 new Keyframe(0f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.041666666666666664f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.125f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.20833333333333334f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.2916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.375f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.4583333333333333f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                 new Keyframe(0f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.125f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.375f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 1f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                 new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.08333333333333333f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.4166666666666667f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                 new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                             )
                                                                             .build();

    public static final AnimationDefinition MINE_FORWARDS = AnimationDefinition.Builder.withLength(0.5f).looping()
                                                                              .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                  new Keyframe(0f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.posVec(0f, -2f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(-107.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.125f, KeyframeAnimations.degreeVec(-135f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(-80f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.375f, KeyframeAnimations.degreeVec(-80f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(-107.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                  new Keyframe(0f, KeyframeAnimations.posVec(0f, -2f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.posVec(0f, -2f, -5f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0.125f, KeyframeAnimations.degreeVec(-80f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.375f, KeyframeAnimations.degreeVec(-135f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(-80f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f,  5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                  new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                  new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f,  5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                              )
                                                                              .build();

    public static final AnimationDefinition MINE_DOWNWARDS = AnimationDefinition.Builder.withLength(0.5f).looping()
                                                                               .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                   new Keyframe(0f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.posVec(0f, -2f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(-107.5f + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(-135 + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(-80 + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(-80 + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(-107.5f + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                   new Keyframe(0f, KeyframeAnimations.posVec(0f, -2f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.posVec(0f, -2f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.posVec(0f, -2f, -5f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(-80 + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(-135 + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(-80 + 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f,  5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f,  5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .build();

    public static final AnimationDefinition MINE_UPWARDS = AnimationDefinition.Builder.withLength(0.5f).looping()
                                                                               .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                   new Keyframe(0f, KeyframeAnimations.posVec(0f, 2f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 2f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 2f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(LEFT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(-107.5f - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(-135 - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(-80 - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(-80 - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(-107.5f - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                                                   new Keyframe(0f, KeyframeAnimations.posVec(0f, 2f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 2f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 2f, -5f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(RIGHT_ARM, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(-80 - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(-135 - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(-80 - 60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(BODY, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(LEFT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f,  5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .addAnimation(RIGHT_LEG, new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                                                   new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, -5f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                                                   new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f,  5f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                                               )
                                                                               .build();

}
