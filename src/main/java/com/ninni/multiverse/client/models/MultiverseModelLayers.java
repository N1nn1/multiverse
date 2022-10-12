package com.ninni.multiverse.client.models;

import com.ninni.multiverse.Multiverse;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class MultiverseModelLayers {

    public static final ModelLayerLocation COBBLESTONE_GOLEM = create("cobblestone_golem", "main");
    public static final ModelLayerLocation EXHAUSTED_COBBLESTONE_GOLEM = create("exhausted_cobblestone_golem", "main");
    public static final ModelLayerLocation RAINBOW_SHEEP = create("rainbow_sheep", "main");

    private static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(new ResourceLocation(Multiverse.MOD_ID, id), layer);
    }

}
