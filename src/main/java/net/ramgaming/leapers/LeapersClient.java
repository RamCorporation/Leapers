package net.ramgaming.leapers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.ramgaming.leapers.block.ModBlocks;
import net.ramgaming.leapers.screen.ModScreenHandlers;
import net.ramgaming.leapers.screen.SkyGazerScreen;

public class LeapersClient implements ClientModInitializer {

    private static void setTranslucent(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());

    }


    @Override
    public void onInitializeClient() {
        setTranslucent(ModBlocks.CACHE);
        setTranslucent(ModBlocks.DISLEAPER);
        ScreenRegistry.register(ModScreenHandlers.STAR_GAZER_SCREEN_HANDLER, SkyGazerScreen::new);
    }
}
