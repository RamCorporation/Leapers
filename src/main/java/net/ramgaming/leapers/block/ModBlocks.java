package net.ramgaming.leapers.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.block.custom.*;
import net.ramgaming.leapers.item.ModItemGroups;

public class ModBlocks {

    public static final Block UMBER_ORE = registerBlock("umber_ore", new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);
    public static final Block AERIS_ORE = registerBlock("aeris_ore", new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);
    public static final Block FERVIS_ORE = registerBlock("fervis_ore", new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);
    public static final Block GALVA_ORE = registerBlock("galva_ore", new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);
    public static final Block HORA_ORE = registerBlock("hora_ore", new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);
    public static final Block MEMORIA_ORE = registerBlock("memoria_ore", new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);
    public static final Block ALLURE_ORE = registerBlock("allure_ore", new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)), ModItemGroups.LEAPER_GROUP);

    public static final Block DISLEAPER = registerBlock("disleaper", new TranslucentBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)), ModItemGroups.LEAPER_GROUP);
    public static final Block CACHE = registerBlock("cache", new TranslucentBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)), null);

    public static final Block CRYSTAL_CUTTER = registerBlock("crystal_cutter", new CrystalCutterBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)), ModItemGroups.LEAPER_GROUP);

    public static final Block LEAP_PAD = registerBlock("leap_pad", new LeapPadBlock(FabricBlockSettings.copyOf(Blocks.STONE).nonOpaque()), ModItemGroups.LEAPER_GROUP);

    public static final Block REDSTONE_RAY = registerBlock("redstone_ray",new RedstoneRayBlock(FabricBlockSettings.copyOf(Blocks.REPEATER).nonOpaque()), ModItemGroups.LEAPER_GROUP);
    public static final Block MIRROR = registerBlock("mirror",new MirrorBlock(FabricBlockSettings.copyOf(Blocks.GLASS).nonOpaque()), ModItemGroups.LEAPER_GROUP);

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Leapers.MOD_ID,name), new BlockItem(block, new FabricItemSettings()));
        if(tab != null) ModItemGroups.appendToTab(item,tab);
        return item;
    }

    private static Block registerBlock(String name,Block block, ItemGroup tab) {
        registerBlockItem(name,block,tab);
        return Registry.register(Registries.BLOCK, new Identifier(Leapers.MOD_ID,name), block);

    }
    public static void registerBlocks(){
        Leapers.LOGGER.debug("Registering Mod Blocks for "+ Leapers.MOD_ID);
    }
}
