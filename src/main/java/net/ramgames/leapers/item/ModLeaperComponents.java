package net.ramgames.leapers.item;

import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.ramgames.leapers.api.data.LeaperRegistries;
import net.ramgames.leapers.api.data.LeaperRegistry;
import net.ramgames.leapers.api.modules.*;

public class ModLeaperComponents {

    public static final Core AMETHYST_CORE = new Core("leapers:item/cores/amethyst_leaper", "leapers:textures/item/cores/amethyst_core", 6, 1200, 20);
    public static final Core DIAMOND_CORE = new Core("leapers:item/cores/diamond_leaper", "leapers:textures/item/cores/diamond_core",3, 300, 10);
    public static final Core HEART_OF_THE_SEA_CORE = new Core("leapers:item/cores/heart_of_the_sea_leaper", "leapers:textures/item/cores/heart_of_the_sea_core",24, 1800, 10);
    public static final Core NETHERITE_CORE = new Core("leapers:item/cores/netherite_leaper", "leapers:textures/item/cores/netherite_core",12, 1500, 15);
    public static final Core STAR_CORE = new Core("leapers:item/cores/star_leaper", "leapers:textures/item/cores/star_core",48, 3600, 35);

    public static final Handle WOODEN_HANDLE = new Handle("leapers:item/handles/wooden_handle", "minecraft:textures/item/stick",64, 4);
    public static final Handle IRON_HANDLE = new Handle("leapers:item/handles/iron_handle", "minecraft:textures/block/chain",96, 8);
    public static final Handle COPPER_HANDLE = new Handle("leapers:item/handles/copper_handle", "minecraft:textures/block/lightning_rod",128, 16);
    public static final Handle BLAZE_ROD_HANDLE = new Handle("leapers:item/handles/blaze_rod_handle", "minecraft:textures/item/blaze_rod",160, 24);
    public static final Handle END_ROD_HANDLE = new Handle("leapers:item/handles/end_rod_handle", "minecraft:textures/block/end_rod",64, 4);

    public static final Fixture GLASS_FIXTURE = new Fixture("leapers:item/fixtures/glass_leaper", "leapers:textures/item/fixtures/glass_fixture", TransportType.FIXED, 1);
    public static final Fixture GOLD_FIXTURE = new Fixture("leapers:item/fixtures/gold_leaper", "leapers:textures/item/fixtures/gold_fixture", TransportType.RELATIVE, 4);
    public static final Fixture MEMORIA_FIXTURE = new Fixture("leapers:item/fixtures/memoria_leaper", "leapers:textures/item/fixtures/memoria_fixture", TransportType.DYNAMIC, 8);
    public static final Fixture QUARTZ_FIXTURE = new Fixture("leapers:item/fixtures/quartz_leaper", "leapers:textures/item/fixtures/quartz_fixture", TransportType.FIXED, 10);

    public static final Crystal AERIS_CRYSTAL = new Crystal("leapers:item/crystals/aeris_leaper", "leapers:textures/item/crystals/aeris_crystal", CrystalType.DAY, 32);
    public static final Crystal ALLURE_CRYSTAL = new Crystal("leapers:item/crystals/allure_leaper", "leapers:textures/item/crystals/allure_crystal", CrystalType.REDSTONE, 8);
    public static final Crystal FERVIS_CRYSTAL = new Crystal("leapers:item/crystals/fervis_leaper", "leapers:textures/item/crystals/fervis_crystal", CrystalType.NIGHT, 16);

    public static void register() {
        LeaperRegistry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.AMETHYST_CORE), AMETHYST_CORE);
        LeaperRegistry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.DIAMOND_CORE), DIAMOND_CORE);
        LeaperRegistry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.HEART_OF_THE_SEA_CORE), HEART_OF_THE_SEA_CORE);
        LeaperRegistry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.NETHERITE_CORE), NETHERITE_CORE);
        LeaperRegistry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.STAR_CORE), STAR_CORE);

        LeaperRegistry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.STICK), WOODEN_HANDLE);
        LeaperRegistry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.CHAIN), IRON_HANDLE);
        LeaperRegistry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.LIGHTNING_ROD), COPPER_HANDLE);
        LeaperRegistry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.BLAZE_ROD), BLAZE_ROD_HANDLE);
        LeaperRegistry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.END_ROD), END_ROD_HANDLE);

        LeaperRegistry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.GLASS_FIXTURE), GLASS_FIXTURE);
        LeaperRegistry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.GOLD_FIXTURE), GOLD_FIXTURE);
        LeaperRegistry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.MEMORIA_FIXTURE), MEMORIA_FIXTURE);
        LeaperRegistry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.QUARTZ_FIXTURE), QUARTZ_FIXTURE);

        LeaperRegistry.register(LeaperRegistries.CRYSTALS, Registries.ITEM.getId(ModItems.CUT_AERIS_CRYSTAL), AERIS_CRYSTAL);
        LeaperRegistry.register(LeaperRegistries.CRYSTALS, Registries.ITEM.getId(ModItems.CUT_ALLURE_CRYSTAL), ALLURE_CRYSTAL);
        LeaperRegistry.register(LeaperRegistries.CRYSTALS, Registries.ITEM.getId(ModItems.CUT_FERVIS_CRYSTAL), FERVIS_CRYSTAL);

    }
}
