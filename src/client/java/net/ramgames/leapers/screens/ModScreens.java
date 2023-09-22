package net.ramgames.leapers.screens;

import net.minecraft.client.gui.screen.ingame.HandledScreens;

import static net.ramgames.leapers.screenhandlers.ModScreenHandlers.CRYSTAL_INSPECTOR_SCREEN_HANDLER;
import static net.ramgames.leapers.screenhandlers.ModScreenHandlers.LEAP_STONE_SCREEN_HANDLER;

public class ModScreens {

    public static void register() {

        HandledScreens.register(CRYSTAL_INSPECTOR_SCREEN_HANDLER, CrystalInspectorScreen::new);
        HandledScreens.register(LEAP_STONE_SCREEN_HANDLER, LeapStoneScreen::new);

    }

}
