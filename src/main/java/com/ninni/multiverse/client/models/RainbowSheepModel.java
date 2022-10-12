package com.ninni.multiverse.client.models;

import com.google.common.collect.ImmutableList;
import com.ninni.multiverse.entities.MultiversePose;
import com.ninni.multiverse.entities.RainbowSheep;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;


public class RainbowSheepModel<T extends RainbowSheep> extends QuadrupedModel<T> {
    private float headXRot;
    
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart bodyWool;
    private final ModelPart head;
    private final ModelPart headWool;
    private final ModelPart rightHindLeg;
    private final ModelPart rightHindLegWool;
    private final ModelPart leftHindLeg;
    private final ModelPart leftHindLegWool;
    private final ModelPart rightFrontLeg;
    private final ModelPart rightFrontLegWool;
    private final ModelPart leftFrontLeg;
    private final ModelPart leftFrontLegWool;

    public RainbowSheepModel(ModelPart root) {
        super(root, false, 8.0f, 4.0f, 2.0f, 2.0f, 24);
        this.root = root;
        
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
        
        this.bodyWool = body.getChild("body_wool");
        this.headWool = head.getChild("head_wool");
        this.leftFrontLegWool = leftFrontLeg.getChild("left_front_leg_wool");
        this.rightFrontLegWool = rightFrontLeg.getChild("right_front_leg_wool");
        this.leftHindLegWool = leftHindLeg.getChild("left_hind_leg_wool");
        this.rightHindLegWool = rightHindLeg.getChild("right_hind_leg_wool");

    }

