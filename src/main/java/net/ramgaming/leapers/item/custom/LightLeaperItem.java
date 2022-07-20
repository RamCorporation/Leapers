package net.ramgaming.leapers.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class LightLeaperItem extends Item {
    public LightLeaperItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient() && hand == Hand.MAIN_HAND) {
            leap(user);
            user.getItemCooldownManager().set(this,20);
        }

        return super.use(world, user, hand);
    }

     private static void leap(PlayerEntity player) {
        player.sendMessage(Text.literal("Leaping..."));
     }

    private int getRandomNumber(int max) {
        return Random.createLocal().nextInt(max);
    }
}
