package net.ramgames.leapers.screens;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.screens.crystalInspector.CrystalInspectorScreen;
import net.ramgames.leapers.screens.crystalInspector.CrystalInspectorScreenHandler;
import net.ramgames.leapers.screens.leapStone.LeapStoneScreen;
import net.ramgames.leapers.screens.leapStone.LeapStoneScreenHandler;

public class ModScreenManager {

    public static ScreenHandlerType<CrystalInspectorScreenHandler> CRYSTAL_INSPECTOR_SCREEN_HANDLER = new ScreenHandlerType<>(CrystalInspectorScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    public static ScreenHandlerType<LeapStoneScreenHandler> LEAP_STONE_SCREEN_HANDLER = new ScreenHandlerType<>(LeapStoneScreenHandler::new, FeatureFlags.VANILLA_FEATURES);


    public static void initScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(Leapers.MOD_ID, "crystal_inspector"), CRYSTAL_INSPECTOR_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(Leapers.MOD_ID, "leap_pad"), LEAP_STONE_SCREEN_HANDLER);
    }

    public static void initScreens() {
        HandledScreens.register(CRYSTAL_INSPECTOR_SCREEN_HANDLER, CrystalInspectorScreen::new);
        HandledScreens.register(LEAP_STONE_SCREEN_HANDLER, LeapStoneScreen::new);
    }

}
