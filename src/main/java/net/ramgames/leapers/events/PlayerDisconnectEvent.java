package net.ramgames.leapers.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import static net.ramgames.leapers.leaption.LeaptionManager.INSTANCE;
public class PlayerDisconnectEvent {
    public static void start(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer minecraftServer) {
        PlayerEntity player = serverPlayNetworkHandler.player;

        if(INSTANCE.isLeaping(player.getUuid())) INSTANCE.cancelLeap(minecraftServer.getOverworld(), player);
    }
}
