package net.ramgames.leapers.blocks;

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
import net.ramgames.leapers.sounds.ModSoundGroups;
import net.ramgames.leapers.blocks.custom.*;
import net.ramgames.leapers.items.ModItemGroups;

public class ModBlocks {

    public static final Block GALVA_CLUSTER = registerNonFullBlock("galva_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_GALVA_BUD = registerNonFullBlock("large_galva_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_GALVA_BUD = registerNonFullBlock("medium_galva_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_GALVA_BUD = registerNonFullBlock("small_galva_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);

    public static final Block ALLURE_CLUSTER = registerNonFullBlock("allure_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_ALLURE_BUD = registerNonFullBlock("large_allure_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_ALLURE_BUD = registerNonFullBlock("medium_allure_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_ALLURE_BUD = registerNonFullBlock("small_allure_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);

    public static final Block FERVIS_CLUSTER = registerNonFullBlock("fervis_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_FERVIS_BUD = registerNonFullBlock("large_fervis_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_FERVIS_BUD = registerNonFullBlock("medium_fervis_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_FERVIS_BUD = registerNonFullBlock("small_fervis_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);

    public static final Block HORA_CLUSTER = registerNonFullBlock("hora_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_HORA_BUD = registerNonFullBlock("large_hora_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_HORA_BUD = registerNonFullBlock("medium_hora_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_HORA_BUD = registerNonFullBlock("small_hora_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);

    public static final Block MEMORIA_CLUSTER = registerNonFullBlock("memoria_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_MEMORIA_BUD = registerNonFullBlock("large_memoria_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_MEMORIA_BUD = registerNonFullBlock("medium_memoria_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_MEMORIA_BUD = registerNonFullBlock("small_memoria_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);

    public static final Block AERIS_CLUSTER = registerNonFullBlock("aeris_cluster", new CrystalClusterBlock(7,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_AERIS_BUD = registerNonFullBlock("large_aeris_bud", new CrystalClusterBlock(5,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_AERIS_BUD = registerNonFullBlock("medium_aeris_bud", new CrystalClusterBlock(4,3,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_AERIS_BUD = registerNonFullBlock("small_aeris_bud", new CrystalClusterBlock(3,4,FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);

    public static final Block UMBER_CLUSTER = registerNonFullBlock("umber_cluster", new CrystalClusterBlock(7,3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block LARGE_UMBER_BUD = registerNonFullBlock("large_umber_bud", new CrystalClusterBlock(5,3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEDIUM_UMBER_BUD = registerNonFullBlock("medium_umber_bud", new CrystalClusterBlock(4,3, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);
    public static final Block SMALL_UMBER_BUD = registerNonFullBlock("small_umber_bud", new CrystalClusterBlock(3,4, FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)), ModItemGroups.LEAPER_GROUP);



    public static final Block BUDDING_ENDSTONE = registerBlock("budding_endstone", new BuddingEndstoneBlock(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);

    public static final Block CRYSTALLIZED_SCULK = registerBlock("crystallized_sculk", new CrystallizedSculkBlock(FabricBlockSettings.copyOf(Blocks.SCULK)), ModItemGroups.LEAPER_GROUP);
    public static final Block SANDY_CLUSTER = registerNonFullBlock("sandy_cluster", new SandyClusterBlock(FabricBlockSettings.copyOf(Blocks.SAND)), ModItemGroups.LEAPER_GROUP);


    public static final Block DISLEAPER = registerTransparentBlock("disleaper", new DisleaperBlock(FabricBlockSettings.copyOf(Blocks.BEACON).luminance(0).sounds(ModSoundGroups.DISLEAPER)), ModItemGroups.LEAPER_GROUP);
    public static final Block CRYSTAL_INSPECTOR = registerTransparentBlock("crystal_inspector", new CrystalInspectorBlock(FabricBlockSettings.copyOf(Blocks.CHISELED_QUARTZ_BLOCK).nonOpaque().luminance(7)), ModItemGroups.LEAPER_GROUP);

    public static final Block LEAP_STONE = registerNonFullBlock("leap_stone", new LeapStoneBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()), ModItemGroups.LEAPER_GROUP);

    public static final Block REDSTONE_RAY = registerNonFullBlock("redstone_ray",new RedstoneRayBlock(FabricBlockSettings.copyOf(Blocks.REPEATER).nonOpaque()), ModItemGroups.LEAPER_GROUP);
    public static final Block MIRROR = registerTransparentBlock("mirror",new MirrorBlock(FabricBlockSettings.copyOf(Blocks.GLASS).nonOpaque()), ModItemGroups.LEAPER_GROUP);

    private static void registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Leapers.MOD_ID,name), new BlockItem(block, new FabricItemSettings()));
        if(tab != null) ModItemGroups.appendToTab(item,tab);
    }

    private static Block registerBlock(String name,Block block, ItemGroup tab) {
        registerBlockItem(name,block,tab);
        return Registry.register(Registries.BLOCK, new Identifier(Leapers.MOD_ID,name), block);

    }
    private static Block registerTransparentBlock(String name, Block block, ItemGroup tab) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
        return registerBlock(name, block, tab);
    }

    private static Block registerNonFullBlock(String name, Block block, ItemGroup tab) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        return registerBlock(name, block, tab);
    }


    public static void registerBlocks(){
        Leapers.LOGGER.debug("Registering Mod Blocks for "+ Leapers.MOD_ID);
    }
}
