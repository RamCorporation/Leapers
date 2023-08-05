package net.ramgames.leapers.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.networking.packet.*;

public class ModMessages {
    public static final Identifier ITEM_SYNC = new Identifier(Leapers.MOD_ID,"item_sync");

    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
    }

}
