package net.ramgames.leapers.block.custom;

import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionTypes;
import net.ramgames.leapers.block.ModBlocks;

public class BuddingEndstoneBlock extends Block {
    public BuddingEndstoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        //if(random.nextInt(10) != 0) return;
        Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
        BlockPos blockPos = pos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        RegistryEntry<Biome> biome = world.getBiome(blockPos);
        Block block = null;
        if(world.getDimensionKey() == DimensionTypes.THE_END) block = checkWithSet(blockState, direction, ModBlocks.SMALL_UMBER_BUD, ModBlocks.MEDIUM_UMBER_BUD, ModBlocks.LARGE_UMBER_BUD, ModBlocks.UMBER_CLUSTER);
        if (block == null) return;
        BlockState blockState2 = block.getDefaultState().with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
        world.setBlockState(blockPos, blockState2);
    }

    public static Block checkWithSet(BlockState blockState, Direction direction, Block smallBud, Block mediumBud, Block largeBud, Block cluster) {
        if(blockState.isAir() || blockState.isOf(Blocks.WATER) && blockState.getFluidState().getLevel() == 8) return smallBud;
        else if(blockState.isOf(smallBud) && blockState.get(AmethystClusterBlock.FACING) == direction) return mediumBud;
        else if (blockState.isOf(mediumBud) && blockState.get(AmethystClusterBlock.FACING) == direction) return largeBud;
        else if (blockState.isOf(largeBud) && blockState.get(AmethystClusterBlock.FACING) == direction) return cluster;
        return null;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
}
