package com.ninni.multiverse;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Multiverse implements ModInitializer {
	public static final String MOD_ID = "multiverse";
	public static final CreativeModeTab TAB = FabricItemGroupBuilder.create(new ResourceLocation(MOD_ID, MOD_ID)).icon(() -> new ItemStack(Items.COBBLESTONE)).build();

	@Override
	public void onInitialize() {
	}
}
