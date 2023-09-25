package net.ramgames.leapers.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.leaption.LeaptionManager;
import net.ramgames.leapers.leaption.api.data.LeaperRegistries;
import net.ramgames.leapers.leaption.api.modules.Core;
import net.ramgames.leapers.leaption.api.modules.Crystal;
import net.ramgames.leapers.leaption.api.modules.Fixture;
import net.ramgames.leapers.leaption.api.modules.Handle;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class LeaperItem extends Item implements Vanishable {

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

    public static boolean containsImproperNbt(NbtCompound nbt) {
        return nbt == null || !nbt.contains("Core") || !nbt.contains("Handle") || !nbt.contains("Fixture") || !nbt.contains("Crystal") || !nbt.contains("Specifications");
    }
    public static Optional<int[]> getCrystalSpecifications(NbtCompound nbt) {
        return Optional.ofNullable(nbt.getIntArray("Specifications"));
    }

    public static Optional<Core> getCoreEntry(NbtCompound nbt) {
        return Optional.ofNullable(LeaperRegistries.CORES.get(new Identifier(nbt.getString("Core"))));
    }
    public static Optional<Handle> getHandleEntry(NbtCompound nbt) {
        return Optional.ofNullable(LeaperRegistries.HANDLES.get(new Identifier(nbt.getString("Handle"))));
    }
    public static Optional<Fixture> getFixtureEntry(NbtCompound nbt) {
        return Optional.ofNullable(LeaperRegistries.FIXTURES.get(new Identifier(nbt.getString("Fixture"))));
    }
    public static Optional<Crystal> getCrystalEntry(NbtCompound nbt) {
        return Optional.ofNullable(LeaperRegistries.CRYSTALS.get(new Identifier(nbt.getString("Crystal"))));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

}
