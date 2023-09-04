package net.ramgames.leapers.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

public class DisleaperBlock extends Block {


    public DisleaperBlock(Settings settings) {
        super(settings);
    }
    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return world.getMaxLightLevel();
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(random.nextBoolean()) return;
        int count = 0;
        RegistryKey<DimensionType> dimensionKey = world.getDimensionKey();
        if(dimensionKey == DimensionTypes.OVERWORLD) {
            if(world.getLightLevel(pos) <= 7) {
                if(random.nextBoolean()) return;
                count = 1;
            }
            else if(world.getLightLevel(pos) <= 12) count = 2;
            else count = 3;
        }
        if(dimensionKey == DimensionTypes.THE_NETHER || dimensionKey == DimensionTypes.OVERWORLD_CAVES) {
            if(random.nextBoolean()) return;
            count = 1;
        }
        for(int i = 0; i < count; ++i) {
            double xChange = (random.nextBoolean() ? -random.nextDouble() : random.nextDouble()) * 2;
            double yChange = (random.nextBoolean() ? -random.nextDouble() : random.nextDouble()) * 2;
            double zChange = (random.nextBoolean() ? -random.nextDouble() : random.nextDouble()) * 2;
            double d = (double)pos.getX() + xChange;
            double e = (double)pos.getY() + yChange;
            double f = (double)pos.getZ() + zChange;
            double g = (xChange-0.5) * 0.065;
            double h = (yChange-0.5) * 0.065;
            double j = (zChange-0.5) * 0.065;
            world.addParticle(ParticleTypes.END_ROD, d, e, f, -g, -h, -j);
        }
    }
}
