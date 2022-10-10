package com.ninni.multiverse.item;


import com.ninni.multiverse.block.MultiverseBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static com.ninni.multiverse.Multiverse.*;

@SuppressWarnings("unused")
public class MultiverseItems {

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

    public static final Item LORE_TABLET = register("lore_tablet", new LoreTabletItem(new Item.Properties().stacksTo(1).tab(TAB)));

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, id), item);
    }
}
