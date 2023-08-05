package net.ramgames.leapers.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ComparatorBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.ramgames.leapers.block.ModShapes;
import net.ramgames.leapers.block.entity.RedstoneRayBlockEntity;
import org.jetbrains.annotations.Nullable;

public class RedstoneRayBlock extends AbstractRedstoneGateBlock implements BlockEntityProvider {
    public RedstoneRayBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(POWERED,false).with(PREVIEW,true));
    }
    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.Type.HORIZONTAL);
    public static final IntProperty POWER = Properties.POWER;
    public static final BooleanProperty PREVIEW = BooleanProperty.of("preview");

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

        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(PREVIEW,false);
    }

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, PREVIEW);
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

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        world.setBlockState(pos,Blocks.REPEATER.getDefaultState().with(RepeaterBlock.FACING,state.get(FACING)));
    }
}
