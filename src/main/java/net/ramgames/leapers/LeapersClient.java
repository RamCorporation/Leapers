package net.ramgames.leapers;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.ramgames.leapers.block.entity.ModBlockEntities;
import net.ramgames.leapers.block.entity.client.CrystalInspectorBlockEntityRenderer;
import net.ramgames.leapers.networking.ModMessages;
import net.ramgames.leapers.screen.ModScreenManager;

public class LeapersClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        BlockEntityRendererFactories.register(ModBlockEntities.CRYSTAL_INSPECTOR, CrystalInspectorBlockEntityRenderer::new);
        ModScreenManager.initScreenHandlers();
        ModScreenManager.initScreens();

    }


}
