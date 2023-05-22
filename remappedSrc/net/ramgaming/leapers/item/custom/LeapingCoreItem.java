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

public class LeapingCoreItem extends Item {
    public LeapingCoreItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if(Screen.hasShiftDown()){
            if(nbt == null) {
                tooltip.add(Text.literal("Plating: ").formatted(Formatting.GRAY).append(Text.literal("Copper")).formatted(Formatting.GOLD));
                return;
            }
            Float platingID = nbt.getFloat("PlatingID");
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
}
