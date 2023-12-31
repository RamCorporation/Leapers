package net.ramgames.leapers.packets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.ramgames.leapers.LeapersClient;
import net.ramgames.leapers.ModNetworking;
import net.ramgames.leapers.leaption.ClientLeaptionManager;

public class LeaptionAgreementS2CPacket {

    public static void receive(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf byteBuf, PacketSender packetSender) {
        LeapersClient.LOGGER.info("agreement");
        if(byteBuf.readBoolean()) ClientLeaptionManager.INSTANCE.startLeap(byteBuf.readVarInt());
    }
}
