package net.ramgaming.leapers.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.ramgaming.leapers.RegisterTags;

public class ModHandleSlot extends Slot {
    public ModHandleSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    public boolean canInsert(ItemStack stack) {
        if (stack.isIn(RegisterTags.LEAPER_HANDLE)) {return true;}
        return false;
    }
}
