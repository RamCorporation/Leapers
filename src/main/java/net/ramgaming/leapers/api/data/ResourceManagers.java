package net.ramgaming.leapers.api.data;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.ramgaming.leapers.api.modules.*;

import static net.ramgaming.leapers.api.data.LeaperRegistries.*;

public class ResourceManagers {

    public static void onInitialize() {

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("cores", CORES, (id, json) -> {
            JsonObject properties = json.getAsJsonObject("properties");
            String[] split = id.getPath().split("/");
            return new Pair<>(Registries.ITEM.get(new Identifier(properties.get("item").getAsString())), new CoreEntry(
                    split[split.length - 1].replace(".json", ""),
                    properties.get("texture").getAsString(),
                    properties.get("charges").getAsInt(),
                    properties.get("chargeTime").getAsInt(),
                    properties.get("dischargeTime").getAsInt()
            ));
        }));
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("handles", HANDLES, (id, json) -> {
            JsonObject properties = json.getAsJsonObject("properties");
            String[] split = id.getPath().split("/");
            return new Pair<>(Registries.ITEM.get(new Identifier(properties.get("item").getAsString())), new HandleEntry(
                    split[split.length - 1].replace(".json", ""),
                    properties.get("texture").getAsString(),
                    properties.get("maxRange").getAsInt(),
                    properties.get("durability").getAsInt()
            ));
        }));
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("fixtures", FIXTURES, (id, json) -> {
            JsonObject properties = json.getAsJsonObject("properties");
            String[] split = id.getPath().split("/");
            return new Pair<>(Registries.ITEM.get(new Identifier(properties.get("item").getAsString())), new FixtureEntry(
                    split[split.length - 1].replace(".json", ""),
                    properties.get("texture").getAsString(),
                    TransportType.fromString(properties.get("type").getAsString()),
                    properties.get("durability").getAsInt()
            ));
        }));
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LeaperResourceManager<>("crystals", CRYSTALS, (id, json) -> {
            JsonObject properties = json.getAsJsonObject("properties");
            String[] split = id.getPath().split("/");
            return new Pair<>(Registries.ITEM.get(new Identifier(properties.get("item").getAsString())), new CrystalEntry(
                    split[split.length - 1].replace(".json", ""),
                    properties.get("texture").getAsString(),
                    CrystalType.fromString(properties.get("type").getAsString()),
                    properties.get("durability").getAsInt()
            ));
        }));
    }
}
