package net.ramgaming.leapers.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.ramgaming.leapers.block.entity.ModBlockEntities;
import net.ramgaming.leapers.block.entity.SkyGazerBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class SkyGazerBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(13, 2, 13, 16, 3, 16),
            Block.createCuboidShape(13, 2, 0, 16, 3, 3),
            Block.createCuboidShape(0, 2, 0, 3, 3, 3),
            Block.createCuboidShape(0, 2, 13, 3, 3, 16),
            Block.createCuboidShape(1, 3, 1, 3, 14, 3),
            Block.createCuboidShape(1, 3, 13, 3, 14, 15),
            Block.createCuboidShape(13, 3, 13, 15, 14, 15),
            Block.createCuboidShape(13, 3, 1, 15, 14, 3),
            Block.createCuboidShape(3, 12, 13, 13, 13, 14),
            Block.createCuboidShape(13, 0, 3, 16, 2, 13),
            Block.createCuboidShape(3, 0, 3, 13, 1, 13),
            Block.createCuboidShape(0, 0, 3, 3, 2, 13),
            Block.createCuboidShape(0, 0, 0, 16, 2, 3),
            Block.createCuboidShape(0, 0, 13, 16, 2, 16),
            Block.createCuboidShape(3, 12, 2, 13, 13, 3),
            Block.createCuboidShape(13, 12, 3, 14, 13, 13),
            Block.createCuboidShape(2, 12, 3, 3, 13, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }




    public SkyGazerBlock(Settings settings) {
        super(settings);
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SkyGazerBlockEntity(pos,state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SkyGazerBlockEntity) {
                ItemScatterer.spawn(world, pos, (SkyGazerBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.SKY_GAZER,SkyGazerBlockEntity::tick);
    }
}
