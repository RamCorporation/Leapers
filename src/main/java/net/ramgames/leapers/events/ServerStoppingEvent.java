package net.ramgames.leapers.events;

import net.minecraft.server.MinecraftServer;
import net.ramgames.leapers.leaption.LeaptionManager;

public class ServerStoppingEvent {
    public static void start(MinecraftServer minecraftServer) {
        LeaptionManager.INSTANCE.handleShutdown(minecraftServer.getOverworld());
    }
}
