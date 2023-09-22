package net.ramgames.leapers.mixin.client;

import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.TooltipData;
import net.ramgames.leapers.items.tooltip.LeaperTooltipComponent;
import net.ramgames.leapers.items.tooltip.LeaperTooltipData;
import net.ramgames.leapers.items.tooltip.SpyglassTooltipComponent;
import net.ramgames.leapers.items.tooltip.SpyglassTooltipData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TooltipComponent.class)
public interface TooltipComponentMixin {

    @Inject(method = "of(Lnet/minecraft/client/item/TooltipData;)Lnet/minecraft/client/gui/tooltip/TooltipComponent;", at = @At("HEAD"), cancellable = true)
    private static void ofMixin(TooltipData data, CallbackInfoReturnable<TooltipComponent> info) {
        if (data instanceof LeaperTooltipData) info.setReturnValue(new LeaperTooltipComponent((LeaperTooltipData) data));
        else if (data instanceof SpyglassTooltipData) info.setReturnValue(new SpyglassTooltipComponent((SpyglassTooltipData) data));
    }
}
