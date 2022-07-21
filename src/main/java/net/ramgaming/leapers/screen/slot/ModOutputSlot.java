package net.ramgaming.leapers.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ModOutputSlot extends Slot {
    public ModOutputSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    protected void onTake(int amount) {

    }
}
