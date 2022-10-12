package com.ninni.multiverse.client.renderer;

import com.ninni.multiverse.Multiverse;
import com.ninni.multiverse.client.models.MultiverseModelLayers;
import com.ninni.multiverse.client.models.RainbowSheepModel;
import com.ninni.multiverse.entities.RainbowSheep;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(value= EnvType.CLIENT)
public class RainbowSheepRenderer extends MobRenderer<RainbowSheep, RainbowSheepModel<RainbowSheep>> {
    private static final ResourceLocation RAINBOW_SHEEP_LOCATION = new ResourceLocation(Multiverse.MOD_ID, "textures/entity/rainbow_sheep/rainbow_sheep.png");

    public RainbowSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new RainbowSheepModel<>(context.bakeLayer(MultiverseModelLayers.RAINBOW_SHEEP)), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(RainbowSheep sheep) {
        return RAINBOW_SHEEP_LOCATION;
    }
}

