package net.ramgames.leapers.mixin.client;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;
import net.ramgames.leapers.leaption.LeaptionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {

    @ModifyVariable(method = "render*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/entity/LivingEntity;ZZZ)Lnet/minecraft/client/render/RenderLayer;"), name = "bl2")
    private boolean modifyTranslucency(boolean original, LivingEntity livingEntity) {
        Boolean val = null;
        if(livingEntity instanceof LeapGhostEntity) val = true;
        if(livingEntity instanceof PlayerEntity player) val = LeaptionManager.INSTANCE.isLeaping(player.getUuid());
        return Boolean.TRUE.equals(val);

    }

    @ModifyConstant(method = "render*", constant = @Constant(floatValue = 0.15F, ordinal = 0))
    private float modifyTranslucency(float original, LivingEntity livingEntity) {
        //Leapers.LOGGER.info("modified");
        if(livingEntity instanceof PlayerEntity player) return 1f - LeaptionManager.INSTANCE.getLeaption(player.getUuid()).getDischargeProgress();
        if(livingEntity instanceof LeapGhostEntity leapGhost) return LeaptionManager.INSTANCE.getLeaption(leapGhost.player.getUuid()).getDischargeProgress();

        return original;
    }
}
