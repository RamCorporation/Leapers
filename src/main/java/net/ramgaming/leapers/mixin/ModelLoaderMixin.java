package net.ramgaming.leapers.mixin;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin {
	@Inject(method = "loadModelFromJson", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;getResource(Lnet/minecraft/util/Identifier;)Lnet/minecraft/resource/Resource;"), cancellable = true)
	public void loadModelFromJson(Identifier id, CallbackInfoReturnable<JsonUnbakedModel> cir) {
		if (!Leapers.MOD_ID.equals(id.getNamespace())) return;
		if(!id.getPath().equals(ModItems.LEAPER.getName().getString())) return;
		String modelJson = "";
		if ("".equals(modelJson)) return;
		JsonUnbakedModel model = JsonUnbakedModel.deserialize(modelJson);
		model.id = id.toString();
		cir.setReturnValue(model);
	}
}
