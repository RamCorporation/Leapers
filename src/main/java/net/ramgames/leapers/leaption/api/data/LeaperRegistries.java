package net.ramgames.leapers.leaption.api.data;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.leaption.api.modules.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface LeaperRegistries {

    Logger LOGGER = LoggerFactory.getLogger("Leaption API");


    Registry<Core> CORES = registerRegistry(new Identifier(Leapers.MOD_ID,"cores"));
    Registry<Handle> HANDLES = registerRegistry(new Identifier(Leapers.MOD_ID,"handles"));
    Registry<Fixture> FIXTURES = registerRegistry(new Identifier(Leapers.MOD_ID,"fixture"));
    Registry<Crystal> CRYSTALS = registerRegistry(new Identifier(Leapers.MOD_ID,"crystals"));

    Registry<CrystalInspectorTooltip> CRYSTAL_INSPECTOR_TOOLTIP = registerRegistry(new Identifier(Leapers.MOD_ID,"crystal_inspector_tooltip"));

    private static <T> Registry<T> registerRegistry(Identifier identifier) {
        RegistryKey<Registry<T>> registryKey = RegistryKey.ofRegistry(identifier);
        return FabricRegistryBuilder.createSimple(registryKey).attribute(RegistryAttribute.SYNCED).buildAndRegister();

    }

}



