package net.ramgaming.leapers.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.networking.packet.ExampleC2SPacket;

public class ModMessages {
    public static final Identifier EXAMPLE_ID = new Identifier(Leapers.MOD_ID,"example");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
    }

    public static void registerS2CPackets() {

    }

}
