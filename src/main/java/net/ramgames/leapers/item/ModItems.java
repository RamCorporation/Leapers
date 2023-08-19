package net.ramgames.leapers.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.item.custom.LeaperItem;
import org.jetbrains.annotations.Nullable;

public class ModItems {

    public static final Item AMETHYST_CORE = registerItem("amethyst_core", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item DIAMOND_CORE = registerItem("diamond_core", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item NETHERITE_CORE = registerItem("netherite_core", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item HEART_OF_THE_SEA_CORE = registerItem("heart_of_the_sea_core", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item STAR_CORE = registerItem("star_core", new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);


    public static final Item UMBER_CRYSTAL = registerItem("umber_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item AERIS_CRYSTAL = registerItem("aeris_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item FERVIS_CRYSTAL = registerItem("fervis_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item ALLURE_CRYSTAL = registerItem("allure_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item GALVA_CRYSTAL = registerItem("galva_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item HORA_CRYSTAL = registerItem("hora_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item MEMORIA_CRYSTAL = registerItem("memoria_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);
    public static final Item SPECTRAL_CRYSTAL = registerItem("spectral_crystal",new Item(new FabricItemSettings()),ModItemGroups.LEAPER_GROUP);

    public static final Item CUT_UMBER_CRYSTAL = registerItem("cut_umber_crystal",new Item(new FabricItemSettings()),null);
    public static final Item CUT_AERIS_CRYSTAL = registerItem("cut_aeris_crystal",new Item(new FabricItemSettings()),null);
    public static final Item CUT_FERVIS_CRYSTAL = registerItem("cut_fervis_crystal",new Item(new FabricItemSettings()),null);
    public static final Item CUT_ALLURE_CRYSTAL = registerItem("cut_allure_crystal",new Item(new FabricItemSettings()),null);
    public static final Item CUT_GALVA_CRYSTAL = registerItem("cut_galva_crystal",new Item(new FabricItemSettings()),null);
    public static final Item CUT_HORA_CRYSTAL = registerItem("cut_hora_crystal",new Item(new FabricItemSettings()),null);
    public static final Item CUT_MEMORIA_CRYSTAL = registerItem("cut_memoria_crystal",new Item(new FabricItemSettings()),null);
    public static final Item CUT_SPECTRAL_CRYSTAL = registerItem("cut_spectral_crystal",new Item(new FabricItemSettings()),null);


    public static final Item GLASS_FIXTURE = registerItem("glass_fixture",new Item(new FabricItemSettings()), ModItemGroups.LEAPER_GROUP);
    public static final Item GOLD_FIXTURE = registerItem("gold_fixture",new Item(new FabricItemSettings()), ModItemGroups.LEAPER_GROUP);
    public static final Item MEMORIA_FIXTURE = registerItem("memoria_fixture",new Item(new FabricItemSettings()), ModItemGroups.LEAPER_GROUP);
    public static final Item QUARTZ_FIXTURE = registerItem("quartz_fixture",new Item(new FabricItemSettings()), ModItemGroups.LEAPER_GROUP);



    public static final Item ENFORCER = registerItem("enforcer", new Item(new FabricItemSettings()),null);

    public static final Item LEAPER = registerItem("leaper", new LeaperItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)),ModItemGroups.LEAPER_GROUP);

    private static Item registerItem(String name, Item item, @Nullable ItemGroup tab) {
        Registry.register(Registries.ITEM, new Identifier(Leapers.MOD_ID,name),item);
        if(tab != null) ModItemGroups.appendToTab(item,tab);
        return item;
    }

    public static void registerModItems() {
        Leapers.LOGGER.debug("Registering items for mod: "+Leapers.MOD_ID);
    }
}