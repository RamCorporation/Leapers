package net.ramgames.leapers.mixin.client;

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
import net.ramgames.leapers.items.ModSeeds;
import net.ramgames.leapers.leaption.api.data.LeaperRegistries;
import net.ramgames.leapers.leaption.api.modules.Core;
import net.ramgames.leapers.leaption.api.modules.Crystal;
import net.ramgames.leapers.leaption.api.modules.Fixture;
import net.ramgames.leapers.leaption.api.modules.Handle;
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
    private final HashMap<String, JsonUnbakedModel> leaperVariants = new HashMap<>();


    @Inject(method = "getOrLoadModel", at = @At("HEAD"), cancellable = true)
    private void getOrLoadModelChecker(Identifier id, CallbackInfoReturnable<UnbakedModel> cir) {
        if(leaperVariants.containsKey(id.getPath())) cir.setReturnValue(leaperVariants.get(id.getPath()));
    }

    @Inject(method = "addModel", at = @At("HEAD"))
    private void registerModels(ModelIdentifier modelId, CallbackInfo info) {
        if (!modelId.getNamespace().equals(Leapers.MOD_ID)) return;
        if (!modelId.getVariant().equals("inventory")) return;
        if (modelId.getPath().equals("leaper")) registerLeaperModels();
    }

    @Unique
    private void registerLeaperModels() {
        LeaperRegistries.CORES.getKeys().forEach(coreItem -> LeaperRegistries.HANDLES.getKeys().forEach(handleItem -> LeaperRegistries.FIXTURES.getKeys().forEach(fixtureItem -> LeaperRegistries.CRYSTALS.getKeys().forEach(crystalItem -> {
                    Core core = LeaperRegistries.CORES.get(coreItem);
                    Handle handle = LeaperRegistries.HANDLES.get(handleItem);
                    Fixture fixture = LeaperRegistries.FIXTURES.get(fixtureItem);
                    Crystal crystal = LeaperRegistries.CRYSTALS.get(crystalItem);
                    float seed = ModSeeds.getOrGenModelSeed(
                            LeaperRegistries.CORES.getId(core).toString(),
                            LeaperRegistries.HANDLES.getId(handle).toString(),
                            LeaperRegistries.FIXTURES.getId(fixture).toString(),
                            LeaperRegistries.CRYSTALS.getId(crystal).toString());
                    String path = "leaper_mg_" + seed;
                    ModelIdentifier identifier = new ModelIdentifier(new Identifier(Leapers.MOD_ID,path),"inventory");
                    JsonUnbakedModel jsonUnbakedModel = JsonUnbakedModel.deserialize(transformJson(() -> {
                        JsonObject object = new JsonObject();
                        object.addProperty("parent", "leapers:item/light_leaper");
                        JsonObject textures = new JsonObject();
                        textures.addProperty("core", core.getLeaperTexture().toString());
                        textures.addProperty("handle", handle.getLeaperTexture().toString());
                        textures.addProperty("fixture", fixture.getLeaperTexture().toString());
                        textures.addProperty("crystal", crystal.getLeaperTexture().toString());
                        object.add("textures", textures);

                        return object;
                    }));


                    this.unbakedModels.put(identifier, jsonUnbakedModel);
                    this.modelsToBake.put(identifier, jsonUnbakedModel);
                    this.leaperVariants.put(path, jsonUnbakedModel);
                }
        ))));
        LeaperRegistries.LOGGER.info("Registered {} unique leaper variants!", leaperVariants.size());
    }

    @Unique
    private String transformJson(Supplier<JsonElement> json) {
        return json.get().toString();
    }

}