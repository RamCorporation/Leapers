package net.ramgames.leapers.mixin;

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
import net.ramgames.leapers.ModPredicates;
import net.ramgames.leapers.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Final
    @Shadow
    private ItemModels models;

    @Inject(method = "getModel", at=@At("HEAD"), cancellable = true)
    private void getModelOverrides(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        if(stack.getItem() != ModItems.LEAPER) return;
        if(!stack.hasNbt()) return;
        NbtCompound nbt = stack.getNbt();
        if(!nbt.contains("core") || !nbt.contains("handle") || !nbt.contains("fixture") || !nbt.contains("crystal")) return;
        float modelSeed = ModPredicates.getOrGenSeed(
                nbt.getString("core"),
                nbt.getString("handle"),
                nbt.getString("fixture"),
                nbt.getString("crystal"));
        BakedModel model = models.getModelManager().getModel(new ModelIdentifier(new Identifier(Leapers.MOD_ID, "leaper_mg_"+ modelSeed),"inventory"));
        if(model == models.getModelManager().getMissingModel()) return;
        cir.setReturnValue(model);
    }

}
