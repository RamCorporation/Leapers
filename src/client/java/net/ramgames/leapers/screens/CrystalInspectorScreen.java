package net.ramgames.leapers.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.ramgames.leapers.leaption.api.data.LeaperRegistries;
import net.ramgames.leapers.screenhandlers.CrystalInspectorScreenHandler;
import net.ramgames.leapers.screenhandlers.CrystalSlot;

import java.util.HashMap;
import java.util.List;

public class CrystalInspectorScreen extends HandledScreen<CrystalInspectorScreenHandler> {

    private static final Identifier TEXTURE = new Identifier("leapers:textures/gui/crystal_inspector_gui.png");
    private static final HashMap<ItemStack, Pair<List<Text>, Integer>> cache = new HashMap<>();

    public CrystalInspectorScreen(CrystalInspectorScreenHandler handler, PlayerInventory inventory, Text title) {
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
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        Pair<List<Text>, Integer> tooltip = generateTooltip();
        context.drawTooltip(this.textRenderer, tooltip.getLeft(), this.x+215, this.y+105-tooltip.getRight());
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
    }

    @Override
    protected void drawMouseoverTooltip(DrawContext context, int x, int y) {
        if(this.focusedSlot == null) return;
        if(this.focusedSlot instanceof CrystalSlot) return;
        super.drawMouseoverTooltip(context, x, y);
    }

    public Pair<List<Text>, Integer> generateTooltip() {
        ItemStack stack = this.handler.getInventory().getStack(0);
        if(cache.containsKey(stack)) return cache.get(stack);
        List<Text> textToRender;
        Identifier identifier = Registries.ITEM.getId(stack.getItem());
        if(LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP.containsId(identifier)) textToRender = LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP.get(identifier).getTooltip(stack.getNbt());
        else textToRender = LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP.get(Registries.ITEM.getId(ItemStack.EMPTY.getItem())).getTooltip(stack.getNbt());
        Pair<List<Text>, Integer> entry = new Pair<>(textToRender, 10 * textToRender.size());
        cache.put(stack, entry);
        return entry;
    }
}
