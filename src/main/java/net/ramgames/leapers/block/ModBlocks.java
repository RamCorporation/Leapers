package net.ramgames.leapers.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.block.custom.*;
import net.ramgames.leapers.item.ModItemGroups;

public class ModBlocks {

    public static final Block GALVA_CLUSTER = registerBlock("galva_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_GALVA_BUD = registerBlock("large_galva_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_GALVA_BUD = registerBlock("medium_galva_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_GALVA_BUD = registerBlock("small_galva_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);

    public static final Block ALLURE_CLUSTER = registerBlock("allure_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_ALLURE_BUD = registerBlock("large_allure_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_ALLURE_BUD = registerBlock("medium_allure_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_ALLURE_BUD = registerBlock("small_allure_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);

    public static final Block FERVIS_CLUSTER = registerBlock("fervis_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_FERVIS_BUD = registerBlock("large_fervis_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_FERVIS_BUD = registerBlock("medium_fervis_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_FERVIS_BUD = registerBlock("small_fervis_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);

    public static final Block HORA_CLUSTER = registerBlock("hora_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_HORA_BUD = registerBlock("large_hora_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_HORA_BUD = registerBlock("medium_hora_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_HORA_BUD = registerBlock("small_hora_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);

    public static final Block MEMORIA_CLUSTER = registerBlock("memoria_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_MEMORIA_BUD = registerBlock("large_memoria_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_MEMORIA_BUD = registerBlock("medium_memoria_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_MEMORIA_BUD = registerBlock("small_memoria_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);

    public static final Block AERIS_CLUSTER = registerBlock("aeris_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_AERIS_BUD = registerBlock("large_aeris_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_AERIS_BUD = registerBlock("medium_aeris_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_AERIS_BUD = registerBlock("small_aeris_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);

    public static final Block UMBER_CLUSTER = registerBlock("umber_cluster", new CrystalClusterBlock(7,3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_UMBER_BUD = registerBlock("large_umber_bud", new CrystalClusterBlock(5,3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_UMBER_BUD = registerBlock("medium_umber_bud", new CrystalClusterBlock(4,3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_UMBER_BUD = registerBlock("small_umber_bud", new CrystalClusterBlock(3,4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), true, ModItemGroups.LEAPER_GROUP);



    public static final Block BUDDING_ENDSTONE = registerBlock("budding_endstone", new BuddingEndstoneBlock(FabricBlockSettings.copyOf(Blocks.END_STONE)), false, ModItemGroups.LEAPER_GROUP);

    public static final Block CRYSTALLIZED_SCULK = registerBlock("crystallized_sculk", new Block(FabricBlockSettings.copyOf(Blocks.SCULK)), false, ModItemGroups.LEAPER_GROUP);
    public static final Block SANDY_CRYSTAL = registerBlock("sandy_crystal", new SandyCrystalBlock(FabricBlockSettings.copyOf(Blocks.SCULK)), false, ModItemGroups.LEAPER_GROUP);


    public static final Block DISLEAPER = registerBlock("disleaper", new TranslucentBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)), true, ModItemGroups.LEAPER_GROUP);
    public static final Block CACHE = registerBlock("cache", new TranslucentBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)), true, null);

    public static final Block CRYSTAL_CUTTER = registerBlock("crystal_cutter", new CrystalCutterBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)), true, ModItemGroups.LEAPER_GROUP);

    public static final Block LEAP_PAD = registerBlock("leap_pad", new LeapPadBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()), true, ModItemGroups.LEAPER_GROUP);

    public static final Block REDSTONE_RAY = registerBlock("redstone_ray",new RedstoneRayBlock(FabricBlockSettings.copyOf(Blocks.REPEATER).nonOpaque()), true, ModItemGroups.LEAPER_GROUP);
    public static final Block MIRROR = registerBlock("mirror",new MirrorBlock(FabricBlockSettings.copyOf(Blocks.GLASS).nonOpaque()), true, ModItemGroups.LEAPER_GROUP);

    private static void registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Leapers.MOD_ID,name), new BlockItem(block, new FabricItemSettings()));
        if(tab != null) ModItemGroups.appendToTab(item,tab);
    }

    private static Block registerBlock(String name,Block block, boolean transparentFeatures, ItemGroup tab) {
        registerBlockItem(name,block,tab);
        if(transparentFeatures) BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        return Registry.register(Registries.BLOCK, new Identifier(Leapers.MOD_ID,name), block);

    }
    public static void registerBlocks(){
        Leapers.LOGGER.debug("Registering Mod Blocks for "+ Leapers.MOD_ID);
    }
}
