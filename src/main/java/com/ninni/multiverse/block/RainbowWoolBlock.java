package com.ninni.multiverse.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class RainbowWoolBlock extends DirectionalBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    protected RainbowWoolBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(((this.stateDefinition.any()).setValue(FACING, Direction.NORTH)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getNearestLookingDirection());
    }
}
