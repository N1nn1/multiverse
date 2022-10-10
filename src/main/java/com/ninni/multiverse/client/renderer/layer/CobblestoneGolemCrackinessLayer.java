package com.ninni.multiverse.client.renderer.layer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.api.CrackableEntity;
import com.ninni.multiverse.api.Crackiness;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.Locale;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class CobblestoneGolemCrackinessLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final Map<Crackiness, ResourceLocation> resourceLocations = Util.make(Maps.newHashMap(), map -> {
        for (Crackiness crackiness : Crackiness.values()) {
            map.put(crackiness, new ResourceLocation(Multiverse.MOD_ID, String.format(Locale.ROOT, "textures/entity/cobblestone_golem/cobblestone_golem_crackiness_%s.png", crackiness.getName())));
        }
    });

    public CobblestoneGolemCrackinessLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T entity, float f, float g, float h, float j, float k, float l) {
        if (entity instanceof CrackableEntity crackableEntity) {
            if (entity.isInvisible()) {
                return;
            }
            Crackiness crackiness = crackableEntity.getCrackiness();
            if (crackiness == Crackiness.NONE) {
                return;
            }
            ResourceLocation resourceLocation = resourceLocations.get(crackiness);
            CobblestoneGolemCrackinessLayer.renderColoredCutoutModel(this.getParentModel(), resourceLocation, poseStack, multiBufferSource, i, entity, 1.0f, 1.0f, 1.0f);
        }
    }

}
