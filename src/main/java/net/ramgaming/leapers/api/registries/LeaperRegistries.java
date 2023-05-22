package net.ramgaming.leapers.api.registries;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.ramgaming.leapers.api.modules.CoreModule;
import net.ramgaming.leapers.api.modules.CrystalModule;
import net.ramgaming.leapers.api.modules.FixtureModule;
import net.ramgaming.leapers.api.modules.HandleModule;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class LeaperRegistries {

    public final LeaperRegistry<CoreModule> CORES = new LeaperRegistry<>();
    public final LeaperRegistry<HandleModule> HANDLES = new LeaperRegistry<>();
    public final LeaperRegistry<FixtureModule> FIXTURES = new LeaperRegistry<>();
    public final LeaperRegistry<CrystalModule> CRYSTALS = new LeaperRegistry<>();

    public static void onInitialize() {
        // CORES INIT
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleResourceReloadListener<>() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("minecraft", "leapers");
            }

            @Override
            public CompletableFuture<Object> load(ResourceManager manager, Profiler profiler, Executor executor) {
                ResourceLoader.primaryLoad(manager);
                return null;
            }

            @Override
            public CompletableFuture<Void> apply(Object data, ResourceManager manager, Profiler profiler, Executor executor) {
                return null;
            }

            @Override
            public CompletableFuture<Void> reload(Synchronizer helper, ResourceManager manager, Profiler loadProfiler, Profiler applyProfiler, Executor loadExecutor, Executor applyExecutor) {
                ResourceLoader.primaryLoad(manager);
                return SimpleResourceReloadListener.super.reload(helper, manager, loadProfiler, applyProfiler, loadExecutor, applyExecutor);
            }
        });
    }
}
