package net.ramgaming.leapers.events;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.ramgaming.leapers.block.ModBlocks;
import net.ramgaming.leapers.block.custom.RedstoneRayBlock;

public class PlaceRedstoneRayEvent {
    protected static ActionResult start(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult){
        if(player.getStackInHand(hand).getItem() != ModBlocks.REDSTONE_RAY.asItem()) {
            return ActionResult.PASS;
        }
        if(player.getStackInHand(hand).getItem() == ModBlocks.REDSTONE_RAY.asItem() && world.getBlockState(hitResult.getBlockPos()).getBlock() != Blocks.REPEATER){
            return ActionResult.FAIL;
        }
        if(player.getStackInHand(hand).getItem() == ModBlocks.REDSTONE_RAY.asItem() && world.getBlockState(hitResult.getBlockPos()).getBlock() == Blocks.REPEATER) {
            if(!player.isCreative()) {
                if (player.getStackInHand(hand).getCount() == 1) {
                    player.setStackInHand(hand, ItemStack.EMPTY);
                } else {
                    ItemStack stack = player.getStackInHand(hand);
                    stack.setCount(stack.getCount() - 1);
                    player.setStackInHand(hand, stack);
                }
            }
            BlockState state = ModBlocks.REDSTONE_RAY.getDefaultState().with(RedstoneRayBlock.FACING,world.getBlockState(hitResult.getBlockPos()).get(RepeaterBlock.FACING).getOpposite()).with(RedstoneRayBlock.Preview,false).with(RedstoneRayBlock.POWERED,false);
            state.with(RedstoneRayBlock.FACING,world.getBlockState(hitResult.getBlockPos()).get(RepeaterBlock.FACING));
            world.removeBlock(hitResult.getBlockPos(),false);
            world.setBlockState(hitResult.getBlockPos(),state);
            return ActionResult.PASS;
        }
        return ActionResult.PASS;
    }
}
