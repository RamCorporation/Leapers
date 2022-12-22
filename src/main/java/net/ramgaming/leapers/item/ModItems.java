package net.ramgaming.leapers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.item.custom.AerisLeaperItem;
import net.ramgaming.leapers.item.custom.MemoriaLeaperItem;

public class ModItems {

    public static final Item UNCUT_AERIS_CRYSTAL = registerItem("uncut_aeris_crystal", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item UNCUT_FERVIS_CRYSTAL = registerItem("uncut_fervis_crystal", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item UNCUT_UMBER_CRYSTAL = registerItem("uncut_umber_crystal", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);

    public static final Item UMBER_CRYSTAL = registerItem("umber_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item AERIS_CRYSTAL = registerItem("aeris_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item FERVIS_CRYSTAL = registerItem("fervis_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item ALLURE_CRYSTAL = registerItem("allure_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item GALVA_CRYSTAL = registerItem("galva_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item HORA_CRYSTAL = registerItem("hora_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item MEMORIA_CRYSTAL = registerItem("memoria_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item SPECTRAL_CRYSTAL = registerItem("spectral_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);

    public static final Item ENFORCER = registerItem("enforcer", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);

    public static final Item AERIS_LEAPER = registerItem("aeris_leaper", new AerisLeaperItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)),ModItemGroups.LEAPER_GROUP);
    public static final Item MEMORIA_LEAPER = registerItem("memoria_leaper", new MemoriaLeaperItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)),ModItemGroups.LEAPER_GROUP);

    public static final Item STONE_ROD = registerItem("stone_rod", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item COPPER_ROD = registerItem("copper_rod", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);

    private static Item registerItem(String name, Item item, ItemGroup tab) {
        Item itemz = Registry.register(Registries.ITEM, new Identifier(Leapers.MOD_ID,name),item);
        ModItemGroups.appendToTab(itemz,tab);
        return itemz;
    }

    public static void registerModItems() {
        Leapers.LOGGER.debug("Registering items for mod: "+Leapers.MOD_ID);
    }
}