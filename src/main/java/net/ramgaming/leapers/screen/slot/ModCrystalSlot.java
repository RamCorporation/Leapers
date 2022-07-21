package net.ramgaming.leapers.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.ramgaming.leapers.RegisterTags;

public class ModCrystalSlot extends Slot {
    public ModCrystalSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    public boolean canInsert(ItemStack stack) {
        if (stack.isIn(RegisterTags.LEAPING_CRYSTAL)) {return true;}
        return false;
    }
}
