package com.ninni.multiverse.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ninni.multiverse.client.animation.GorbAnimations;
import com.ninni.multiverse.entities.Gorb;
import com.ninni.multiverse.entities.MultiversePose;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import static net.minecraft.client.model.geom.PartNames.*;
import static net.minecraft.util.Mth.clamp;

@SuppressWarnings("FieldCanBeLocal, unused")
public class GorbModel<T extends Gorb> extends HierarchicalModel<T> {
    public static final String LOWER_JAW = "lower_jaw";
    public static final String UPPER_JAW = "upper_jaw";
    public static final String CROP = "crop";
    public static final String ALL = "all";

    private final ModelPart root;
    private final ModelPart all;

    private final ModelPart body;
    private final ModelPart lowerJaw;
    private final ModelPart leftArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart tail;
    private final ModelPart upperJaw;
    private final ModelPart crop;

    public GorbModel(ModelPart root) {
        this.root = root;

        this.all = root.getChild(ALL);

        this.body = all.getChild(BODY);
        this.lowerJaw = all.getChild(LOWER_JAW);
        this.leftArm = all.getChild(LEFT_ARM);
        this.leftLeg = all.getChild(LEFT_LEG);
        this.rightLeg = all.getChild(RIGHT_LEG);
        this.rightArm = all.getChild(RIGHT_ARM);

        this.tail = body.getChild(TAIL);

        this.upperJaw = lowerJaw.getChild(UPPER_JAW);
        this.crop = lowerJaw.getChild(CROP);

    }

    public static LayerDefinition getLayerDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild(
                ALL,
                CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition body = all.addOrReplaceChild(
                BODY,
                CubeListBuilder.create()
                        .texOffs(0, 40)
                        .addBox(-5.0F, -4.5F, -6.0F, 10.0F, 6.0F, 12.0F),
                PartPose.offset(0.0F, 18.5F, 0.0F)
        );

        PartDefinition tail = body.addOrReplaceChild(
                TAIL, CubeListBuilder.create()
                        .texOffs(39, 27)
                        .addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 6.0F),
                PartPose.offsetAndRotation(0.0F, -3.5F, 6.0F, -0.7854F, 0.0F, 0.0F)
        );

        PartDefinition lowerJaw = all.addOrReplaceChild(
                LOWER_JAW, CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-6.0F, -2.5F, -15.0F, 12.0F, 5.0F, 15.0F)
                        .texOffs(39, 0)
                        .addBox(-6.0F, -1.5F, -15.0F, 12.0F, 0.0F, 15.0F)
                        .texOffs(39, 18)
                        .addBox(0.0F, 2.5F, -8.0F, 0.0F, 3.0F, 6.0F),
                PartPose.offset(0.0F, 13.5F, -2.0F)
        );

        PartDefinition upperJaw = lowerJaw.addOrReplaceChild(
                UPPER_JAW, CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-6.0F, -5.0F, -15.0F, 12.0F, 5.0F, 15.0F)
                        .texOffs(39, 15)
                        .addBox(-6.0F, -1.0F, -15.0F, 12.0F, 0.0F, 15.0F),
                PartPose.offset(0.0F, -1.5F, 0.0F)
        );

        PartDefinition crop = lowerJaw.addOrReplaceChild(
                CROP, CubeListBuilder.create()
                        .texOffs(0, 58)
                        .addBox(-4.0F, 2.5F, -9.0F, 8.0F, 4.0F, 6.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftArm = all.addOrReplaceChild(
                LEFT_ARM, CubeListBuilder.create()
                        .texOffs(32, 40)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(3.0F, 20.0F, -4.0F)
        );

        PartDefinition leftLeg = all.addOrReplaceChild(
                LEFT_LEG, CubeListBuilder.create()
                        .texOffs(44, 44)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(3.0F, 20.0F, 4.0F)
        );

        PartDefinition rightLeg = all.addOrReplaceChild(
                RIGHT_LEG, CubeListBuilder.create()
                        .texOffs(32, 40)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F)
                        .mirror(false),
                PartPose.offset(-3.0F, 20.0F, 4.0F)
        );

        PartDefinition rightArm = all.addOrReplaceChild(
                RIGHT_ARM, CubeListBuilder.create()
                        .texOffs(44, 44)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F)
                        .mirror(false),
                PartPose.offset(-3.0F, 20.0F, -4.0F)
        );

        return LayerDefinition.create(meshdefinition, 80, 80);
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        if (entity.getPose() != MultiversePose.HIDDEN.get()) {
            limbDistance = clamp(limbDistance, -0.45F, 0.45F);

            float pi = (float) Math.PI;
            float speed = 1.5f;
            float degree = 1.0f;

            this.lowerJaw.xRot = headPitch * (pi / 180);
            this.lowerJaw.yRot = headYaw * (pi / 180);

            this.rightLeg.xRot = Mth.cos(limbAngle * 0.7f * speed) * 1.4f * degree * limbDistance;
            this.leftLeg.xRot = Mth.cos(limbAngle * 0.7f * speed + pi) * 1.4f * degree * limbDistance;
            this.rightArm.xRot = Mth.cos(limbAngle * 0.7f * speed + pi) * 1.4f * degree * limbDistance;
            this.leftArm.xRot = Mth.cos(limbAngle * 0.7f * speed) * 1.4f * degree * limbDistance;
            this.tail.yRot = Mth.cos(limbAngle * 0.7f * speed + pi / 2) * 1.4f * degree * limbDistance;
            this.lowerJaw.zRot = Mth.cos(limbAngle * 0.35f * speed - pi / 2) * 0.5f * degree * limbDistance;

            if (entity.isAggressive() && entity.getPose() != MultiversePose.HIDDEN.get()) {
                this.upperJaw.xRot = Mth.cos(animationProgress * speed * 0.4F) * degree * -1.6F * 0.25F - 0.4F;
            } else {
                this.upperJaw.xRot = 0;
            }
        }

        //this doesn't work
        this.crop.visible = !entity.getMainHandItem().isEmpty();

        this.animate(entity.digAnimationState, GorbAnimations.DIG, animationProgress);
        this.animate(entity.hopAnimationState, GorbAnimations.JUMP, animationProgress);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

}
