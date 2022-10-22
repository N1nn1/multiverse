package com.ninni.multiverse;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class MultiverseTags {

    public static final TagKey<Item> STONE_TILES_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Multiverse.MOD_ID, "stone_tiles"));
    public static final TagKey<Item> LORE_TABLETS_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Multiverse.MOD_ID, "lore_tablets"));
    public static final TagKey<Item> RAINBOW_SHEEP_LOVED = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Multiverse.MOD_ID, "rainbow_sheep_loved"));
    public static final TagKey<Item> RAINBOW_SHEEP_BYPASSES = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Multiverse.MOD_ID, "rainbow_sheep_bypasses"));

    public static final TagKey<Block> STONE_TILES_BLOCK = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Multiverse.MOD_ID,"stone_tiles"));
    public static final TagKey<Block> LORE_TABLETS_BLOCK = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Multiverse.MOD_ID, "lore_tablets"));
    public static final TagKey<Block> COBBLESTONE_GOLEM_BREAKABLES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Multiverse.MOD_ID,"cobblestone_golem_breakables"));

    public static final TagKey<Biome> RAINBOW_SHEEP_SPAWNS = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Multiverse.MOD_ID, "rainbow_sheep_spawns"));

    public static final TagKey<PaintingVariant> COLORFUL_PLACEABLE = TagKey.create(Registry.PAINTING_VARIANT_REGISTRY, new ResourceLocation(Multiverse.MOD_ID, "colorful_placeable"));

}
