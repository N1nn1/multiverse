package com.ninni.multiverse.network;

import com.ninni.multiverse.client.gui.LoreTabletScreen;
import com.ninni.multiverse.item.MultiverseItems;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class OpenLoreTabletScreen implements ClientPlayNetworking.PlayChannelHandler {

    @Override
    public void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        InteractionHand hand = buf.readEnum(InteractionHand.class);
        Optional.ofNullable(client.player).ifPresent(localPlayer -> {
            client.execute(() -> {
                ItemStack itemStack = client.player.getItemInHand(hand);
                if (itemStack.is(MultiverseItems.LORE_TABLET)) {
                    client.setScreen(new LoreTabletScreen(new LoreTabletScreen.LoreInfoAccess(itemStack)));
                }
            });
        });
    }

}
