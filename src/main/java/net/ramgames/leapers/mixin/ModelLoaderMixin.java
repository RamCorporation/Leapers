package net.ramgames.leapers.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.item.ModelSeeds;
import net.ramgames.leapers.api.data.LeaperRegistries;
import net.ramgames.leapers.api.data.LeaperRegistry;
import net.ramgames.leapers.api.modules.Core;
import net.ramgames.leapers.api.modules.Crystal;
import net.ramgames.leapers.api.modules.Fixture;
import net.ramgames.leapers.api.modules.Handle;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {

    @Final
    @Shadow
    private Map<Identifier, UnbakedModel> unbakedModels;

    @Final
    @Shadow
    private Map<Identifier, UnbakedModel> modelsToBake;

    @Unique
    private final HashMap<String, JsonUnbakedModel> leaperVarients = new HashMap<>();


    @Inject(method = "getOrLoadModel", at = @At("HEAD"), cancellable = true)
    private void getOrLoadModelChecker(Identifier id, CallbackInfoReturnable<UnbakedModel> cir) {
        if(leaperVarients.containsKey(id.getPath())) cir.setReturnValue(leaperVarients.get(id.getPath()));
    }

    @Inject(method = "addModel", at = @At("HEAD"))
    private void registerModels(ModelIdentifier modelId, CallbackInfo info) {
        if (!modelId.getNamespace().equals(Leapers.MOD_ID)) return;
        if (!modelId.getVariant().equals("inventory")) return;
        if (!modelId.getPath().equals("leaper")) return;
        LeaperRegistries.CORES.keySet().forEach(coreItem -> LeaperRegistries.HANDLES.keySet().forEach(handleItem -> LeaperRegistries.FIXTURES.keySet().forEach(fixtureItem -> LeaperRegistries.CRYSTALS.keySet().forEach(crystalItem -> {
            Core core = LeaperRegistries.CORES.query(coreItem);
            Handle handle = LeaperRegistries.HANDLES.query(handleItem);
            Fixture fixture = LeaperRegistries.FIXTURES.query(fixtureItem);
            Crystal crystal = LeaperRegistries.CRYSTALS.query(crystalItem);
            float seed = ModelSeeds.getOrGenSeed(coreItem.toString(), handleItem.toString(), fixtureItem.toString(), crystalItem.toString());
            String path = "leaper_mg_" + seed;
            ModelIdentifier identifier = new ModelIdentifier(new Identifier(Leapers.MOD_ID,path),"inventory");
            JsonUnbakedModel jsonUnbakedModel = JsonUnbakedModel.deserialize(transformJson(() -> {
                JsonObject object = new JsonObject();
                object.addProperty("parent", "leapers:custom/light_leaper");
                JsonObject textures = new JsonObject();
                textures.addProperty("core", core.getLeaperTexture());
                textures.addProperty("handle", handle.getLeaperTexture());
                textures.addProperty("fixture", fixture.getLeaperTexture());
                textures.addProperty("crystal", crystal.getLeaperTexture());
                object.add("textures", textures);

                return object;
            }));


            this.unbakedModels.put(identifier, jsonUnbakedModel);
            this.modelsToBake.put(identifier, jsonUnbakedModel);
            this.leaperVarients.put(path, jsonUnbakedModel);
        }
        ))));
        LeaperRegistry.LOGGER.info("Registered {} unique leaper variants!", leaperVarients.size());
    }

    @Unique
    private String transformJson(Supplier<JsonElement> json) {
        return json.get().toString();
    }

}