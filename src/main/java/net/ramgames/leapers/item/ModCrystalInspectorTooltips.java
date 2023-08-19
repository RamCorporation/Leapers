package net.ramgames.leapers.item;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Formatting;
import net.ramgames.leapers.api.data.LeaperRegistries;
import net.ramgames.leapers.api.data.LeaperRegistry;
import net.ramgames.leapers.api.modules.CrystalInspectorTooltip;

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

        LeaperRegistry.register(LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP, Registries.ITEM.getId(Blocks.AIR.asItem()), CRYSTAL_INSPECTOR);
        LeaperRegistry.register(LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP, Registries.ITEM.getId(ModItems.MEMORIA_CRYSTAL), MEMORIA_CRYSTAL);
    }

}
