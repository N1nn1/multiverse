package com.ninni.multiverse.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class RainbowCarpetBlock extends CarpetBlock {
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected RainbowCarpetBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(((this.stateDefinition.any()).setValue(HORIZONTAL_FACING, Direction.NORTH)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return (BlockState)this.defaultBlockState().setValue(HORIZONTAL_FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }
}
