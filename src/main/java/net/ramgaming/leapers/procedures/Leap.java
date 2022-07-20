package net.ramgaming.leapers.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.ramgaming.leapers.RegisterTags.LEAPER_TAG;

public class Leap {
    public static TypedActionResult<ItemStack> Start(PlayerEntity player, World world, Hand hand) {
        if (!world.isClient() && hand == Hand.MAIN_HAND) {
            player.sendMessage(Text.literal("CLICKED"));
            ItemStack MH_STACK = player.getStackInHand(Hand.MAIN_HAND);
            Item MH_ITEM = MH_STACK.getItem();
            if (MH_STACK.isIn(LEAPER_TAG)) {
                player.sendMessage(Text.literal("CLICKED LEAPER"));
            }
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }

}
