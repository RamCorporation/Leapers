package net.ramgames.leapers.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.leaption.api.data.Entries;
import net.ramgames.leapers.leaption.api.data.LeaperRegistries;
import net.ramgames.leapers.screenhandlers.LeapStoneScreenHandler;

public class LeapStoneScreen extends HandledScreen<LeapStoneScreenHandler> {

    private static final Identifier TEXTURE = new Identifier("leapers:textures/gui/leap_stone_gui.png");

    public LeapStoneScreen(LeapStoneScreenHandler handler, PlayerInventory inventory, Text title) {
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
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        Inventory inventory = handler.getInventory();
        Item coreItem = inventory.getStack(2).getItem();
        Item fixtureItem = inventory.getStack(1).getItem();
        Item crystalItem = inventory.getStack(0).getItem();
        if(coreItem != Items.AIR) context.drawTexture(getDisplayTexture(LeaperRegistries.CORES, coreItem), this.x+100,this.y+11, 0, 0, 48, 48, 48, 48);
        if(fixtureItem != Items.AIR) context.drawTexture(getDisplayTexture(LeaperRegistries.FIXTURES, fixtureItem), this.x+100,this.y+11, 0, 0, 48, 48, 48, 48);
        if(crystalItem!= Items.AIR) context.drawTexture(getDisplayTexture(LeaperRegistries.CRYSTALS, crystalItem), this.x+100,this.y+11, 0, 0, 48, 48, 48, 48);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private <T extends Entries.LeaperEntry> Identifier getDisplayTexture(Registry<T> registry, Item item) {
        T registryEntry = registry.get(Registries.ITEM.getId(item));
        assert registryEntry != null;
        return registryEntry.getLeapStoneTexture();
    }
}
