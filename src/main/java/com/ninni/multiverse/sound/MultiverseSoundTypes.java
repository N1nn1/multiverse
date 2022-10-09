package com.ninni.multiverse.sound;

import net.minecraft.world.level.block.SoundType;

public interface MultiverseSoundTypes {

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
