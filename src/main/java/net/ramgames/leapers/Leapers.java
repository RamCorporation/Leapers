package net.ramgames.leapers;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.ramgames.leapers.blocks.ModBlocks;
import net.ramgames.leapers.blocks.entity.ModBlockEntities;
import net.ramgames.leapers.entities.ModServerEntities;
import net.ramgames.leapers.events.BlockBreakEvent;
import net.ramgames.leapers.events.PlaceRedstoneRayEvent;
import net.ramgames.leapers.events.PlayerDisconnectEvent;
import net.ramgames.leapers.events.ServerStoppingEvent;
import net.ramgames.leapers.items.ModItemGroups;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.screenhandlers.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Leapers implements ModInitializer {

	public static final String MOD_ID = "leapers";
	public static final Logger LOGGER = LoggerFactory.getLogger("Leapers");

	@Override
	public void onInitialize() {

		ModBlocks.registerBlocks();
		ModItems.registerModItems();
		ModItemGroups.onInitialize();
		ModTags.onInitialize();
		ModBlockEntities.onInitialize();
		ModServerNetworking.registerC2SPackets();
		ModServerEntities.registerEntities();
		ModScreenHandlers.register();

		UseBlockCallback.EVENT.register(PlaceRedstoneRayEvent::start);
		PlayerBlockBreakEvents.AFTER.register(BlockBreakEvent::start);
		ServerPlayConnectionEvents.DISCONNECT.register(PlayerDisconnectEvent::start);
		ServerLifecycleEvents.SERVER_STOPPING.register(ServerStoppingEvent::start);
	}
}