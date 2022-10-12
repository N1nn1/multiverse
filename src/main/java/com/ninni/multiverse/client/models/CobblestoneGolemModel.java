package com.ninni.multiverse.client.models;

import com.ninni.multiverse.client.entity.CobblestoneGolemAnimations;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.definitions.FrogAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.frog.Frog;

@Environment(EnvType.CLIENT)
public class CobblestoneGolemModel extends HierarchicalModel<CobblestoneGolemEntity> {
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public CobblestoneGolemModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.leftArm = this.body.getChild("leftArm");
        this.rightArm = this.body.getChild("rightArm");
        this.leftLeg = this.body.getChild("leftLeg");
        this.rightLeg = this.body.getChild("rightLeg");
    }

    public static LayerDefinition getLayerDefinition() {

        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();

        PartDefinition body = root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .mirror(false)
                        .addBox(-7.5F, -13.0F, -7.5F, 15.0F, 15.0F, 15.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0)
                        .mirror(false)
                        .addBox(-1.5F, -4.0F, -9.5F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftArm = body.addOrReplaceChild(
                "leftArm",
                CubeListBuilder.create()
                        .texOffs(0, 30)
                        .mirror(false)
                        .addBox(0.0F, -2.5F, -4.0F, 5.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(26, 42)
                        .mirror(false)
                        .addBox(2.0F, 10.5F, -4.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(7.5F, -6.5F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightArm = body.addOrReplaceChild(
                "rightArm",
                CubeListBuilder.create()
                        .texOffs(0, 30)
                        .mirror(true)
                        .addBox(-5.0F, -2.5F, -4.0F, 5.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(26, 42)
                        .mirror(true)
                        .addBox(-5.0F, 10.5F, -4.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-7.5F, -6.5F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftLeg = body.addOrReplaceChild(
                "leftLeg",
                CubeListBuilder.create()
                        .texOffs(27, 34)
                        .mirror(false)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightLeg = body.addOrReplaceChild(
                "rightLeg",
                CubeListBuilder.create()
                        .texOffs(27, 34)
                        .mirror(true)
                        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        return LayerDefinition.create(data, 64, 64);
    }

    @Override
    public void setupAnim(CobblestoneGolemEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(entity.walkAnimationState, CobblestoneGolemAnimations.WALK, animationProgress);
        this.animate(entity.runAnimationState, CobblestoneGolemAnimations.RUN, animationProgress);
        this.animate(entity.forwardsMiningAnimationState, CobblestoneGolemAnimations.MINE_FORWARDS, animationProgress);
        this.animate(entity.downwardsMiningAnimationState, CobblestoneGolemAnimations.MINE_DOWNWARDS, animationProgress);
        this.animate(entity.upwardsMiningwalkAnimationState, CobblestoneGolemAnimations.MINE_UPWARDS, animationProgress);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    public ModelPart getBody() {
        return this.body;
    }

}