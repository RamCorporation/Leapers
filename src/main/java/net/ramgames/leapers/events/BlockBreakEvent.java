package net.ramgames.leapers.events;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ramgames.leapers.blocks.ModBlocks;
import net.ramgames.leapers.blocks.custom.CrystallizedSculkBlock;
import net.ramgames.leapers.blocks.custom.SandyClusterBlock;

public interface BlockBreakEvent {

    static void start(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity entity) {
        if(player.isCreative()) return;
        if(state.getBlock() == ModBlocks.SANDY_CLUSTER) actSandyCluster(world, pos, state);
        if(state.getBlock() == ModBlocks.CRYSTALLIZED_SCULK) actCrystallizedSculk(world, pos, state);
    }

    private static void actSandyCluster(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, SandyClusterBlock.getClusterState(state), 3);
    }

    private static void actCrystallizedSculk(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, CrystallizedSculkBlock.getClusterState(state), 3);
    }
}
