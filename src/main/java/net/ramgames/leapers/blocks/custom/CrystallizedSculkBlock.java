package net.ramgames.leapers.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SculkBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import net.ramgames.leapers.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class CrystallizedSculkBlock extends SculkBlock {

    public static final Property<Integer> GROWTH = IntProperty.of("growth", 1, 4);
    public static final Property<Direction> FACING = DirectionProperty.of("facing", Direction.class);
    public CrystallizedSculkBlock(Settings settings) {
        super(settings);
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


    public static BlockState getClusterState(BlockState state) {
        return switch (state.get(GROWTH)) {
            case 1 -> ModBlocks.SMALL_MEMORIA_BUD.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            case 2 -> ModBlocks.MEDIUM_MEMORIA_BUD.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            case 3 -> ModBlocks.LARGE_MEMORIA_BUD.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            case 4 -> ModBlocks.MEMORIA_CLUSTER.getDefaultState().with(CrystalClusterBlock.FACING, state.get(FACING)).with(CrystalClusterBlock.CAN_GROW, false);
            default -> throw new IndexOutOfBoundsException("unknown Growth value: "+state.get(GROWTH));
        };
    }
}
