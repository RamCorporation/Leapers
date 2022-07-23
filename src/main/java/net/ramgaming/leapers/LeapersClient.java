package net.ramgaming.leapers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.ramgaming.leapers.block.ModBlocks;
import net.ramgaming.leapers.screen.CrystalCutterScreen;
import net.ramgaming.leapers.screen.ModScreenHandlers;

public class LeapersClient implements ClientModInitializer {

    private static void setTranslucent(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());

    }


    @Override
    public void onInitializeClient() {
        setTranslucent(ModBlocks.CACHE);
        setTranslucent(ModBlocks.DISLEAPER);
        setTranslucent(ModBlocks.CRYSTAL_CUTTER);
        ScreenRegistry.register(ModScreenHandlers.CRYSTAL_CUTTER_SCREEN_HANDLER, CrystalCutterScreen::new);
    }


}
