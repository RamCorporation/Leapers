package net.ramgames.leapers.events;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.ramgames.leapers.block.ModBlocks;
import net.ramgames.leapers.block.custom.RedstoneRayBlock;

public class PlaceRedstoneRayEvent {
    protected static ActionResult start(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult){
        if(player.getStackInHand(hand).getItem() != ModBlocks.REDSTONE_RAY.asItem()) return ActionResult.PASS;
        if(player.isCreative() && world.getBlockState(hitResult.getBlockPos()).getBlock() != Blocks.REPEATER) return act(world, hitResult.getBlockPos().offset(hitResult.getSide()), player.getHorizontalFacing().getOpposite());
        if(world.getBlockState(hitResult.getBlockPos()).getBlock() != Blocks.REPEATER) return ActionResult.FAIL;
        ItemStack stack = player.getStackInHand(hand);
        if(stack.getCount() == 1) player.setStackInHand(hand, ItemStack.EMPTY);
        else {
            stack.setCount(stack.getCount() - 1);
            player.setStackInHand(hand, stack);
        }
        return act(world, hitResult.getBlockPos(), world.getBlockState(hitResult.getBlockPos()).get(RepeaterBlock.FACING));
    }

    private static ActionResult act(World world, BlockPos blockPos, Direction direction) {
        BlockState state = ModBlocks.REDSTONE_RAY.getDefaultState().with(RedstoneRayBlock.FACING, direction).with(RedstoneRayBlock.PREVIEW,false).with(RedstoneRayBlock.POWERED,false);
        world.removeBlock(blockPos,false);
        world.setBlockState(blockPos,state);
        return ActionResult.SUCCESS;
    }
}
