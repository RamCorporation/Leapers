package net.ramgames.leapers.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class MirrorBlock extends Block {
    public MirrorBlock(Settings settings) {
        super(settings);
    }
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<MirrorStances> STANCE = EnumProperty.of("stance", MirrorStances.class);
    public static final EnumProperty<MirrorDirections> GRIP = EnumProperty.of("grip", MirrorDirections.class);

    private static final VoxelShape shape = Block.createCuboidShape(2, 0, 2, 14, 12, 14);
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction sidePlacedOn = ctx.getSide();
        Direction facing = ctx.getHorizontalPlayerFacing().getOpposite();
        BlockState state = this.getDefaultState();
        state = state.with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
        if(ctx.getPlayer() == null) return ctx.getWorld().getBlockState(ctx.getBlockPos());
        if(ctx.getPlayer().isSneaking()) {
            if(ctx.getPlayer().getPitch() >=0) {
                state = state.with(STANCE, MirrorStances.UP);
                if(sidePlacedOn == Direction.UP) return state.with(GRIP, MirrorDirections.STANDING);
            } else {
                state = state.with(STANCE, MirrorStances.DOWN);
                if(sidePlacedOn == Direction.DOWN) return state.with(GRIP, MirrorDirections.UP);
            }
            if(sidePlacedOn == facing) return state.with(GRIP, MirrorDirections.BACK);
            if(sidePlacedOn.rotateYClockwise() == facing) return state.with(GRIP, MirrorDirections.LEFT);
            if(sidePlacedOn.rotateYCounterclockwise() == facing) return state.with(GRIP, MirrorDirections.RIGHT);
        } else {
            state = state.with(STANCE, MirrorStances.FRONT);
            if(sidePlacedOn == Direction.DOWN) return state.with(GRIP, MirrorDirections.UP);
            if(sidePlacedOn == Direction.UP) return state.with(GRIP, MirrorDirections.STANDING);
            if(sidePlacedOn == facing) return state.with(GRIP, MirrorDirections.RIGHT);
            if(sidePlacedOn.rotateYClockwise() == facing) return state.with(GRIP, MirrorDirections.LEFT);
        }
        return ctx.getWorld().getBlockState(ctx.getBlockPos());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STANCE,FACING, GRIP);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if(!canGrip(state, world, pos)) return false;
        return super.canPlaceAt(state, world, pos);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private boolean canGrip(BlockState state, WorldView world, BlockPos pos) {
        MirrorStances stance = state.get(STANCE);
        MirrorDirections grip = state.get(GRIP);
        Direction facing = state.get(FACING);
        if(stance == MirrorStances.UP && grip == MirrorDirections.UP) return false;
        if(stance == MirrorStances.DOWN && grip == MirrorDirections.STANDING) return false;
        if(stance == MirrorStances.FRONT && grip == MirrorDirections.BACK) return false;
        return switch (grip) {
            case STANDING -> world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos, Direction.UP);
            case LEFT -> world.getBlockState(pos.offset(facing.rotateYClockwise())).isSideSolidFullSquare(world, pos, facing);
            case RIGHT -> stance == MirrorStances.FRONT ? world.getBlockState(pos.offset(facing.getOpposite())).isSideSolidFullSquare(world, pos, facing) : world.getBlockState(pos.offset(facing.rotateYCounterclockwise())).isSideSolidFullSquare(world, pos, facing);
            case UP -> world.getBlockState(pos.up()).isSideSolidFullSquare(world, pos, Direction.DOWN);
            case BACK -> world.getBlockState(pos.offset(facing.getOpposite())).isSideSolidFullSquare(world, pos, facing);
        };
    }
}
