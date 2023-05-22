package net.ramgaming.leapers.api.registries;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.resource.ResourceManager;

import java.io.IOException;

public class ResourceLoader {
    public static void primaryLoad(ResourceManager resourceManager) {
        resourceManager.findResources("minecraft/leapers", path -> path.getPath().endsWith(".json")).forEach((id, json) -> {

            if(resourceManager.getResource(id).isPresent()) {
                try {
                    Gson thing=  new Gson(); thing.fromJson(json.getInputStream().toString(), JsonObject.class);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
