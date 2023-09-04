package net.ramgames.leapers;

import net.fabricmc.api.ClientModInitializer;
import net.ramgames.leapers.blocks.entity.client.ModBlockEntityRenderers;
import net.ramgames.leapers.networking.ModMessages;
import net.ramgames.leapers.screens.ModScreenManager;

public class LeapersClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();
        ModBlockEntityRenderers.register();
        ModScreenManager.initScreenHandlers();
        ModScreenManager.initScreens();
    }


}
