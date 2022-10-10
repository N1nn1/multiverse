package com.ninni.multiverse.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ninni.multiverse.client.models.CobblestoneGolemModel;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemStack;

@Environment(value= EnvType.CLIENT)
public class CobblestoneMiningBlockLayer<T extends CobblestoneGolemEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ItemInHandRenderer itemRenderer;

    public CobblestoneMiningBlockLayer(RenderLayerParent<T, M> renderLayerParent, ItemInHandRenderer itemInHandRenderer) {
        super(renderLayerParent);
        this.itemRenderer = itemInHandRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T golem, float f, float g, float h, float j, float k, float l) {
        if (golem.getMiningBlock() != null) {
            poseStack.pushPose();
            poseStack.translate(((CobblestoneGolemModel) this.getParentModel()).getBody().x / 16f, ((CobblestoneGolemModel) this.getParentModel()).getBody().y / 16f, ((CobblestoneGolemModel) this.getParentModel()).getBody().z / 16f);
            poseStack.scale(1.5f, 1.5f, 1.5f);
            poseStack.mulPose(Vector3f.ZP.rotation(((CobblestoneGolemModel) this.getParentModel()).getBody().zRot));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
            poseStack.translate(0f, 0.125f, 0.3f);

            ItemStack itemStack = golem.getMiningBlock().getBlock().asItem().getDefaultInstance();
            this.itemRenderer.renderItem(golem, itemStack, ItemTransforms.TransformType.GROUND, false, poseStack, multiBufferSource, i);
            poseStack.popPose();
        }
    }
}

