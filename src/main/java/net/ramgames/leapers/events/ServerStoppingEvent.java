package net.ramgames.leapers.events;

import com.mojang.authlib.Environment;
import net.minecraft.server.MinecraftServer;
import net.ramgames.leapers.leaption.LeaptionManager;

public class ServerStoppingEvent {
    public static void start(MinecraftServer server) {
        LeaptionManager.INSTANCE.handleShutdown(server);
    }
}
