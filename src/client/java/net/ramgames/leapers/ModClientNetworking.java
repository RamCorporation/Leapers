package net.ramgames.leapers;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.ramgames.leapers.packets.ItemStackSyncS2CPacket;

import static net.ramgames.leapers.ModServerNetworking.ITEM_SYNC;

public class ModClientNetworking {

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
    }
}
