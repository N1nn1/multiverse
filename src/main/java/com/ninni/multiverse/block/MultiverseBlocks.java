package com.ninni.multiverse.block;

import com.ninni.multiverse.sound.MultiverseSoundTypes;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import static com.ninni.multiverse.Multiverse.*;

public class MultiverseBlocks {

    public static final Block RAINBOW_WOOL = register("rainbow_wool", new RainbowWoolBlock(FabricBlockSettings.copyOf(Blocks.RED_WOOL)));
    public static final Block RAINBOW_CARPET = register("rainbow_carpet", new RainbowCarpetBlock(FabricBlockSettings.copyOf(Blocks.RED_CARPET)));
    public static final Block RAINBOW_BED = register("rainbow_bed", new RainbowBedBlock(FabricBlockSettings.copyOf(Blocks.RED_BED)));

    public static final Block STONE_TILES = register("stone_tiles", new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS).sounds(MultiverseSoundTypes.STONE_TILES)));
    public static final Block STONE_TILE_STAIRS = register("stone_tile_stairs", new StairBlock(STONE_TILES.defaultBlockState(), FabricBlockSettings.copyOf(STONE_TILES)));
    public static final Block STONE_TILE_SLAB = register("stone_tile_slab", new SlabBlock(FabricBlockSettings.copyOf(STONE_TILES)));
    public static final Block STONE_TILE_WALL = register("stone_tile_wall", new WallBlock(FabricBlockSettings.copyOf(STONE_TILES)));

    public static final Block DIRTY_STONE_TILES = register("dirty_stone_tiles", new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS).sounds(MultiverseSoundTypes.DIRTY_STONE_TILES)));
    public static final Block DIRTY_STONE_TILE_STAIRS = register("dirty_stone_tile_stairs", new StairBlock(DIRTY_STONE_TILES.defaultBlockState(), FabricBlockSettings.copyOf(DIRTY_STONE_TILES)));
    public static final Block DIRTY_STONE_TILE_SLAB = register("dirty_stone_tile_slab", new SlabBlock(FabricBlockSettings.copyOf(DIRTY_STONE_TILES)));

    public static final Block SANDY_STONE_TILES = register("sandy_stone_tiles", new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS).sounds(MultiverseSoundTypes.SANDY_STONE_TILES)));
    public static final Block SANDY_STONE_TILE_STAIRS = register("sandy_stone_tile_stairs", new StairBlock(SANDY_STONE_TILES.defaultBlockState(), FabricBlockSettings.copyOf(SANDY_STONE_TILES)));
    public static final Block SANDY_STONE_TILE_SLAB = register("sandy_stone_tile_slab", new SlabBlock(FabricBlockSettings.copyOf(SANDY_STONE_TILES)));

    public static final Block COBBLESTONE_GOLEM_LORE_TABLET = register("cobblestone_golem_lore_tablet", new LoreTabletBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F).noOcclusion()));
    public static final Block SPRINKLER_LORE_TABLET = register("sprinkler_lore_tablet", new LoreTabletBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F).noOcclusion()));
    public static final Block GRINDSTONE_GOLEM_LORE_TABLET = register("grindstone_golem_lore_tablet", new LoreTabletBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F).noOcclusion()));
    public static final Block PLANK_GOLEM_LORE_TABLET = register("plank_golem_lore_tablet", new LoreTabletBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(2.0F, 6.0F).noOcclusion()));

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, id), block);
    }
}
