package net.ramgames.leapers.screen;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.screen.crystalInspector.CrystalInspectorScreen;
import net.ramgames.leapers.screen.crystalInspector.CrystalInspectorScreenHandler;

public class ModScreenManager {

    public static ScreenHandlerType<CrystalInspectorScreenHandler> CRYSTAL_INSPECTOR_SCREEN_HANDLER = new ScreenHandlerType<>(CrystalInspectorScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void initScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier("leapers:crystal_inspector"), CRYSTAL_INSPECTOR_SCREEN_HANDLER);
    }

    public static void initScreens() {
        HandledScreens.register(CRYSTAL_INSPECTOR_SCREEN_HANDLER, CrystalInspectorScreen::new);
    }

}
