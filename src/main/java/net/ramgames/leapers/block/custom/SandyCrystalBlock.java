package net.ramgames.leapers.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.block.ModBlocks;

public class SandyCrystalBlock extends FacingBlock {

    public static final Property<Integer> GROWTH = IntProperty.of("growth", 1, 3);
    public SandyCrystalBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        Leapers.LOGGER.info("updating!");
        if(!world.getBlockState(pos.mutableCopy().offset(Direction.DOWN)).isSolid()) {
            Leapers.LOGGER.info("no solid");
            FallingBlockEntity entity = FallingBlockEntity.spawnFromBlock(world, pos, Blocks.SAND.getDefaultState());
            entity.setPos(pos.getX(), pos.getY()-0.1, pos.getZ());
            world.spawnEntity(entity);
            world.scheduleBlockTick(pos, this, 2);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GROWTH, FACING);
        super.appendProperties(builder);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        Leapers.LOGGER.info("scheduled!");
        Block crystalBlock;
        if(state.get(GROWTH) == 1) crystalBlock = ModBlocks.SMALL_HORA_BUD;
        else if(state.get(GROWTH) == 2) crystalBlock = ModBlocks.MEDIUM_HORA_BUD;
        else crystalBlock = ModBlocks.LARGE_HORA_BUD;
        BlockState crystalState = crystalBlock.getDefaultState().with(FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
        world.setBlockState(pos, crystalState);
        super.scheduledTick(state, world, pos, random);
    }
}
