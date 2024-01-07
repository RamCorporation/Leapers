package net.ramgames.leapers.screens;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;

import static net.ramgames.leapers.screenhandlers.ModScreenHandlers.*;

public class ModScreens {

    public static void register() {

        HandledScreens.register(CRYSTAL_INSPECTOR_SCREEN_HANDLER, CrystalInspectorScreen::new);
        HandledScreens.register(LEAP_STONE_SCREEN_HANDLER, LeapStoneScreen::new);
        HandledScreens.register(FUSION_TABLE_SCREEN_HANDLER, FusionTableScreen::new);

    }

}
