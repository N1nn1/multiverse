package com.ninni.multiverse.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RainbowBedBlockEntity extends BlockEntity {

    public RainbowBedBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MultiverseBlockEntityTypes.RAINBOW_BED, blockPos, blockState);
    }

    @Override
    public Packet getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}


