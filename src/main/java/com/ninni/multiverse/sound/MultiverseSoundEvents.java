package com.ninni.multiverse.sound;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static com.ninni.multiverse.Multiverse.*;

public class MultiverseSoundEvents {

    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_AMBIENT = register("entity.cobblestone_golem.ambient");
    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_DEATH = register("entity.cobblestone_golem.death");
    public static final SoundEvent ENTITY_COBBLESTONE_GOLEM_HURT = register("entity.cobblestone_golem.hurt");

    public static final SoundEvent ENTITY_RAINBOW_SHEEP_AMBIENT = register("entity.rainbow_sheep.ambient");
    public static final SoundEvent ENTITY_RAINBOW_SHEEP_SCARED = register("entity.rainbow_sheep.scared");
    public static final SoundEvent ENTITY_RAINBOW_SHEEP_DEATH = register("entity.rainbow_sheep.death");
    public static final SoundEvent ENTITY_RAINBOW_SHEEP_HURT = register("entity.rainbow_sheep.hurt");

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
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
