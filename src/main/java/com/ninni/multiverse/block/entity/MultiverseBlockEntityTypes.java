package com.ninni.multiverse.block.entity;

import com.ninni.multiverse.init.MultiverseBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.ninni.multiverse.Multiverse.MOD_ID;

public class MultiverseBlockEntityTypes {
    public static final BlockEntityType<RainbowBedBlockEntity> RAINBOW_BED = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, "rainbow_bed"), FabricBlockEntityTypeBuilder.create(RainbowBedBlockEntity::new, MultiverseBlocks.RAINBOW_BED).build(null));
}
