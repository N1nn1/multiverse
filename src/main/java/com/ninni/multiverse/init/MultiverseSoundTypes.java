package com.ninni.multiverse.init;

import com.ninni.multiverse.init.MultiverseSoundEvents;
import net.minecraft.world.level.block.SoundType;

public interface MultiverseSoundTypes {

    SoundType RAINBOW_WOOL = new SoundType(
            1.0F, 1.0F,

            MultiverseSoundEvents.BLOCK_RAINBOW_WOOL_BREAK,
            MultiverseSoundEvents.BLOCK_RAINBOW_WOOL_STEP,
            MultiverseSoundEvents.BLOCK_RAINBOW_WOOL_PLACE,
            MultiverseSoundEvents.BLOCK_RAINBOW_WOOL_HIT,
            MultiverseSoundEvents.BLOCK_RAINBOW_WOOL_FALL
    );

    SoundType RAINBOW_BED = new SoundType(
            1.0F, 1.0F,

            MultiverseSoundEvents.BLOCK_RAINBOW_BED_BREAK,
            MultiverseSoundEvents.BLOCK_RAINBOW_BED_STEP,
            MultiverseSoundEvents.BLOCK_RAINBOW_BED_PLACE,
            MultiverseSoundEvents.BLOCK_RAINBOW_BED_HIT,
            MultiverseSoundEvents.BLOCK_RAINBOW_BED_FALL
    );

    SoundType STONE_TILES = new SoundType(
        1.0F, 1.0F,

        MultiverseSoundEvents.BLOCK_STONE_TILES_BREAK,
        MultiverseSoundEvents.BLOCK_STONE_TILES_STEP,
        MultiverseSoundEvents.BLOCK_STONE_TILES_PLACE,
        MultiverseSoundEvents.BLOCK_STONE_TILES_HIT,
        MultiverseSoundEvents.BLOCK_STONE_TILES_FALL
    );

    SoundType DIRTY_STONE_TILES = new SoundType(
        1.0F, 1.0F,

        MultiverseSoundEvents.BLOCK_DIRTY_STONE_TILES_BREAK,
        MultiverseSoundEvents.BLOCK_DIRTY_STONE_TILES_STEP,
        MultiverseSoundEvents.BLOCK_DIRTY_STONE_TILES_PLACE,
        MultiverseSoundEvents.BLOCK_DIRTY_STONE_TILES_HIT,
        MultiverseSoundEvents.BLOCK_DIRTY_STONE_TILES_FALL
    );

    SoundType SANDY_STONE_TILES = new SoundType(
        1.0F, 1.0F,

        MultiverseSoundEvents.BLOCK_SANDY_STONE_TILES_BREAK,
        MultiverseSoundEvents.BLOCK_SANDY_STONE_TILES_STEP,
        MultiverseSoundEvents.BLOCK_SANDY_STONE_TILES_PLACE,
        MultiverseSoundEvents.BLOCK_SANDY_STONE_TILES_HIT,
        MultiverseSoundEvents.BLOCK_SANDY_STONE_TILES_FALL
    );
}
