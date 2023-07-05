package com.ninni.multiverse.init;

import com.ninni.multiverse.Multiverse;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

public class MultiverseCreativeModeTabs {

    public static final CreativeModeTab ITEM_GROUP = register("item_group", FabricItemGroup.builder().icon(MultiverseItems.COLORFUL_PAINTING::getDefaultInstance).title(Component.translatable("itemGroup.multiverse.multiverse")).displayItems((featureFlagSet, output) -> {
        for (Item item : BuiltInRegistries.ITEM.stream().filter(block -> Objects.equals(block.builtInRegistryHolder().unwrapKey().get().location().getNamespace(), Multiverse.MOD_ID)).toList()) {
            output.accept(item);
        }
    }).build());

    private static CreativeModeTab register(String id, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Multiverse.MOD_ID, id), tab);
    }

}
