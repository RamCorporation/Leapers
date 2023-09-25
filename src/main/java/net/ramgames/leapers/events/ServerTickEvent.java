package net.ramgames.leapers.events;

import net.minecraft.server.MinecraftServer;
import net.ramgames.leapers.leaption.LeaptionManager;

public class ServerTickEvent {
    public static void start(MinecraftServer server) {
        LeaptionManager.INSTANCE.tick(server);
    }
}
