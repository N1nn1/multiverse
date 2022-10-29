package com.ninni.multiverse.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ninni.multiverse.entities.Gorb;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

import static net.minecraft.client.model.geom.PartNames.*;

@SuppressWarnings("FieldCanBeLocal, unused")
public class GorbModel<T extends Gorb> extends EntityModel<T> {
    public static final String LOWER_JAW = "lower_jaw";
    public static final String UPPER_JAW = "upper_jaw";
    public static final String CROP = "crop";

    private final ModelPart root;

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

        this.body = root.getChild(BODY);
        this.lowerJaw = root.getChild(LOWER_JAW);
        this.leftArm = root.getChild(LEFT_ARM);
        this.leftLeg = root.getChild(LEFT_LEG);
        this.rightLeg = root.getChild(RIGHT_LEG);
        this.rightArm = root.getChild(RIGHT_ARM);

        this.tail = body.getChild(TAIL);

        this.upperJaw = lowerJaw.getChild(UPPER_JAW);
        this.crop = lowerJaw.getChild(CROP);

    }

    public static LayerDefinition getLayerDefinition() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild(
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

        PartDefinition lowerJaw = partdefinition.addOrReplaceChild(
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

        PartDefinition leftArm = partdefinition.addOrReplaceChild(
                LEFT_ARM, CubeListBuilder.create()
                        .texOffs(32, 40)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(3.0F, 20.0F, -4.0F)
        );

        PartDefinition leftLeg = partdefinition.addOrReplaceChild(
                LEFT_LEG, CubeListBuilder.create()
                        .texOffs(44, 44)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(3.0F, 20.0F, 4.0F)
        );

        PartDefinition rightLeg = partdefinition.addOrReplaceChild(
                RIGHT_LEG, CubeListBuilder.create()
                        .texOffs(32, 40)
                        .mirror()
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F)
                        .mirror(false),
                PartPose.offset(-3.0F, 20.0F, 4.0F)
        );

        PartDefinition rightArm = partdefinition.addOrReplaceChild(
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
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float pi = (float)Math.PI;
        this.lowerJaw.xRot = headPitch * (pi / 180);
        this.lowerJaw.yRot = netHeadYaw * (pi / 180);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

}
