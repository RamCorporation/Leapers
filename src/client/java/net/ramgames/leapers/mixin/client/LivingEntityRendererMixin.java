package net.ramgames.leapers.mixin.client;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.ramgames.leapers.LeapersClient;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;
import net.ramgames.leapers.leaption.ClientLeaptionManager;
import net.ramgames.leapers.leaption.LeaptionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {

    @ModifyVariable(method = "render*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getRenderLayer(Lnet/minecraft/entity/LivingEntity;ZZZ)Lnet/minecraft/client/render/RenderLayer;"), name = "bl2")
    private boolean shouldModifyTranslucency(boolean original, LivingEntity livingEntity) {
        if(livingEntity instanceof PlayerEntity player) return LeaptionManager.INSTANCE.isPlayerLeaping(player.getUuid());
        if(livingEntity instanceof LeapGhostEntity) {
            return true;
        }
        return original;

    }

    @ModifyConstant(method = "render*", constant = @Constant(floatValue = 0.15F))
    private float modifyTranslucency(float original, LivingEntity livingEntity) {
        if(livingEntity instanceof PlayerEntity player && LeaptionManager.INSTANCE.isPlayerLeaping(player.getUuid())) return 1 - LeaptionManager.INSTANCE.getPlayerLeapProgress(player.getUuid());
        if(livingEntity instanceof LeapGhostEntity leapGhost) {
            LeapersClient.LOGGER.info(LeaptionManager.INSTANCE.playersLeaping().toString());
            LeapersClient.LOGGER.info("progress: "+LeaptionManager.INSTANCE.getPlayerLeapProgress(leapGhost.player.getUuid()));
            return LeaptionManager.INSTANCE.getPlayerLeapProgress(leapGhost.player.getUuid());
        }

        return original;
    }
}
