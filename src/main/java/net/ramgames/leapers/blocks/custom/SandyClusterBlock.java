package net.ramgames.leapers.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.ramgames.leapers.blocks.ModBlocks;
import net.ramgames.leapers.blocks.entity.SandyClusterFallingBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SandyClusterBlock extends SandBlock {

    public static final Property<Integer> GROWTH = IntProperty.of("growth", 1, 4);
    public static final Property<Direction> FACING = DirectionProperty.of("facing", Direction.class);
    public SandyClusterBlock(Settings settings) {
        super(14406560, settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GROWTH, FACING);
        super.appendProperties(builder);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
            FallingBlockEntity fallingBlockEntity = spawnFromBlock(world, pos, state);
            this.configureFallingBlockEntity(fallingBlockEntity);
        }
    }

    private static FallingBlockEntity spawnFromBlock(World world, BlockPos pos, BlockState state) {
        FallingBlockEntity fallingBlockEntity = new SandyClusterFallingBlockEntity(EntityType.FALLING_BLOCK, world);
        fallingBlockEntity.setPos((double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5);
        fallingBlockEntity.noClip = true;
        BlockState blockState = getClusterState(state);
        world.setBlockState(pos, blockState, 3);
        world.spawnEntity(fallingBlockEntity);
        return fallingBlockEntity;
    }

    public static BlockState getClusterState(BlockState state) {
        return switch (state.get(GROWTH)) {
            case 1 -> ModBlocks.SMALL_HORA_BUD.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            case 2 -> ModBlocks.MEDIUM_HORA_BUD.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            case 3 -> ModBlocks.LARGE_HORA_BUD.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            case 4 -> ModBlocks.HORA_CLUSTER.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            default -> throw new IndexOutOfBoundsException("unknown Growth value: "+state.get(GROWTH));
        };
    }
}
