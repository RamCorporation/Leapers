package net.ramgaming.leapers.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ComparatorBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.ramgaming.leapers.block.ModShapes;
import net.ramgaming.leapers.block.entity.RedstoneRayBlockEntity;
import org.jetbrains.annotations.Nullable;

public class RedstoneRayBlock extends AbstractRedstoneGateBlock implements BlockEntityProvider {
    public RedstoneRayBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(POWERED,false));
    }
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> ModShapes.REDSTONE_RAY_NORTH;
            case SOUTH -> ModShapes.REDSTONE_RAY_SOUTH;
            case EAST -> ModShapes.REDSTONE_RAY_EAST;
            case WEST -> ModShapes.REDSTONE_RAY_WEST;
            default -> VoxelShapes.fullCube();
        };
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof ComparatorBlockEntity ? ((ComparatorBlockEntity)blockEntity).getOutputSignal() : 0;
    }

    @Override
    protected int getPower(World world, BlockPos pos, BlockState state) {
        return super.getPower(world, pos, state);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RedstoneRayBlockEntity(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BlockEntityProvider.super.getTicker(world, state, type);
    }
}
