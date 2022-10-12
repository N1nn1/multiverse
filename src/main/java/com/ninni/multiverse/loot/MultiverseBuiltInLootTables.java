package com.ninni.multiverse.loot;

import com.google.common.collect.Sets;
import com.ninni.multiverse.Multiverse;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class MultiverseBuiltInLootTables {
    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();

    public static final ResourceLocation RAINBOW_SHEEP_RAINBOW = MultiverseBuiltInLootTables.register("entities/rainbow_sheep/rainbow");

    private static ResourceLocation register(String string) {
        return MultiverseBuiltInLootTables.register(new ResourceLocation(Multiverse.MOD_ID, string));
    }

    private static ResourceLocation register(ResourceLocation resourceLocation) {
        if (LOCATIONS.add(resourceLocation)) {
            return resourceLocation;
        }
        throw new IllegalArgumentException(resourceLocation + " is already a registered built-in loot table");
    }

}
