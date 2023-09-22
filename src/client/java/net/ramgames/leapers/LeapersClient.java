package net.ramgames.leapers;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.ramgames.leapers.blocks.ModBlockRenderLayers;
import net.ramgames.leapers.events.HudRenderEvent;
import net.ramgames.leapers.screens.ModScreens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.ramgames.leapers.blocks.ModBlocks.*;

public class LeapersClient implements ClientModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("Leapers Client");

	@Override
	public void onInitializeClient() {

		ModLeaperComponents.register();
		ModCrystalInspectorTooltips.onInitialize();
		HudRenderCallback.EVENT.register(HudRenderEvent::start);
		ModClientNetworking.registerS2CPackets();
		ModScreens.register();
		ModBlockRenderLayers.registerNonFull(
				SMALL_FERVIS_BUD,
				SMALL_UMBER_BUD,
				SMALL_GALVA_BUD,
				SMALL_ALLURE_BUD,
				SMALL_HORA_BUD,
				SMALL_MEMORIA_BUD,
				SMALL_AERIS_BUD,
				MEDIUM_FERVIS_BUD,
				MEDIUM_UMBER_BUD,
				MEDIUM_GALVA_BUD,
				MEDIUM_ALLURE_BUD,
				MEDIUM_HORA_BUD,
				MEDIUM_MEMORIA_BUD,
				MEDIUM_AERIS_BUD,
				LARGE_FERVIS_BUD,
				LARGE_UMBER_BUD,
				LARGE_GALVA_BUD,
				LARGE_ALLURE_BUD,
				LARGE_HORA_BUD,
				LARGE_MEMORIA_BUD,
				LARGE_AERIS_BUD,
				LEAP_STONE,
				REDSTONE_RAY,
				ALLURE_CLUSTER,
				FERVIS_CLUSTER,
				AERIS_CLUSTER,
				GALVA_CLUSTER,
				HORA_CLUSTER,
				MEMORIA_CLUSTER,
				UMBER_CLUSTER
		);
		ModBlockRenderLayers.registerTranslucent(
				DISLEAPER,
				CRYSTAL_INSPECTOR,
				MIRROR
		);
	}
}