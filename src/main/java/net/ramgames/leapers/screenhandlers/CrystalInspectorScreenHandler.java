package net.ramgames.leapers.screenhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public class CrystalInspectorScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public CrystalInspectorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(1));
    }
    public CrystalInspectorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.CRYSTAL_INSPECTOR_SCREEN_HANDLER, syncId);
        checkSize(inventory, 1);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        int m;
        int l;
        int index = 0;
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, index++, 8 + m * 18, 125));
        }
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, index++, 8 + l * 18, 67 + m * 18));
            }
        }
        this.addSlot(new LeapStoneSlots.CrystalSlot(inventory, 0, 44, 25));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        return ItemStack.EMPTY;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return ModScreenHandlers.CRYSTAL_INSPECTOR_SCREEN_HANDLER;
    }
}
