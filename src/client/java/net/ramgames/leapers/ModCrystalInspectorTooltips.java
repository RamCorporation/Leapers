package net.ramgames.leapers;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Formatting;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.leaption.api.data.LeaperRegistries;
import net.ramgames.leapers.leaption.api.modules.CrystalInspectorTooltip;

public class ModCrystalInspectorTooltips {

    public static final CrystalInspectorTooltip CRYSTAL_INSPECTOR = CrystalInspectorTooltip.build()
            .add("Crystal Inspector", Formatting.WHITE).newLine()
            .newLine()
            .add("Insert a crystal to", Formatting.GRAY).newLine()
            .add("inspect it").finish();

    public static final CrystalInspectorTooltip MEMORIA_CRYSTAL = CrystalInspectorTooltip.build()
            .add("Memoria Crystal", Formatting.WHITE).newLine()
            .newLine()
            .add("ID:", Formatting.GRAY).newLine()
            .add("$UUID$").finish();


    public static void onInitialize() {

        Registry.register(LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP, Registries.ITEM.getId(Blocks.AIR.asItem()), CRYSTAL_INSPECTOR);
        Registry.register(LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP, Registries.ITEM.getId(ModItems.MEMORIA_CRYSTAL), MEMORIA_CRYSTAL);
    }

}
