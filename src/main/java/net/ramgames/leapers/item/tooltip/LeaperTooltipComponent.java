package net.ramgames.leapers.item.tooltip;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class LeaperTooltipComponent implements TooltipComponent {
    private final Identifier coreTextureLocation;
    private final Identifier handleTextureLocation;
    private final Identifier fixtureTextureLocation;
    private final Identifier crystalTextureLocation;
    private final int[] coreCharges;
    private final int[] handleDurability;
    private final int[] fixtureTransmittance;
    private final int[] crystalStability;

    public LeaperTooltipComponent(LeaperTooltipData tooltipData) {
        this.coreTextureLocation = tooltipData.getCoreTextureLocation();
        this.handleTextureLocation = tooltipData.getHandleTextureLocation();
        this.fixtureTextureLocation = tooltipData.getFixtureTextureLocation();
        this.crystalTextureLocation = tooltipData.getCrystalTextureLocation();
        this.coreCharges = tooltipData.getCoreCharges();
        this.handleDurability = tooltipData.getHandleDurability();
        this.fixtureTransmittance = tooltipData.getFixtureTransmittance();
        this.crystalStability = tooltipData.getCrystalStability();

    }

    @Override
    public int getHeight() {
        return 52;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        int core = textRenderer.getWidth(this.coreCharges[0]+"/"+this.coreCharges[1])+14;
        int handle = textRenderer.getWidth(this.handleDurability[0]+"/"+this.handleDurability[1])+14;
        int fixture = textRenderer.getWidth(this.fixtureTransmittance[0]+"/"+this.fixtureTransmittance[1])+14;
        int crystal = textRenderer.getWidth(this.crystalStability[0]+"/"+this.crystalStability[1])+14;
        return Math.max(core, Math.max(handle, Math.max(fixture, crystal)));
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        context.drawTexture(coreTextureLocation, x, y, 0, 0, 11, 11, 11, 11);
        context.drawTexture(handleTextureLocation, x, y+13, 0, 0, 11, 11, 11, 11);
        context.drawTexture(fixtureTextureLocation, x, y+26, 0, 0, 11, 11, 11, 11);
        context.drawTexture(crystalTextureLocation, x, y+39, 0, 0, 11, 11, 11, 11);
        TooltipComponent.super.drawItems(textRenderer, x, y, context);
    }

    @Override
    public void drawText(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {

        textRenderer.draw(Text.literal(this.coreCharges[0]+"/"+this.coreCharges[1]).formatted(Formatting.WHITE),x+14, y+2, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        textRenderer.draw(Text.literal(this.handleDurability[0]+"/"+this.handleDurability[1]).formatted(Formatting.WHITE),x+14, y+15, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        textRenderer.draw(Text.literal(this.fixtureTransmittance[0]+"/"+this.fixtureTransmittance[1]).formatted(Formatting.WHITE),x+14, y+28, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        textRenderer.draw(Text.literal(this.crystalStability[0]+"/"+this.crystalStability[1]).formatted(Formatting.WHITE),x+14, y+41, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        TooltipComponent.super.drawText(textRenderer, x, y, matrix, vertexConsumers);
    }
}
