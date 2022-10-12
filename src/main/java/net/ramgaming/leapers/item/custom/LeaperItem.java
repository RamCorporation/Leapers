package net.ramgaming.leapers.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LeaperItem extends Item {
    public LeaperItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        if(Screen.hasShiftDown()){
            NbtCompound nbt = stack.getNbt();
            if(nbt == null || nbt.getIntArray("leapingPos") == null) {
                tooltip.add(Text.literal("crystal has no").formatted(Formatting.GRAY));
                tooltip.add(Text.literal("cut coordinates").formatted(Formatting.GRAY));
            } else {
                if(nbt.getIntArray("leapingPos").length == 0) {
                    tooltip.add(Text.literal("failed to retrieve").formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("leaping coordinates").formatted(Formatting.GRAY));
                } else {
                    tooltip.add(Text.literal("Attached Crystal is").formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("cut to coordinates:").formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("X: " + nbt.getIntArray("leapingPos")[0]+" | Z: "+ nbt.getIntArray("leapingPos")[1]));}

            }
        }else {
            tooltip.add(Text.literal("Press Shift to view").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("crystal specifications").formatted(Formatting.GRAY));
        }
    }
}
