package net.ramgaming.leapers.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;

public class RegisterEvents {
    public static void onInitialize() {
        UseItemCallback.EVENT.register(LeapEvent::start);
        
        UseBlockCallback.EVENT.register(PlaceRedstoneRayEvent::start);

        ServerTickEvents.START_WORLD_TICK.register(SpectralCrystalCraftEvent::start);
    }
}
