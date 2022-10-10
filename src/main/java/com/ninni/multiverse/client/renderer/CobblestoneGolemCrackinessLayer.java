package com.ninni.multiverse.client.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.api.Crackiness;
import com.ninni.multiverse.client.models.CobblestoneGolemModel;
import com.ninni.multiverse.entities.CobblestoneGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class CobblestoneGolemCrackinessLayer extends RenderLayer<CobblestoneGolemEntity, CobblestoneGolemModel> {
    private static final Map<Crackiness, ResourceLocation> resourceLocations = Util.make(Maps.newHashMap(), map -> {
        for (Crackiness crackiness : Crackiness.values()) {
            map.put(crackiness, new ResourceLocation(Multiverse.MOD_ID, String.format(Locale.ROOT, "textures/entity/cobblestone_golem/cobblestone_golem_crackiness_%s.png", crackiness.getName())));
        }
    });

    public CobblestoneGolemCrackinessLayer(RenderLayerParent<CobblestoneGolemEntity, CobblestoneGolemModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CobblestoneGolemEntity entity, float f, float g, float h, float j, float k, float l) {
        if (entity.isInvisible()) {
            return;
        }
        Crackiness crackiness = entity.getCrackiness();
        if (crackiness == Crackiness.NONE) {
            return;
        }
        ResourceLocation resourceLocation = resourceLocations.get(crackiness);
        CobblestoneGolemCrackinessLayer.renderColoredCutoutModel(this.getParentModel(), resourceLocation, poseStack, multiBufferSource, i, entity, 1.0f, 1.0f, 1.0f);
    }

}