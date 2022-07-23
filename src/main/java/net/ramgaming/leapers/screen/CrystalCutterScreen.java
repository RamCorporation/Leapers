package net.ramgaming.leapers.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.screen.button.ModButton;

public class CrystalCutterScreen extends HandledScreen<CrystalCutterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Leapers.MOD_ID,"textures/gui/crystal_cutter_gui.png");



    public CrystalCutterScreen(CrystalCutterScreenHandler handler, PlayerInventory inventory, Text  title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - backgroundWidth) /2;
        int y = (height - backgroundHeight) /2;
        drawTexture(matrices,x,y,0,0,backgroundWidth,backgroundHeight);

    }

    @Override
    protected void init() {
        super.init();
        titleX = 10;
        titleY = 25;

    }
    ButtonWidget BUTTON = new ModButton((backgroundWidth)/2,115,75,18, Text.literal("Cut Crystal"),PRESSED());

    private static ButtonWidget.PressAction PRESSED() {
        return null;
    }
    @Override
    public void render(MatrixStack matrices, int mouseX,int mouseY, float delta) {
        renderBackground(matrices);

        super.render(matrices,mouseX,mouseY,delta);
        BUTTON.render(matrices,mouseX,mouseY,delta);
        drawMouseoverTooltip(matrices,mouseX,mouseY);

    }
}
