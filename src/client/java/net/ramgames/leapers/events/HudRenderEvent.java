package net.ramgames.leapers.events;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.ramgames.leapers.leaption.ClientLeaptionManager;
import net.ramgames.leapers.leaption.LeaptionManager;
import net.ramgames.leapers.mixin.client.GameRendererAccessor;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public interface HudRenderEvent {

    static void start(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        if(client.player == null) return;
        if(!ClientLeaptionManager.INSTANCE.isLeaping()) return;
        GameRendererAccessor gameRendererAccessor = (GameRendererAccessor) client.gameRenderer;
        GameRenderer gameRenderer = MinecraftClient.getInstance().gameRenderer;
        Camera camera = new Camera();
        MatrixStack matrixStack = new MatrixStack();
        double d = gameRendererAccessor.invokeGetFov(camera, v, true);
        matrixStack.multiplyPositionMatrix(gameRendererAccessor.invokeGetBasicProjectionMatrix(d));
        gameRendererAccessor.invokeTiltViewWhenHurt(matrixStack, v);
        if (client.options.getBobView().getValue()) gameRendererAccessor.invokeBobView(matrixStack, v);

        MatrixStack matrices = new MatrixStack();
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(camera.getPitch()));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw() + 180.0F));
        float f = client.options.getDistortionEffectScale().getValue().floatValue();
        float g = MathHelper.lerp(v, client.player.prevNauseaIntensity, client.player.nauseaIntensity) * f * f;
        if (g > 0.0F) {
            int i = client.player.hasStatusEffect(StatusEffects.NAUSEA) ? 7 : 20;
            float h = 5.0F / (g * g + 5.0F) - g * 0.04F;
            h *= h;
            RotationAxis rotationAxis = RotationAxis.of(new Vector3f(0.0F, MathHelper.SQUARE_ROOT_OF_TWO / 2.0F, MathHelper.SQUARE_ROOT_OF_TWO / 2.0F));
            matrixStack.multiply(rotationAxis.rotationDegrees(((float)((GameRendererAccessor) gameRenderer).getTicks() + v) * (float)i));
            matrixStack.scale(1.0F / h, 1.0F, 1.0F);
            float j = -((float)((GameRendererAccessor) gameRenderer).getTicks() + v) * (float)i;
            matrixStack.multiply(rotationAxis.rotationDegrees(j));

        }

        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        camera.update(MinecraftClient.getInstance().world, ClientLeaptionManager.INSTANCE.getLeapGhost(), false, false, v);
        client.worldRenderer.setupFrustum(matrices, camera.getPos(), gameRendererAccessor.invokeGetBasicProjectionMatrix(Math.max(d, (double) client.options.getFov().getValue())));
        client.worldRenderer.render(matrices, v, Util.getMeasuringTimeNano(), false, camera, client.gameRenderer, gameRendererAccessor.getLightmapTextureManager(), matrix4f);

    }
}
