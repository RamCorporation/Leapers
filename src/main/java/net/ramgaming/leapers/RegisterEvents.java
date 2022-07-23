package net.ramgaming.leapers;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.util.math.MatrixStack;
import net.ramgaming.leapers.procedures.Leap;

public class RegisterEvents {
    public static void onInitialize() {
        UseItemCallback.EVENT.register((player,world,hand) -> {
            return Leap.Start(player,world,hand);
        });
        HudRenderCallback.EVENT.register((MatrixStack matrixStack, float tickDelta)-> {

        });
    }
}
