package net.ramgaming.leapers.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ramgaming.leapers.LeaperVariables;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MemoriaLeaperItem extends Item {
    public MemoriaLeaperItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            NbtCompound nbt = stack.getNbt();
            if(nbt == null || nbt.getUuid("memoriaID") == null || null == LeaperVariables.getLeapPad(world,nbt.getUuid("memoriaID"))) {
                tooltip.add(Text.literal("crystal is not bound to").formatted(Formatting.GRAY));
                tooltip.add(Text.literal("any other Memoria crystal").formatted(Formatting.GRAY));
            } else {
                BlockPos pos = LeaperVariables.getLeapPad(world,nbt.getUuid("memoriaID"));
                    tooltip.add(Text.literal("Attached Crystal is bound").formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("to another crystal at:").formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("X: " + pos.getX()+" | Z: "+ pos.getZ()));}
        }else {
            tooltip.add(Text.literal("Press Shift to view").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("crystal specifications").formatted(Formatting.GRAY));
        }
    }
}
