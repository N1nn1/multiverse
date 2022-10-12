package com.ninni.multiverse.network;

import com.ninni.multiverse.client.gui.LoreTabletScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class OpenLoreTabletBlockScreen implements ClientPlayNetworking.PlayChannelHandler {

    @Override
    public void receive(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        BlockPos pos = buf.readBlockPos();
        Optional.ofNullable(client.player).flatMap(localPlayer -> Optional.ofNullable(client.level)).ifPresent(clientLevel -> {
            client.execute(() -> {
                ItemStack itemStack = clientLevel.getBlockState(pos).getBlock().asItem().getDefaultInstance();
                client.setScreen(new LoreTabletScreen(new LoreTabletScreen.LoreInfoAccess(itemStack)));
            });
        });
    }

}
