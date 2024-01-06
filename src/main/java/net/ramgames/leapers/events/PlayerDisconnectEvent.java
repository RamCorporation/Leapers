package net.ramgames.leapers.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.ramgames.leapers.leaption.LeaptionManager.INSTANCE;
public class PlayerDisconnectEvent {
    public static void start(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer minecraftServer) {
        ServerPlayerEntity player = serverPlayNetworkHandler.player;
        if(INSTANCE.isPlayerLeaping(player.getUuid())) INSTANCE.cancelLeaption(player);
    }
}
