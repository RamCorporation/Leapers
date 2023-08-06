package net.ramgames.leapers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.ramgames.leapers.block.entity.ModBlockEntities;
import net.ramgames.leapers.block.entity.client.CrystalCutterBlockEntityRenderer;
import net.ramgames.leapers.networking.ModMessages;

public class LeapersClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        BlockEntityRendererRegistry.register(ModBlockEntities.CRYSTAL_CUTTER, CrystalCutterBlockEntityRenderer::new);
        //ModPredicates.register();
        //LeaperModelGenerator.generate();


    }


}
