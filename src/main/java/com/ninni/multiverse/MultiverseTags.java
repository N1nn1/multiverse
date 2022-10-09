package com.ninni.multiverse;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class MultiverseTags {

    TagKey<Item> STONE_TILES_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("stone_tiles"));

    TagKey<Block> STONE_TILES_BLOCK = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("stone_tiles"));
}
