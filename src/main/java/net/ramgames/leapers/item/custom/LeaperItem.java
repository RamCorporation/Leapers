package net.ramgames.leapers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.ramgames.leapers.Leapers;

public class LeaperItem extends Item {

    public static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.93333f, 0.93333f, 0.93333f);
    public LeaperItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if(nbt == null) return false;
        if(!nbt.contains("Damage")) return false;
        return nbt.getInt("Damage") != 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if(nbt == null) {
            Leapers.LOGGER.info("null :skull:");
            return 0;
        }
        if(!nbt.contains("MaxDamage") || !nbt.contains("Damage")) {
            Leapers.LOGGER.info("improper tags :skull: :skull:");
            return 0;
        }
        float f = Math.max(0.0F, ((float)nbt.getInt("MaxDamage") - (float)nbt.getInt("Damage")) / (float)nbt.getInt("MaxDamage"));

        return (int) Math.floor((double) Item.ITEM_BAR_STEPS * f);
    }

    public static boolean containsProperNbt(NbtCompound nbt) {
        return nbt.contains("core") && nbt.contains("handle") && nbt.contains("fixture") && nbt.contains("crystal");
    }
}
