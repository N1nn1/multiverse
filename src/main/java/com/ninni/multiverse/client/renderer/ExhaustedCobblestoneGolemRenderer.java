package com.ninni.multiverse.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.client.models.ExhaustedCobblestoneGolemModel;
import com.ninni.multiverse.client.models.MultiverseModelLayers;
import com.ninni.multiverse.client.renderer.layer.CobblestoneGolemCrackinessLayer;
import com.ninni.multiverse.entities.ExhaustedCobblestoneGolem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

@Environment(EnvType.CLIENT)
public class ExhaustedCobblestoneGolemRenderer extends LivingEntityRenderer<ExhaustedCobblestoneGolem, ExhaustedCobblestoneGolemModel> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Multiverse.MOD_ID, "textures/entity/cobblestone_golem/exhausted_cobblestone_golem.png");

    public ExhaustedCobblestoneGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new ExhaustedCobblestoneGolemModel(context.bakeLayer(MultiverseModelLayers.EXHAUSTED_COBBLESTONE_GOLEM)), 0.5F);
        this.addLayer(new CobblestoneGolemCrackinessLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ExhaustedCobblestoneGolem entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(ExhaustedCobblestoneGolem entity, PoseStack poseStack, float f, float g, float h) {
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f - g));
        float i = (float)(entity.level.getGameTime() - entity.lastHit) + h;
        if (i < 5.0f) {
            poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.sin(i / 1.5f * (float)Math.PI) * 3.0f));
        }
    }

    @Override
    protected boolean shouldShowName(ExhaustedCobblestoneGolem entity) {
        float f = entity.isCrouching() ? 32.0f : 64.0f;
        double d = this.entityRenderDispatcher.distanceToSqr(entity);
        if (d >= (double)(f * f)) {
            return false;
        }
        return super.shouldShowName(entity) && (entity.shouldShowName() || entity.hasCustomName() && entity == this.entityRenderDispatcher.crosshairPickEntity);
    }

}