    public static LayerDefinition getLayerDefinition() {

        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();

        PartDefinition body = root.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                            .texOffs(36, 0)
                            .addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F),
            PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5708F, 0.0F, 0.0F)
        );

        PartDefinition bodyWool = body.addOrReplaceChild(
            "body_wool",
            CubeListBuilder.create()
                            .texOffs(36, 32)
                            .addBox(-4.0F, -29.0F, -5.0F, 8.0F, 16.0F, 6.0F, new CubeDeformation(1.75F)),
            PartPose.offsetAndRotation(0.0F, 19.0F, -2.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition head = root.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                           .texOffs(0, 0)
                           .mirror(false)
                           .addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F),
            PartPose.offsetAndRotation(0.0F, 6.0F, -8.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition headWool = head.addOrReplaceChild(
            "head_wool",
            CubeListBuilder.create()
                            .texOffs(0, 32)
                            .addBox(-3.0F, -22.0F, -12.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.6F)),
            PartPose.offsetAndRotation(0.0F, 18.0F, 8.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightHindLeg = root.addOrReplaceChild(
            "right_hind_leg",
            CubeListBuilder.create()
                            .texOffs(0, 16)
                            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
            PartPose.offsetAndRotation(-3.0F, 12.0F, 7.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightHindLegWool = rightHindLeg.addOrReplaceChild(
            "right_hind_leg_wool",
            CubeListBuilder.create()
                            .texOffs(0, 48)
                            .addBox(-5.0F, -12.0F, 5.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)),
            PartPose.offsetAndRotation(3.0F, 12.0F, -7.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftHindLeg = root.addOrReplaceChild(
            "left_hind_leg",
            CubeListBuilder.create()
                            .texOffs(0, 16)
                            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
            PartPose.offsetAndRotation(3.0F, 12.0F, 7.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftHindLegWool = leftHindLeg.addOrReplaceChild(
            "left_hind_leg_wool",
            CubeListBuilder.create()
                            .texOffs(0, 48)
                            .addBox(1.0F, -12.0F, 5.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)),
            PartPose.offsetAndRotation(-3.0F, 12.0F, -7.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightFrontLeg = root.addOrReplaceChild(
            "right_front_leg",
            CubeListBuilder.create()
                            .texOffs(16, 16)
                            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
            PartPose.offsetAndRotation(-3.0F, 12.0F, -5.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightFrontLegWool = rightFrontLeg.addOrReplaceChild(
            "right_front_leg_wool",
            CubeListBuilder.create()
                            .texOffs(16, 48)
                            .addBox(-5.0F, -12.0F, -7.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)),
            PartPose.offsetAndRotation(3.0F, 12.0F, 5.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftFrontLeg = root.addOrReplaceChild(
            "left_front_leg",
            CubeListBuilder.create()
                            .texOffs(16, 16)
                            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
            PartPose.offsetAndRotation(3.0F, 12.0F, -5.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftFrontLegWool = leftFrontLeg.addOrReplaceChild(
            "left_front_leg_wool",
            CubeListBuilder.create()
                            .texOffs(16, 48)
                            .addBox(1.0F, -12.0F, -7.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F)),
            PartPose.offsetAndRotation(-3.0F, 12.0F, 5.0F, 0.0F, 0.0F, 0.0F)
        );

        return LayerDefinition.create(data, 64, 64);
    }
    
    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(
            this.body,
            this.leftFrontLeg,
            this.leftHindLeg,
            this.rightHindLeg,
            this.rightFrontLeg
        );
    }

    @Override
    public void prepareMobModel(T sheep, float f, float g, float h) {
        super.prepareMobModel(sheep, f, g, h);
        this.head.y = 6.0f + (sheep).getHeadEatPositionScale(h) * 9.0f;
        this.headXRot = (sheep).getHeadEatAngleScale(h);
    }

    @Override
    public void setupAnim(T sheep, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float pi = (float)Math.PI;
        this.head.xRot = this.headXRot;
        this.head.yRot = headPitch * ((float)Math.PI / 180);

        if (sheep.getPose().equals(MultiversePose.HOP.get())) {
            float hopSpeed = 1.25F;
            float hopDegree = 0.4F;
            float hopStrength = 1;

            this.head.xRot += Mth.cos(limbAngle * 0.6f * hopSpeed + 0.7f) * 0.25F * limbDistance * hopDegree;
            this.head.y = Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 3 * limbDistance * hopStrength + 6.0F - 1F;
            this.body.xRot = Mth.cos(limbAngle * 0.6f * hopSpeed) * 0.25F * limbDistance * hopDegree + pi/2;
            this.body.y = Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 3 * limbDistance * hopDegree + 5.0F;
            this.body.y += Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 3 * limbDistance * hopStrength - 1F;
            this.leftFrontLeg.xRot = Mth.cos(limbAngle * 0.6f * hopSpeed) * 3 * limbDistance * hopDegree - pi/6;
            this.leftFrontLeg.y = Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 1.5F * limbDistance * hopDegree + 11.5F;
            this.leftFrontLeg.y += Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 3 * limbDistance * hopStrength - 1F;
            this.rightFrontLeg.xRot = Mth.cos(limbAngle * 0.6f * hopSpeed + 0.7f) * 3 * limbDistance * hopDegree - pi/6;
            this.rightFrontLeg.y = Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2 + 0.7f) * 1.5F * limbDistance * hopDegree + 11.5F;
            this.rightFrontLeg.y += Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 3 * limbDistance * hopStrength - 1F;
            this.rightHindLeg.xRot = Mth.cos(limbAngle * 0.6f * hopSpeed + pi) * 3 * limbDistance * hopDegree + pi/6;
            this.rightHindLeg.y = Mth.cos(limbAngle * 0.6f * hopSpeed - pi/2) * 1.5F * limbDistance * hopDegree + 11.5F;
            this.rightHindLeg.y += Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 3 * limbDistance * hopStrength - 1F;
            this.leftHindLeg.xRot = Mth.cos(limbAngle * 0.6f * hopSpeed + 0.7f + pi) * 3 * limbDistance * hopDegree + pi/6;
            this.leftHindLeg.y = Mth.cos(limbAngle * 0.6f * hopSpeed - pi/2 + 0.7f) * 1.5F * limbDistance * hopDegree + 11.5F;
            this.leftHindLeg.y += Mth.cos(limbAngle * 0.6f * hopSpeed + pi/2) * 3 * limbDistance * hopStrength - 1F;
        } else {
            this.body.xRot = pi/2;
            this.body.y = 5.0F;
            this.rightHindLeg.xRot = Mth.cos(limbAngle * 0.6662f) * 1.4f * limbDistance;
            this.rightHindLeg.y = 12F;
            this.leftHindLeg.xRot = Mth.cos(limbAngle * 0.6662f + pi) * 1.4f * limbDistance;
            this.leftHindLeg.y = 12F;
            this.rightFrontLeg.xRot = Mth.cos(limbAngle * 0.6662f + pi) * 1.4f * limbDistance;
            this.rightFrontLeg.y = 12F;
            this.leftFrontLeg.xRot = Mth.cos(limbAngle * 0.6662f) * 1.4f * limbDistance;
            this.leftFrontLeg.y = 12F;
        }

        if (sheep.isSheared()) {
            this.bodyWool.visible = false;
            this.headWool.visible = false;
            this.rightHindLegWool.visible = false;
            this.leftHindLegWool.visible = false;
            this.rightFrontLegWool.visible = false;
            this.leftFrontLegWool.visible = false;
        } else {
            this.bodyWool.visible = true;
            this.headWool.visible = true;
            this.rightHindLegWool.visible = true;
            this.leftHindLegWool.visible = true;
            this.rightFrontLegWool.visible = true;
            this.leftFrontLegWool.visible = true;
        }
    }
}
