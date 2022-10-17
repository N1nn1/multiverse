package com.ninni.multiverse.item;


import com.ninni.multiverse.block.MultiverseBlocks;
import com.ninni.multiverse.entities.MultiverseEntityTypes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

import static com.ninni.multiverse.Multiverse.*;

@SuppressWarnings("unused")
public class MultiverseItems {

    public static final Item RAINBOW_WOOL = register("rainbow_wool", new BlockItem(MultiverseBlocks.RAINBOW_WOOL, new FabricItemSettings().group(TAB)));
    public static final Item RAINBOW_CARPET = register("rainbow_carpet", new BlockItem(MultiverseBlocks.RAINBOW_CARPET, new FabricItemSettings().group(TAB)));
    public static final Item RAINBOW_BED = register("rainbow_bed", new BedItem(MultiverseBlocks.RAINBOW_BED, new FabricItemSettings().group(TAB)));

    public static final Item STONE_TILES = register("stone_tiles", new BlockItem(MultiverseBlocks.STONE_TILES, new FabricItemSettings().group(TAB)));
    public static final Item STONE_TILE_STAIRS = register("stone_tile_stairs", new BlockItem(MultiverseBlocks.STONE_TILE_STAIRS, new FabricItemSettings().group(TAB)));
    public static final Item STONE_TILE_SLAB = register("stone_tile_slab", new BlockItem(MultiverseBlocks.STONE_TILE_SLAB, new FabricItemSettings().group(TAB)));
    public static final Item STONE_TILE_WALL = register("stone_tile_wall", new BlockItem(MultiverseBlocks.STONE_TILE_WALL, new FabricItemSettings().group(TAB)));

    public static final Item DIRTY_STONE_TILES = register("dirty_stone_tiles", new BlockItem(MultiverseBlocks.DIRTY_STONE_TILES, new FabricItemSettings().group(TAB)));
    public static final Item DIRTY_STONE_TILE_STAIRS = register("dirty_stone_tile_stairs", new BlockItem(MultiverseBlocks.DIRTY_STONE_TILE_STAIRS, new FabricItemSettings().group(TAB)));
    public static final Item DIRTY_STONE_TILE_SLAB = register("dirty_stone_tile_slab", new BlockItem(MultiverseBlocks.DIRTY_STONE_TILE_SLAB, new FabricItemSettings().group(TAB)));

    public static final Item SANDY_STONE_TILES = register("sandy_stone_tiles", new BlockItem(MultiverseBlocks.SANDY_STONE_TILES, new FabricItemSettings().group(TAB)));
    public static final Item SANDY_STONE_TILE_STAIRS = register("sandy_stone_tile_stairs", new BlockItem(MultiverseBlocks.SANDY_STONE_TILE_STAIRS, new FabricItemSettings().group(TAB)));
    public static final Item SANDY_STONE_TILE_SLAB = register("sandy_stone_tile_slab", new BlockItem(MultiverseBlocks.SANDY_STONE_TILE_SLAB, new FabricItemSettings().group(TAB)));

    public static final Item EXHAUSTED_COBBLESTONE_GOLEM = register("exhausted_cobblestone_golem", new ExhaustedCobblestoneGolemItem(new Item.Properties().stacksTo(1).tab(TAB)));

    public static final Item COBBLESTONE_GOLEM_LORE_TABLET = register("cobblestone_golem_lore_tablet", new LoreTabletItem("cobblestone_golem", MultiverseBlocks.COBBLESTONE_GOLEM_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).tab(TAB)));
    public static final Item SPRINKLER_LORE_TABLET = register("sprinkler_lore_tablet", new LoreTabletItem("sprinkler", MultiverseBlocks.SPRINKLER_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).tab(TAB)));
    public static final Item GRINDSTONE_GOLEM_LORE_TABLET = register("grindstone_golem_lore_tablet", new LoreTabletItem("grindstone_golem", MultiverseBlocks.GRINDSTONE_GOLEM_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).tab(TAB)));
    public static final Item PLANK_GOLEM_LORE_TABLET = register("plank_golem_lore_tablet", new LoreTabletItem("plank_golem", MultiverseBlocks.PLANK_GOLEM_LORE_TABLET, new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).tab(TAB)));

    public static final Item COLORFUL_PAINTING = register("colorful_painting", new ColorfulPaintingItem(MultiverseEntityTypes.COLORFUL_PAINTING, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, id), item);
    }
}
