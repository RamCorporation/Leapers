package net.ramgames.leapers.mixin.client;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.items.ModSeeds;
import net.ramgames.leapers.items.custom.LeaperItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Final
    @Shadow
    private ItemModels models;

    @Unique
    private final HashMap<ModelIdentifier, BakedModel> cachedModels = new HashMap<>();

    @Unique
    private BakedModel getAndCache(ModelIdentifier modelIdentifier) {
        BakedModel cachedModel = cachedModels.get(modelIdentifier);
        if(cachedModel == null) {
            BakedModel model = models.getModelManager().getModel(modelIdentifier);
            cachedModels.put(modelIdentifier, model);
            return model;
        }
        return cachedModel;
    }

    @Inject(method = "getModel", at=@At("HEAD"), cancellable = true)
    private void getModelOverrides(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        if(stack.getItem() == ModItems.LEAPER) leaperOverrides(stack, cir);

    }


    @Unique
    private void leaperOverrides(ItemStack stack, CallbackInfoReturnable<BakedModel> cir) {
        if(stack.getNbt() == null) return;
        NbtCompound nbt = stack.getNbt();
        if(LeaperItem.containsImproperNbt(nbt)) return;
        float modelSeed = ModSeeds.getOrGenModelSeed(
                nbt.getString("Core"),
                nbt.getString("Handle"),
                nbt.getString("Fixture"),
                nbt.getString("Crystal"));
        BakedModel model = getAndCache(new ModelIdentifier(new Identifier(Leapers.MOD_ID, "leaper_mg_"+ modelSeed),"inventory"));
        if(model == models.getModelManager().getMissingModel()) return;
        cir.setReturnValue(model);
    }

}
