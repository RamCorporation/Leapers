package net.ramgaming.leapers.api.data;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Pair;
import net.ramgaming.leapers.api.modules.*;
import net.ramgaming.leapers.item.ModItems;

import static net.ramgaming.leapers.api.data.LeaperRegistries.*;

public class ResourceManagers {

    public static void onInitialize() {

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("cores", CORES, (id, json) -> {
            LeaperResourceManager.LOGGER.info("loaded core resource: "+id);
            return new Pair<>(ModItems.AERIS_CRYSTAL, new CoreModule("", 0, 0, 0));
        }));
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("handles", HANDLES, (id, json) -> {
            LeaperResourceManager.LOGGER.info("loaded handle resource: "+id);
            return new Pair<>(ModItems.AERIS_CRYSTAL, new HandleModule("", 0, 0));
        }));
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("fixtures", FIXTURES, (id, json) -> {
            LeaperResourceManager.LOGGER.info("loaded fixture resource: "+id);
            return new Pair<>(ModItems.AERIS_CRYSTAL, new FixtureModule("", TransportType.DYNAMIC, 0));
        }));
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("crystals", CRYSTALS, (id, json) -> {
            LeaperResourceManager.LOGGER.info("loaded crystal resource: "+id);
            return new Pair<>(ModItems.AERIS_CRYSTAL, new CrystalModule("", CrystalType.DAY, 0));
        }));

    }
}
