package net.ramgames.leapers;

import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.leaption.api.data.LeaperRegistries;
import net.ramgames.leapers.leaption.api.modules.*;

public class ModLeaperComponents {

    public static final Core AMETHYST_CORE = new Core(new Identifier(Leapers.MOD_ID, "item/cores/amethyst_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/cores/amethyst_core"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/cores/amethyst_core.png"),6, 1200, 20);
    public static final Core DIAMOND_CORE = new Core(new Identifier(Leapers.MOD_ID, "item/cores/diamond_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/cores/diamond_core"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/cores/diamond_core.png"),3, 300, 10);
    public static final Core HEART_OF_THE_SEA_CORE = new Core(new Identifier(Leapers.MOD_ID, "item/cores/heart_of_the_sea_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/cores/heart_of_the_sea_core"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/cores/heart_of_the_sea_core.png"),24, 1800, 10);
    public static final Core NETHERITE_CORE = new Core(new Identifier(Leapers.MOD_ID, "item/cores/netherite_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/cores/netherite_core"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/cores/netherite_core.png"),12, 1500, 15);
    public static final Core STAR_CORE = new Core(new Identifier(Leapers.MOD_ID, "item/cores/star_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/cores/star_core"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/cores/star_core.png"),48, 3600, 35);

    public static final Handle WOODEN_HANDLE = new Handle(new Identifier(Leapers.MOD_ID, "item/handles/wooden_handle"), new Identifier("textures/item/stick"),64, 4);
    public static final Handle IRON_HANDLE = new Handle(new Identifier(Leapers.MOD_ID, "item/handles/iron_handle"), new Identifier("textures/block/chain"),96, 8);
    public static final Handle COPPER_HANDLE = new Handle(new Identifier(Leapers.MOD_ID, "item/handles/copper_handle"), new Identifier("textures/block/lightning_rod"),128, 16);
    public static final Handle BLAZE_ROD_HANDLE = new Handle(new Identifier(Leapers.MOD_ID, "item/handles/blaze_rod_handle"), new Identifier("textures/item/blaze_rod"),160, 24);
    public static final Handle END_ROD_HANDLE = new Handle(new Identifier(Leapers.MOD_ID, "item/handles/end_rod_handle"), new Identifier("textures/block/end_rod"),64, 4);

    public static final Fixture GLASS_FIXTURE = new Fixture(new Identifier(Leapers.MOD_ID, "item/fixtures/glass_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/fixtures/glass_fixture"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/fixtures/glass_fixture.png"), FixtureType.FIXED, 1);
    public static final Fixture GOLD_FIXTURE = new Fixture(new Identifier(Leapers.MOD_ID, "item/fixtures/gold_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/fixtures/gold_fixture"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/fixtures/gold_fixture.png"), FixtureType.RELATIVE, 4);
    public static final Fixture MEMORIA_FIXTURE = new Fixture(new Identifier(Leapers.MOD_ID, "item/fixtures/memoria_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/fixtures/memoria_fixture"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/fixtures/memoria_fixture.png"), FixtureType.DYNAMIC, 8);
    public static final Fixture QUARTZ_FIXTURE = new Fixture(new Identifier(Leapers.MOD_ID, "item/fixtures/quartz_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/fixtures/quartz_fixture"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/fixtures/quartz_fixture.png"), FixtureType.FIXED, 10);

    public static final Crystal AERIS_CRYSTAL = new Crystal(new Identifier(Leapers.MOD_ID, "item/crystals/aeris_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/crystals/aeris_crystal"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/crystals/aeris_crystal.png"), CrystalType.DAY, 32);
    public static final Crystal ALLURE_CRYSTAL = new Crystal(new Identifier(Leapers.MOD_ID, "item/crystals/allure_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/crystals/allure_crystal"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/crystals/allure_crystal.png"), CrystalType.REDSTONE, 8);
    public static final Crystal FERVIS_CRYSTAL = new Crystal(new Identifier(Leapers.MOD_ID, "item/crystals/fervis_leaper"), new Identifier(Leapers.MOD_ID, "textures/item/crystals/fervis_crystal"), new Identifier(Leapers.MOD_ID, "textures/block/leap_stone/crystals/fervis_crystal.png"), CrystalType.NIGHT, 16);

    
    public static void register() {
        Registry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.AMETHYST_CORE), AMETHYST_CORE);
        Registry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.DIAMOND_CORE), DIAMOND_CORE);
        Registry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.HEART_OF_THE_SEA_CORE), HEART_OF_THE_SEA_CORE);
        Registry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.NETHERITE_CORE), NETHERITE_CORE);
        Registry.register(LeaperRegistries.CORES, Registries.ITEM.getId(ModItems.STAR_CORE), STAR_CORE);

        Registry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.STICK), WOODEN_HANDLE);
        Registry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.CHAIN), IRON_HANDLE);
        Registry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.LIGHTNING_ROD), COPPER_HANDLE);
        Registry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.BLAZE_ROD), BLAZE_ROD_HANDLE);
        Registry.register(LeaperRegistries.HANDLES, Registries.ITEM.getId(Items.END_ROD), END_ROD_HANDLE);

        Registry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.GLASS_FIXTURE), GLASS_FIXTURE);
        Registry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.GOLD_FIXTURE), GOLD_FIXTURE);
        Registry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.MEMORIA_FIXTURE), MEMORIA_FIXTURE);
        Registry.register(LeaperRegistries.FIXTURES, Registries.ITEM.getId(ModItems.QUARTZ_FIXTURE), QUARTZ_FIXTURE);

        Registry.register(LeaperRegistries.CRYSTALS, Registries.ITEM.getId(ModItems.CUT_AERIS_CRYSTAL), AERIS_CRYSTAL);
        Registry.register(LeaperRegistries.CRYSTALS, Registries.ITEM.getId(ModItems.CUT_ALLURE_CRYSTAL), ALLURE_CRYSTAL);
        Registry.register(LeaperRegistries.CRYSTALS, Registries.ITEM.getId(ModItems.CUT_FERVIS_CRYSTAL), FERVIS_CRYSTAL);

    }
}
