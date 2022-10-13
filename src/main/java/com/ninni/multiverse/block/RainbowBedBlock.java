package com.ninni.multiverse.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class RainbowBedBlock extends BedBlock {

    public RainbowBedBlock(Properties properties) {
        super(DyeColor.RED, properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(60) == 0 && level.getBlockState(blockPos.above()).isAir()) {
            for (int i = 0; i < randomSource.nextInt(1) + 1; ++i) {
                level.addParticle(ParticleTypes.WAX_OFF, blockPos.getX() + randomSource.nextFloat(), blockPos.getY() + 0.5, blockPos.getZ() + randomSource.nextFloat(), randomSource.nextFloat() / 2.0f, randomSource.nextFloat() * 5, randomSource.nextFloat() / 2.0f);
            }
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }
}
