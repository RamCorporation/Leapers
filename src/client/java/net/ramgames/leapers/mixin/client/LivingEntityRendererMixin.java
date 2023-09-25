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
    private boolean modifyTranslucency(boolean original, LivingEntity livingEntity) {
        boolean val = livingEntity instanceof LeapGhostEntity;
        if(livingEntity instanceof PlayerEntity player) val = ClientLeaptionManager.INSTANCE.isLeaping();
        if(livingEntity.getRandom().nextBoolean()) LeapersClient.LOGGER.info("{}", val);
        return val;

    }

    @ModifyConstant(method = "render*", constant = @Constant(floatValue = 0.15F, ordinal = 0))
    private float modifyTranslucency(float original, LivingEntity livingEntity) {
        if(livingEntity instanceof PlayerEntity player) {
            return 1 - ClientLeaptionManager.INSTANCE.getLeapProgress();
        }
        if(livingEntity instanceof LeapGhostEntity leapGhost) {
            LeapersClient.LOGGER.info("modifying ghost");
            return ClientLeaptionManager.INSTANCE.getLeapProgress();
        }

        return original;
    }
}
