package net.ramgaming.leapers.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.ramgaming.leapers.block.ModShapes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MirrorBlock extends Block {
    public MirrorBlock(Settings settings) {
        super(settings);
    }
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<MirrorDirections> POINTING = EnumProperty.of("pointing",MirrorDirections.class);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> switch(state.get(POINTING)) {
                case DOWN -> ModShapes.MIRROR_DOWN_SOUTH;
                case UP -> ModShapes.MIRROR_UP_SOUTH;
                case STANDING -> ModShapes.MIRROR_NORTH;
            };
            case SOUTH -> switch(state.get(POINTING)) {
                case DOWN -> ModShapes.MIRROR_DOWN_NORTH;
                case UP -> ModShapes.MIRROR_UP_NORTH;
                case STANDING -> ModShapes.MIRROR_SOUTH;
            };
            case EAST -> switch(state.get(POINTING)) {
                case DOWN -> ModShapes.MIRROR_DOWN_WEST;
                case UP -> ModShapes.MIRROR_UP_WEST;
                case STANDING -> ModShapes.MIRROR_EAST;
            };
            case WEST -> switch(state.get(POINTING)) {
                case DOWN -> ModShapes.MIRROR_DOWN_EAST;
                case UP -> ModShapes.MIRROR_UP_EAST;
                case STANDING -> ModShapes.MIRROR_WEST;
            };
            default -> VoxelShapes.fullCube();
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = this.getDefaultState();
        state = state.with(FACING, ctx.getPlayerFacing());
        if(Objects.requireNonNull(ctx.getPlayer()).isSneaking()) {
            if(ctx.getPlayer().getPitch() >=0) {
                state = state.with(POINTING,MirrorDirections.UP);
            } else {
                state = state.with(POINTING,MirrorDirections.DOWN);
            }

        } else {
            state = state.with(FACING, ctx.getPlayerFacing().getOpposite());
            state = state.with(POINTING,MirrorDirections.STANDING);
        }
        return state;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POINTING,FACING);
    }


}
