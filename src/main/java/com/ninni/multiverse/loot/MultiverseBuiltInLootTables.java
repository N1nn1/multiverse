package com.ninni.multiverse.loot;

import com.ninni.multiverse.Multiverse;
import net.minecraft.resources.ResourceLocation;

public class MultiverseBuiltInLootTables {

    public static final ResourceLocation RAINBOW_SHEEP_RAINBOW = MultiverseBuiltInLootTables.register("entities/rainbow_sheep/rainbow");

    private static ResourceLocation register(String string) {
        return new ResourceLocation(Multiverse.MOD_ID, string);
    }

}
