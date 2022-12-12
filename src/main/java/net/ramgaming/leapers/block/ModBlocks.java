package net.ramgaming.leapers.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.block.custom.CrystalCutterBlock;
import net.ramgaming.leapers.block.custom.MirrorBlock;
import net.ramgaming.leapers.block.custom.RedstoneRayBlock;
import net.ramgaming.leapers.block.custom.TranslucentBlock;
import net.ramgaming.leapers.item.ModItemGroup;

public class ModBlocks {

    public static final Block UMBER_ORE = registerBlock("umber_ore", new Block(FabricBlockSettings.of(Material.STONE).strength(2f).requiresTool()), ModItemGroup.LEAPER_GROUP);
    public static final Block AERIS_ORE = registerBlock("aeris_ore", new Block(FabricBlockSettings.of(Material.STONE).strength(2f).requiresTool()), ModItemGroup.LEAPER_GROUP);
    public static final Block FERVIS_ORE = registerBlock("fervis_ore", new Block(FabricBlockSettings.of(Material.STONE).strength(2f).requiresTool()), ModItemGroup.LEAPER_GROUP);
    public static final Block DISLEAPER = registerBlock("disleaper", new TranslucentBlock(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool().luminance(-6)), ModItemGroup.LEAPER_GROUP);
    public static final Block CACHE = registerBlock("cache", new TranslucentBlock(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool().luminance(6)), ModItemGroup.LEAPER_GROUP);

    public static final Block CRYSTAL_CUTTER = registerBlock("crystal_cutter", new CrystalCutterBlock(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()), ModItemGroup.LEAPER_GROUP);

    public static final Block REDSTONE_RAY = registerBlock("redstone_ray",new RedstoneRayBlock(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()),ModItemGroup.LEAPER_GROUP);
    public static final Block MIRROR = registerBlock("mirror",new MirrorBlock(FabricBlockSettings.of(Material.METAL).strength(2f).requiresTool().nonOpaque()),ModItemGroup.LEAPER_GROUP);

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(Leapers.MOD_ID,name), new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    private static Block registerBlock(String name,Block block, ItemGroup tab) {
        registerBlockItem(name,block,tab);
        return Registry.register(Registry.BLOCK, new Identifier(Leapers.MOD_ID,name), block);

    }
    public static void registerBlocks(){
        Leapers.LOGGER.debug("Registering Mod Blocks for "+ Leapers.MOD_ID);
    }
}
