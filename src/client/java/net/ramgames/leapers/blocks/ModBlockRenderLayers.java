package net.ramgames.leapers.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public interface ModBlockRenderLayers {

    static void registerTranslucent(Block... blocks) {
        for(Block block : blocks) BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
    }

    static void registerNonFull(Block... blocks) {
        for(Block block : blocks) BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
    }

}
