package net.ramgames.leapers.items.tooltip;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class LeaperTooltipComponent implements TooltipComponent {
    private Identifier coreTextureLocation;
    private Identifier handleTextureLocation;
    private Identifier fixtureTextureLocation;
    private Identifier crystalTextureLocation;

    private String coreText;
    private String handleText;
    private String fixtureText;
    private String crystalText;
    private final boolean isPreview;

    public LeaperTooltipComponent(LeaperTooltipData tooltipData) {
        this.isPreview = tooltipData.isPreview();
        if(this.isPreview) return;
        this.coreTextureLocation = tooltipData.getCoreTextureLocation();
        this.handleTextureLocation = tooltipData.getHandleTextureLocation();
        this.fixtureTextureLocation = tooltipData.getFixtureTextureLocation();
        this.crystalTextureLocation = tooltipData.getCrystalTextureLocation();
        int[] coreCharges = tooltipData.getCoreCharges();
        int[] handleDurability = tooltipData.getHandleDurability();
        int[] fixtureTransmittance = tooltipData.getFixtureTransmittance();
        int[] crystalStability = tooltipData.getCrystalStability();
        boolean isAdvanced = MinecraftClient.getInstance().options.advancedItemTooltips;

        this.coreText = coreCharges[0]+"/"+coreCharges[1] + (isAdvanced ? " Charges" : "");
        this.handleText = handleDurability[0]+"/"+handleDurability[1] + (isAdvanced ? " Durability" : "");
        this.fixtureText = fixtureTransmittance[0]+"/"+fixtureTransmittance[1] + (isAdvanced ? " Transmittance" : "");
        this.crystalText = crystalStability[0]+"/"+crystalStability[1] + (isAdvanced ? " Stability" : "");

    }

    @Override
    public int getHeight() {
        return isPreview ? 20 : 52;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        if(this.isPreview) return 111;
        int core = textRenderer.getWidth(coreText)+14;
        int handle = textRenderer.getWidth(handleText)+14;
        int fixture = textRenderer.getWidth(fixtureText)+14;
        int crystal = textRenderer.getWidth(crystalText)+14;
        return Math.max(core, Math.max(handle, Math.max(fixture, crystal)));
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        if(this.isPreview) return;
        context.drawTexture(coreTextureLocation, x, y, 0, 0, 11, 11, 11, 11);
        context.drawTexture(handleTextureLocation, x, y+13, 0, 0, 11, 11, 11, 11);
        context.drawTexture(fixtureTextureLocation, x-3, y+24, 0, 0, 16, 16, 16, 16);
        context.drawTexture(crystalTextureLocation, x, y+39, 0, 0, 11, 11, 11, 11);
    }

    @Override
    public void drawText(TextRenderer textRenderer, int x, int y, Matrix4f matrix, VertexConsumerProvider.Immediate vertexConsumers) {
        if(this.isPreview) {
            textRenderer.draw(Text.literal("constructor a Leaper").formatted(Formatting.WHITE), x, y, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
            textRenderer.draw(Text.literal("in a ").append(Text.literal("table name").formatted(Formatting.OBFUSCATED)).formatted(Formatting.WHITE), x, y+10, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0 ,255);
            return;
        }
        textRenderer.draw(Text.literal(coreText).formatted(Formatting.WHITE),x+14, y+2, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        textRenderer.draw(Text.literal(handleText).formatted(Formatting.WHITE),x+14, y+15, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        textRenderer.draw(Text.literal(fixtureText).formatted(Formatting.WHITE),x+14, y+28, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
        textRenderer.draw(Text.literal(crystalText).formatted(Formatting.WHITE),x+14, y+41, 0, false, matrix, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, 255);
    }
}
