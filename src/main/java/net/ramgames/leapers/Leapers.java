package net.ramgames.leapers;

import net.fabricmc.api.ModInitializer;
import net.ramgames.leapers.blocks.ModBlocks;
import net.ramgames.leapers.blocks.entity.ModBlockEntities;
import net.ramgames.leapers.events.RegisterEvents;
import net.ramgames.leapers.items.ModCrystalInspectorTooltips;
import net.ramgames.leapers.items.ModItemGroups;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.items.ModLeaperComponents;
import net.ramgames.leapers.networking.ModMessages;
import net.ramgames.leapers.recipes.RegisterRecipeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Leapers implements ModInitializer {
	public static final String MOD_ID = "leapers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		RegisterRecipeTypes.onInitialize();
		ModBlocks.registerBlocks();
		ModItems.registerModItems();
		ModLeaperComponents.register();
		ModCrystalInspectorTooltips.onInitialize();
		ModItemGroups.onInitialize();
		RegisterEvents.onInitialize();
		ModTags.onInitialize();
		ModBlockEntities.onInitialize();
		ModMessages.registerC2SPackets();
	}


}
