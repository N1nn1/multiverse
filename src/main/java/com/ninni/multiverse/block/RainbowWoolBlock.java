package com.ninni.multiverse.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
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
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(60) == 0 && level.getBlockState(blockPos.above()).isAir()) {
            for (int i = 0; i < randomSource.nextInt(1) + 1; ++i) {
                level.addParticle(ParticleTypes.WAX_OFF, blockPos.getX() + randomSource.nextFloat(), blockPos.getY() + 1, blockPos.getZ() + randomSource.nextFloat(), randomSource.nextFloat() / 2.0f, randomSource.nextFloat() * 5, randomSource.nextFloat() / 2.0f);
            }
        }
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
