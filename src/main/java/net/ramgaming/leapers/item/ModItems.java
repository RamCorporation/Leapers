package net.ramgaming.leapers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.item.custom.LeaperItem;

public class ModItems {

    public static final Item UNCUT_AERIS_CRYSTAL = registerItem("uncut_aeris_crystal", new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item UNCUT_FERVIS_CRYSTAL = registerItem("uncut_fervis_crystal", new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item UNCUT_UMBER_CRYSTAL = registerItem("uncut_umber_crystal", new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));

    public static final Item UMBER_CRYSTAL = registerItem("umber_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item AERIS_CRYSTAL = registerItem("aeris_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item FERVIS_CRYSTAL = registerItem("fervis_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item ALLURE_CRYSTAL = registerItem("allure_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item GALVA_CRYSTAL = registerItem("galva_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item HORA_CRYSTAL = registerItem("hora_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item MEMORIA_CRYSTAL = registerItem("memoria_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item SPECTRAL_CRYSTAL = registerItem("spectral_crystal",new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));

    public static final Item ENFORCER = registerItem("enforcer", new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));

    public static final Item AERIS_LEAPER = registerItem("aeris_leaper", new LeaperItem(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP).maxCount(1).rarity(Rarity.RARE)));

    public static final Item STONE_ROD = registerItem("stone_rod", new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));
    public static final Item COPPER_ROD = registerItem("copper_rod", new Item(new FabricItemSettings().group(ModItemGroup.LEAPER_GROUP)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Leapers.MOD_ID,name),item);
    }

    public static void registerModItems() {
        Leapers.LOGGER.debug("Registering items for mod: "+Leapers.MOD_ID);
    }
}
// Umber - deep
// Aeris - normal
//Fervis -orange