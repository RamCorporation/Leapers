package net.ramgames.leapers.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.ramgames.leapers.ModTags;
import net.ramgames.leapers.block.entity.CrystalCutterBlockEntity;
import net.ramgames.leapers.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class CrystalCutterBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(2, 4, 2, 14, 5, 14), Block.createCuboidShape(0, 0, 0, 16, 4, 16), BooleanBiFunction.OR);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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
        Inventory inventory = (Inventory) world.getBlockEntity(pos);
        CrystalCutterBlockEntity blockEntity = (CrystalCutterBlockEntity) world.getBlockEntity(pos);
        if (!world.isClient()) {
            if (inventory.getStack(0) == ItemStack.EMPTY && player.getStackInHand(hand).isIn(ModTags.CRYSTALS)) {
                //world.getBlockEntity(pos).setStackNbt(new ItemStack(player.getStackInHand(hand).getItem()).copy());
            //blockEntity.setStack(0, new ItemStack(player.getStackInHand(hand).getItem()).copy());
                blockEntity.setStack(0,new ItemStack(player.getStackInHand(hand).getItem()).copy());
            if (player.getStackInHand(hand).getCount() > 1) {
                player.getStackInHand(hand).setCount(player.getStackInHand(hand).getCount() - 1);
            } else {
                player.setStackInHand(hand, ItemStack.EMPTY);
            }

        } else {
            if (inventory.getStack(0) != ItemStack.EMPTY && player.getStackInHand(hand) == ItemStack.EMPTY) {
                //world.getBlockEntity(pos).setStackNbt(ItemStack.EMPTY);
                player.setStackInHand(hand, inventory.getStack(0).copy());
                //blockEntity.setStack(0, ItemStack.EMPTY);
                blockEntity.setStack(0,ItemStack.EMPTY);
            }
        }
            inventory.markDirty();
            blockEntity.markDirty();
            world.setBlockState(pos,blockEntity.getCachedState());
            System.out.println(((Inventory) world.getBlockEntity(pos)).getStack(0));
    }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.CRYSTAL_CUTTER,CrystalCutterBlockEntity::tick);
    }
}
