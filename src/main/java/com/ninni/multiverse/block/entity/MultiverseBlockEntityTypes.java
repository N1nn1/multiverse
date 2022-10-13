package com.ninni.multiverse.block.entity;

import com.ninni.multiverse.block.MultiverseBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.ninni.multiverse.Multiverse.*;

public class MultiverseBlockEntityTypes {
    public static final BlockEntityType<RainbowBedBlockEntity> RAINBOW_BED = Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, "rainbow_bed"), FabricBlockEntityTypeBuilder.create(RainbowBedBlockEntity::new, MultiverseBlocks.RAINBOW_BED).build(null));
}
