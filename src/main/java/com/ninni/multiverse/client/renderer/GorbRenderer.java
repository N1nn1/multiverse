package com.ninni.multiverse.client.renderer;

import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.client.models.GorbModel;
import com.ninni.multiverse.client.models.MultiverseModelLayers;
import com.ninni.multiverse.entities.Gorb;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GorbRenderer extends MobRenderer<Gorb, GorbModel<Gorb>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Multiverse.MOD_ID, "textures/entity/gorb/gorb.png");

    public GorbRenderer(EntityRendererProvider.Context context) {
        super(context, new GorbModel<>(context.bakeLayer(MultiverseModelLayers.GORB)),0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(Gorb entity) {
        return TEXTURE;
    }
}
