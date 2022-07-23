package net.ramgaming.leapers.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;

public class ModScreenHandlers {
    public static ScreenHandlerType<CrystalCutterScreenHandler> CRYSTAL_CUTTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
            new Identifier(Leapers.MOD_ID,"crystal_cutter"), CrystalCutterScreenHandler::new);
}
