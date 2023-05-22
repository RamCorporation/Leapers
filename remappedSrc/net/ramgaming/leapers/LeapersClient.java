package net.ramgaming.leapers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.ramgaming.leapers.block.ModBlocks;
import net.ramgaming.leapers.block.entity.ModBlockEntities;
import net.ramgaming.leapers.block.entity.client.CrystalCutterBlockEntityRenderer;
import net.ramgaming.leapers.networking.ModMessages;

public class LeapersClient implements ClientModInitializer {

    private static void setTranslucent(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());

    }


    @Override
    public void onInitializeClient() {
        setTranslucent(ModBlocks.CACHE);
        setTranslucent(ModBlocks.DISLEAPER);
        setTranslucent(ModBlocks.CRYSTAL_CUTTER);
        setTranslucent(ModBlocks.REDSTONE_RAY);
        setTranslucent(ModBlocks.MIRROR);
        ModMessages.registerS2CPackets();
        BlockEntityRendererRegistry.register(ModBlockEntities.CRYSTAL_CUTTER, CrystalCutterBlockEntityRenderer::new);
        ModPredicates.register();
    }


}
