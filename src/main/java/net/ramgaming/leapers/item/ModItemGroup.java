package net.ramgaming.leapers.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;

public class ModItemGroup {
    public static final ItemGroup LEAPER_GROUP = FabricItemGroupBuilder.build(new Identifier(Leapers.MOD_ID, "leaper_group"), () -> new ItemStack(ModItems.SPECTRAL_CRYSTAL));
}
