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
import net.minecraft.world.World;
import net.ramgames.leapers.blocks.ModBlocks;
import net.ramgames.leapers.blocks.custom.RedstoneRayBlock;
import net.ramgames.leapers.items.ModItems;

public interface PlaceRedstoneRayEvent {
     static ActionResult start(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult){
        if(player.getStackInHand(hand).getItem() != ModItems.ALLURE_CRYSTAL) return ActionResult.PASS;
        if(world.getBlockState(hitResult.getBlockPos()).getBlock() != Blocks.REPEATER) return ActionResult.PASS;
        ItemStack stack = player.getStackInHand(hand);
        if(stack.getCount() == 1) player.setStackInHand(hand, ItemStack.EMPTY);
        else {
            stack.setCount(stack.getCount() - 1);
            player.setStackInHand(hand, stack);
        }
        BlockPos blockPos = hitResult.getBlockPos();
        BlockState state = ModBlocks.REDSTONE_RAY.getDefaultState().with(RedstoneRayBlock.FACING, world.getBlockState(blockPos).get(RepeaterBlock.FACING)).with(RedstoneRayBlock.POWERED,false);
        world.removeBlock(blockPos,false);
        world.setBlockState(blockPos,state);
        return ActionResult.PASS;
    }
}
