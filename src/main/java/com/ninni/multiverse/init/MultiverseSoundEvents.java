package com.ninni.multiverse.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static com.ninni.multiverse.Multiverse.MOD_ID;

public class MultiverseSoundEvents {

    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_AMBIENT = register("entity.cobblestone_golem.ambient");
    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_DEATH = register("entity.cobblestone_golem.death");
    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_HURT = register("entity.cobblestone_golem.hurt");

    public static final SoundEvent ENTITY_RAINBOW_SHEEP_AMBIENT = register("entity.rainbow_sheep.ambient");
    public static final SoundEvent ENTITY_RAINBOW_SHEEP_SCARED = register("entity.rainbow_sheep.scared");
    public static final SoundEvent ENTITY_RAINBOW_SHEEP_DRINK = register("entity.rainbow_sheep.drink");
    public static final SoundEvent ENTITY_RAINBOW_SHEEP_DEATH = register("entity.rainbow_sheep.death");
    public static final SoundEvent ENTITY_RAINBOW_SHEEP_HURT = register("entity.rainbow_sheep.hurt");

    public static final SoundEvent BLOCK_RAINBOW_WOOL_BREAK = register("block.rainbow_wool.break");
    public static final SoundEvent BLOCK_RAINBOW_WOOL_STEP  = register("block.rainbow_wool.step");
    public static final SoundEvent BLOCK_RAINBOW_WOOL_PLACE = register("block.rainbow_wool.place");
    public static final SoundEvent BLOCK_RAINBOW_WOOL_HIT   = register("block.rainbow_wool.hit");
    public static final SoundEvent BLOCK_RAINBOW_WOOL_FALL  = register("block.rainbow_wool.fall");

    public static final SoundEvent BLOCK_RAINBOW_BED_BREAK = register("block.rainbow_bed.break");
    public static final SoundEvent BLOCK_RAINBOW_BED_STEP  = register("block.rainbow_bed.step");
    public static final SoundEvent BLOCK_RAINBOW_BED_PLACE = register("block.rainbow_bed.place");
    public static final SoundEvent BLOCK_RAINBOW_BED_HIT   = register("block.rainbow_bed.hit");
    public static final SoundEvent BLOCK_RAINBOW_BED_FALL  = register("block.rainbow_bed.fall");

    public static final SoundEvent BLOCK_COLORFUL_PAINTING_BREAK = register("block.colorful_painting.break");
    public static final SoundEvent BLOCK_COLORFUL_PAINTING_PLACE = register("block.colorful_painting.place");

    public static final SoundEvent BLOCK_STONE_TILES_BREAK = register("block.stone_tiles.break");
    public static final SoundEvent BLOCK_STONE_TILES_STEP  = register("block.stone_tiles.step");
    public static final SoundEvent BLOCK_STONE_TILES_PLACE = register("block.stone_tiles.place");
    public static final SoundEvent BLOCK_STONE_TILES_HIT   = register("block.stone_tiles.hit");
    public static final SoundEvent BLOCK_STONE_TILES_FALL  = register("block.stone_tiles.fall");

    public static final SoundEvent BLOCK_DIRTY_STONE_TILES_BREAK = register("block.dirty_stone_tiles.break");
    public static final SoundEvent BLOCK_DIRTY_STONE_TILES_STEP  = register("block.dirty_stone_tiles.step");
    public static final SoundEvent BLOCK_DIRTY_STONE_TILES_PLACE = register("block.dirty_stone_tiles.place");
    public static final SoundEvent BLOCK_DIRTY_STONE_TILES_HIT   = register("block.dirty_stone_tiles.hit");
    public static final SoundEvent BLOCK_DIRTY_STONE_TILES_FALL  = register("block.dirty_stone_tiles.fall");

    public static final SoundEvent BLOCK_SANDY_STONE_TILES_BREAK = register("block.sandy_stone_tiles.break");
    public static final SoundEvent BLOCK_SANDY_STONE_TILES_STEP  = register("block.sandy_stone_tiles.step");
    public static final SoundEvent BLOCK_SANDY_STONE_TILES_PLACE = register("block.sandy_stone_tiles.place");
    public static final SoundEvent BLOCK_SANDY_STONE_TILES_HIT   = register("block.sandy_stone_tiles.hit");
    public static final SoundEvent BLOCK_SANDY_STONE_TILES_FALL  = register("block.sandy_stone_tiles.fall");

    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}
