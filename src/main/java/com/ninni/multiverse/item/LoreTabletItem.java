package com.ninni.multiverse.item;

import com.ninni.multiverse.network.MultiverseNetwork;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class LoreTabletItem extends Item {
    private final String name;

    public LoreTabletItem(String name, Properties properties) {
        super(properties);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (player instanceof ServerPlayer serverPlayer) {
            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeEnum(interactionHand);
            ServerPlayNetworking.send(serverPlayer, MultiverseNetwork.OPEN_LORE_TABLET_SCREEN, buf);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        Optional.ofNullable(this.name).ifPresent(s -> list.add(Component.translatable("entity.multiverse." + s).withStyle(ChatFormatting.GRAY)));
    }

    @Override
    public String getDescriptionId(ItemStack itemStack) {
        return "item.multiverse.lore_tablet";
    }
}
