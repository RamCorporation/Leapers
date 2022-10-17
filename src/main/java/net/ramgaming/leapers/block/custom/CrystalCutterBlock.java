package net.ramgaming.leapers.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
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
import net.ramgaming.leapers.RegisterTags;
import net.ramgaming.leapers.block.entity.CrystalCutterBlockEntity;
import net.ramgaming.leapers.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class CrystalCutterBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(2, 4, 2, 14, 5, 14), Block.createCuboidShape(0, 0, 0, 16, 4, 16), BooleanBiFunction.OR);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }




    public CrystalCutterBlock(Settings settings) {
        super(settings);
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CrystalCutterBlockEntity(pos,state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
       if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CrystalCutterBlockEntity) {
                ItemScatterer.spawn(world, pos, (CrystalCutterBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
        if(blockEntity.getStack(0) == ItemStack.EMPTY && player.getStackInHand(hand).isIn(RegisterTags.UNCUT_CRYSTALS)) {
            player.getStackInHand(hand).setCount(player.getStackInHand(hand).getCount()-1);
            blockEntity.setStack(0,new ItemStack(player.getStackInHand(hand).getItem()).copy());
        }
        if(blockEntity.getStack(0) != ItemStack.EMPTY && player.getStackInHand(hand) == ItemStack.EMPTY) {
            player.setStackInHand(hand,blockEntity.getStack(0).copy());
            blockEntity.setStack(0,ItemStack.EMPTY);
        }
        blockEntity.markDirty();
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CRYSTAL_CUTTER,CrystalCutterBlockEntity::tick);
    }
}
