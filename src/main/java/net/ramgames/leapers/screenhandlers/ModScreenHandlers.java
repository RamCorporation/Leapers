package net.ramgames.leapers.screenhandlers;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;

public class ModScreenHandlers {

    public static ScreenHandlerType<CrystalInspectorScreenHandler> CRYSTAL_INSPECTOR_SCREEN_HANDLER = new ScreenHandlerType<>(CrystalInspectorScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    public static ScreenHandlerType<LeapStoneScreenHandler> LEAP_STONE_SCREEN_HANDLER = new ScreenHandlerType<>(LeapStoneScreenHandler::new, FeatureFlags.VANILLA_FEATURES);


    public static void register() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(Leapers.MOD_ID, "crystal_inspector"), CRYSTAL_INSPECTOR_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(Leapers.MOD_ID, "leap_pad"), LEAP_STONE_SCREEN_HANDLER);
    }

}
