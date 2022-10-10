package com.ninni.multiverse.network;

import com.ninni.multiverse.Multiverse;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class MultiverseNetwork {
    public static final ResourceLocation OPEN_LORE_TABLET_SCREEN = new ResourceLocation(Multiverse.MOD_ID, "open_lore_tablet_screen");

    @Environment(EnvType.CLIENT)
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(OPEN_LORE_TABLET_SCREEN, new OpenLoreTabletScreen());
    }

}
