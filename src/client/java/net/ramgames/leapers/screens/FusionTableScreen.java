package net.ramgames.leapers.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.screenhandlers.FusionTableScreenHandler;
import net.ramgames.leapers.screenhandlers.FusionTableSlots;

import java.util.List;

public class FusionTableScreen extends HandledScreen<FusionTableScreenHandler> {

    public static final Identifier TEXTURE = new Identifier("leapers:textures/gui/fusion_table_gui.png");
    public boolean showCraftInfo = false;
    public FusionTableScreen(FusionTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {

    }

    @Override
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
        if(slot instanceof FusionTableSlots.UnofficialResultSlot) showCraftInfo = true;
        super.onMouseClick(slot, slotId, button, actionType);
    }

    @Override
    protected void handledScreenTick() {
        if(this.focusedSlot == null) showCraftInfo = false;
        if(this.focusedSlot != null && !(focusedSlot instanceof FusionTableSlots.UnofficialResultSlot)) showCraftInfo = false;
        super.handledScreenTick();
    }

    @Override
    protected void drawMouseoverTooltip(DrawContext context, int x, int y) {
        if(showCraftInfo) context.drawTooltip(textRenderer, List.of(Text.of("strike the Fusion Table"), Text.of("with lightning to craft")), x, y); else super.drawMouseoverTooltip(context, x, y);
    }
}
