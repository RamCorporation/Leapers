package net.ramgaming.leapers;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;


public class Leap
{
    public static void RegisterEvents()
    {
        UseItemCallback.EVENT.register((player, world, hand) ->{
             if (!world.isClient() && hand == Hand.MAIN_HAND){
                 ItemStack MAIN_HAND_STACK = player.getStackInHand(Hand.MAIN_HAND);
                 Item MAIN_HAND_ITEM = MAIN_HAND_STACK.getItem();
                 if (MAIN_HAND_STACK.isIn(tag.LEAPERS_TAG)) {}
                 player.sendMessage(Text.literal("CLICKED!!"));
                 player.getItemCooldownManager().set(MAIN_HAND_ITEM, 20);
        }
            return TypedActionResult.pass(ItemStack.EMPTY);
        });
    }
}