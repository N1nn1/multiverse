package com.ninni.multiverse.client.models;

import com.ninni.multiverse.Multiverse;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class MultiverseModelLayers {

    public static final ModelLayerLocation COBBLESTONE_GOLEM = create("cobblestone_golem", "main");

    private static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(new ResourceLocation(Multiverse.MOD_ID, id), layer);
    }

}
