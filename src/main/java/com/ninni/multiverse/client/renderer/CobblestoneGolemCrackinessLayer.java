package com.ninni.multiverse.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.client.models.CobblestoneGolemModel;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.IronGolemCrackinessLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class CobblestoneGolemCrackinessLayer extends RenderLayer<CobblestoneGolemEntity, CobblestoneGolemModel> {
    private static final Map<CobblestoneGolemEntity.Crackiness, ResourceLocation> resourceLocations = ImmutableMap.of(CobblestoneGolemEntity.Crackiness.LOW, new ResourceLocation(Multiverse.MOD_ID, "textures/entity/cobblestone_golem/cobblestone_golem_crackiness_low.png"), CobblestoneGolemEntity.Crackiness.MEDIUM, new ResourceLocation(Multiverse.MOD_ID, "textures/entity/cobblestone_golem/cobblestone_golem_crackiness_medium.png"), CobblestoneGolemEntity.Crackiness.HIGH, new ResourceLocation(Multiverse.MOD_ID, "textures/entity/cobblestone_golem/cobblestone_golem_crackiness_high.png"));

    public CobblestoneGolemCrackinessLayer(RenderLayerParent<CobblestoneGolemEntity, CobblestoneGolemModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CobblestoneGolemEntity entity, float f, float g, float h, float j, float k, float l) {
        if (entity.isInvisible()) {
            return;
        }
        CobblestoneGolemEntity.Crackiness crackiness = entity.getCrackiness();
        if (crackiness == CobblestoneGolemEntity.Crackiness.NONE) {
            return;
        }
        ResourceLocation resourceLocation = resourceLocations.get(crackiness);
        CobblestoneGolemCrackinessLayer.renderColoredCutoutModel(this.getParentModel(), resourceLocation, poseStack, multiBufferSource, i, entity, 1.0f, 1.0f, 1.0f);
    }

}
