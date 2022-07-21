package net.ramgaming.leapers.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.ramgaming.leapers.screen.slot.ModCrystalSlot;
import net.ramgaming.leapers.screen.slot.ModHandleSlot;
import net.ramgaming.leapers.screen.slot.ModOutputSlot;

public class SkyGazerScreenHandler extends ScreenHandler {

    public final Inventory inventory;

    public SkyGazerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId,playerInventory, new SimpleInventory(4));
    }
    public SkyGazerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.STAR_GAZER_SCREEN_HANDLER, syncId);
        checkSize(inventory,4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);

        //SLOTS
        this.addSlot(new ModHandleSlot(inventory,0,26,37));
        this.addSlot(new ModCrystalSlot(inventory,1,62,37));
        this.addSlot(new ModOutputSlot(inventory,2,134,37));


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);


    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
