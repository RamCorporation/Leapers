package net.ramgames.leapers.events;

import net.minecraft.client.gui.DrawContext;

public interface HudRenderEvent {

    static void start(DrawContext drawContext, float v) {
        /*
        RenderSystem.enableBlend();
        int k = 64 << 24 & -16777216;
        drawContext.fill(0,0, drawContext.getScaledWindowWidth(), drawContext.getScaledWindowHeight(), 16777215 | k);
        RenderSystem.disableBlend();*/
    }
}
