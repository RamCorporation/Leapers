package net.ramgaming.leapers.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

public class RegisterEvents {
    public static void onInitialize() {
        UseItemCallback.EVENT.register((player,world,hand) -> {
            TypedActionResult<ItemStack> currentResult = LeapEvent.start(player,world,hand);
            return currentResult;
        });
        UseBlockCallback.EVENT.register((player,world,hand, hitResult) -> {
            ActionResult currentResult = PlaceRedstoneRayEvent.start(player,world,hand,hitResult);
            return currentResult;
        });

        ServerTickEvents.START_WORLD_TICK.register((world) -> {
            SpectralCrystalCraftEvent.start(world);
        });
    }
}
