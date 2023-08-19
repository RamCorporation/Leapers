package net.ramgames.leapers.item.tooltip;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.joml.Matrix4f;

public class SpyglassTooltipComponent implements TooltipComponent {

    private final String lensType;

    public SpyglassTooltipComponent(SpyglassTooltipData data) {
        this.lensType = data.getLensName();
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return textRenderer.getWidth(lensType+" Lens");
    }

    @Override
    public void drawText(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        textRenderer.draw(Text.literal(lensType+" Lens").formatted(Formatting.GRAY), x, y, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        TooltipComponent.super.drawText(textRenderer, x, y, matrix, vertexConsumers);
    }
}
