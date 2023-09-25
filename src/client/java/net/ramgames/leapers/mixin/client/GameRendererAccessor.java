package net.ramgames.leapers.mixin.client;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRenderer.class)
public interface GameRendererAccessor {

    @Accessor(value = "lightmapTextureManager")
    LightmapTextureManager getLightmapTextureManager();

    @Accessor(value = "ticks")
    int getTicks();

    @Invoker(value = "getFov")
    double invokeGetFov(Camera camera, float tickDelta, boolean changingFov);

    @Invoker(value = "getBasicProjectionMatrix")
    Matrix4f invokeGetBasicProjectionMatrix(double fov);

    @Invoker(value = "tiltViewWhenHurt")
    void invokeTiltViewWhenHurt(MatrixStack matrices, float tickDelta);

    @Invoker(value = "bobView")
    void invokeBobView(MatrixStack matrices, float tickDelta);

}
