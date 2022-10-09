package com.ninni.multiverse;

import com.google.common.reflect.Reflection;
import com.ninni.multiverse.block.MultiverseBlocks;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import com.ninni.multiverse.item.MultiverseItems;
import com.ninni.multiverse.sound.MultiverseSoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Multiverse implements ModInitializer {
	public static final String MOD_ID = "multiverse";
	public static final CreativeModeTab TAB = FabricItemGroupBuilder.create(new ResourceLocation(MOD_ID, MOD_ID)).icon(() -> new ItemStack(MultiverseItems.STONE_TILES)).build();

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		Reflection.initialize(
			MultiverseSoundEvents.class,
			MultiverseEntityTypes.class,
			MultiverseBlocks.class,
			MultiverseItems.class
		);
	}
}
