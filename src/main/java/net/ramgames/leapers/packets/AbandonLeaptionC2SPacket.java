package net.ramgames.leapers.packets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.leaption.LeaptionManager;

import java.util.UUID;

import static net.ramgames.leapers.leaption.LeaptionManager.INSTANCE;


public class AbandonLeaptionC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        Leapers.LOGGER.info("abandon!");
        UUID playerUUID = player.getUuid();
        if(INSTANCE.isPlayerLeaping(playerUUID)) INSTANCE.cancelLeaption(server, player);

    }
}
