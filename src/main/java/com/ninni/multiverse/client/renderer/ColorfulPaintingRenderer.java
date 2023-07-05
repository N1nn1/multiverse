package com.ninni.multiverse.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.mixin.accessors.PaintingRendererAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.resources.PaintingTextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class ColorfulPaintingRenderer extends PaintingRenderer {
    public static final ResourceLocation BACK_TEXTURE_ATLAS_LOCATION = new ResourceLocation(Multiverse.MOD_ID, "multiverse_back");

    public ColorfulPaintingRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(Painting painting, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - f));
        poseStack.scale(0.0625f, 0.0625f, 0.0625f);
        PaintingVariant paintingVariant = painting.getVariant().value();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entitySolid(this.getTextureLocation(painting)));
        PaintingTextureManager paintingTextureManager = Minecraft.getInstance().getPaintingTextures();
        ((PaintingRendererAccessor)this).callRenderPainting(poseStack, vertexConsumer, painting, paintingVariant.getWidth(), paintingVariant.getHeight(), paintingTextureManager.get(paintingVariant), paintingTextureManager.getBackSprite());
        poseStack.popPose();
        super.render(painting, f, g, poseStack, multiBufferSource, i);
    }

}
