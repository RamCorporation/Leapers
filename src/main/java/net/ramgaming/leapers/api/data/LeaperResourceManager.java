package net.ramgaming.leapers.api.data;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.profiler.Profiler;
import net.ramgaming.leapers.api.modules.LeaperEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

public class LeaperResourceManager<T extends LeaperEntry> implements SimpleResourceReloadListener<LeaperResourceManager.ResourceLoader<T>> {
    public static final Logger LOGGER = LoggerFactory.getLogger("Leaption API");
    private final String path;
    private final LeaperRegistry<T> registry;
    private final BiFunction<Identifier, JsonObject,Pair<Item, T>> codec;

    public LeaperResourceManager(String path, LeaperRegistry<T> registry, BiFunction<Identifier, JsonObject,Pair<Item, T>> codec) {
        this.path = path;
        this.registry = registry;
        this.codec = codec;
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("leapers", "api_" + path + "_loader");
    }

    @Override
    public CompletableFuture<ResourceLoader<T>> load(ResourceManager manager, Profiler profiler, Executor executor) {
        return CompletableFuture.supplyAsync(() -> new ResourceLoader<>(manager, profiler, path, codec), executor);
    }

    @Override
    public CompletableFuture<Void> apply(ResourceLoader<T> data, ResourceManager manager, Profiler profiler, Executor executor) {
        data.getModules().forEach(registry::register);
        return CompletableFuture.runAsync(() -> {
        });
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer helper, ResourceManager manager, Profiler loadProfiler, Profiler applyProfiler, Executor loadExecutor, Executor applyExecutor) {
        registry.emptyRegistry();
        return SimpleResourceReloadListener.super.reload(helper, manager, loadProfiler, applyProfiler, loadExecutor, applyExecutor);
    }

    @Override
    public String getName() {
        return SimpleResourceReloadListener.super.getName();
    }

    static class ResourceLoader<T> {
        private final ResourceManager manager;
        private final Profiler profiler;
        private final String path;
        private final HashMap<Item, T> modules = new HashMap<>();
        private final BiFunction<Identifier, JsonObject, Pair<Item, T>> codec;

        public ResourceLoader(ResourceManager manager, Profiler profiler, String path, BiFunction<Identifier, JsonObject,Pair<Item, T>> codec) {
            this.manager = manager;
            this.profiler = profiler;
            this.path = path;
            this.codec = codec;
            loadData();
        }

        private void loadData() {
            profiler.push("Load API_"+path);
            LOGGER.info("loading " + path + " resources");
            Map<Identifier, Resource> resources = manager.findResources("api/" + path, id -> id.getPath().endsWith(".json"));
            resources.forEach((key, value) -> {
                try {
                    Pair<Item, T> result = codec.apply(key, JsonHelper.deserialize(value.getReader()).getAsJsonObject());
                    modules.put(result.getLeft(), result.getRight());
                } catch (Exception e) {
                    LOGGER.error("failed to load resource " + key + ":" + e.getMessage());
                }

            });
            profiler.pop();
        }

        public HashMap<Item, T> getModules() {
            return this.modules;
        }
    }
}
