package com.ninni.multiverse.item;


import com.ninni.multiverse.block.MultiverseBlocks;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;

import static com.ninni.multiverse.Multiverse.MOD_ID;

@SuppressWarnings("unused")
public class MultiverseItems {

    public static final Item RAINBOW_SHEEP_SPAWN_EGG = register("rainbow_sheep_spawn_egg", new SpawnEggItem(MultiverseEntityTypes.RAINBOW_SHEEP, 0xFFFFFF, 0xFFFFFF, new Item.Properties().stacksTo(64)));
    public static final Item RAINBOW_WOOL = register("rainbow_wool", new BlockItem(MultiverseBlocks.RAINBOW_WOOL, new Item.Properties()));
    public static final Item RAINBOW_CARPET = register("rainbow_carpet", new BlockItem(MultiverseBlocks.RAINBOW_CARPET, new Item.Properties()));
    public static final Item RAINBOW_BED = register("rainbow_bed", new BedItem(MultiverseBlocks.RAINBOW_BED, new Item.Properties()));
    public static final Item COLORFUL_PAINTING = register("colorful_painting", new ColorfulPaintingItem(MultiverseEntityTypes.COLORFUL_PAINTING, new Item.Properties()));

    public static final Item GORB_SPAWN_EGG = register("gorb_spawn_egg", new SpawnEggItem(MultiverseEntityTypes.GORB, 0x6C7474, 0xB4C2CB, new Item.Properties().stacksTo(64)));

    public static final Item STONE_TILES = register("stone_tiles", new BlockItem(MultiverseBlocks.STONE_TILES, new Item.Properties()));
    public static final Item STONE_TILE_STAIRS = register("stone_tile_stairs", new BlockItem(MultiverseBlocks.STONE_TILE_STAIRS, new Item.Properties()));
    public static final Item STONE_TILE_SLAB = register("stone_tile_slab", new BlockItem(MultiverseBlocks.STONE_TILE_SLAB, new Item.Properties()));
    public static final Item STONE_TILE_WALL = register("stone_tile_wall", new BlockItem(MultiverseBlocks.STONE_TILE_WALL, new Item.Properties()));

    public static final Item DIRTY_STONE_TILES = register("dirty_stone_tiles", new BlockItem(MultiverseBlocks.DIRTY_STONE_TILES, new Item.Properties()));
    public static final Item DIRTY_STONE_TILE_STAIRS = register("dirty_stone_tile_stairs", new BlockItem(MultiverseBlocks.DIRTY_STONE_TILE_STAIRS, new Item.Properties()));
    public static final Item DIRTY_STONE_TILE_SLAB = register("dirty_stone_tile_slab", new BlockItem(MultiverseBlocks.DIRTY_STONE_TILE_SLAB, new Item.Properties()));

    public static final Item SANDY_STONE_TILES = register("sandy_stone_tiles", new BlockItem(MultiverseBlocks.SANDY_STONE_TILES, new Item.Properties()));
    public static final Item SANDY_STONE_TILE_STAIRS = register("sandy_stone_tile_stairs", new BlockItem(MultiverseBlocks.SANDY_STONE_TILE_STAIRS, new Item.Properties()));
    public static final Item SANDY_STONE_TILE_SLAB = register("sandy_stone_tile_slab", new BlockItem(MultiverseBlocks.SANDY_STONE_TILE_SLAB, new Item.Properties()));

    public static final Item EXHAUSTED_COBBLESTONE_GOLEM = register("exhausted_cobblestone_golem", new ExhaustedCobblestoneGolemItem(new Item.Properties().stacksTo(1)));

    public static final Item COBBLESTONE_GOLEM_LORE_TABLET = register("cobblestone_golem_lore_tablet", new LoreTabletItem("cobblestone_golem", MultiverseBlocks.COBBLESTONE_GOLEM_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Item SPRINKLER_LORE_TABLET = register("sprinkler_lore_tablet", new LoreTabletItem("sprinkler", MultiverseBlocks.SPRINKLER_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Item GRINDSTONE_GOLEM_LORE_TABLET = register("grindstone_golem_lore_tablet", new LoreTabletItem("grindstone_golem", MultiverseBlocks.GRINDSTONE_GOLEM_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Item PLANK_GOLEM_LORE_TABLET = register("plank_golem_lore_tablet", new LoreTabletItem("plank_golem", MultiverseBlocks.PLANK_GOLEM_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));


    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, id), item);
    }
}
