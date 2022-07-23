package net.ramgaming.leapers.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.ramgaming.leapers.RegisterTags;

public class ModUncutCrystalSlot extends Slot {
    public ModUncutCrystalSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }
    public boolean canInsert(ItemStack stack) {
        if (stack.isIn(RegisterTags.UNCUT_LEAPING_CRYSTAL) ){
            if (stack.getCount() == 1) {return true;}
            else {
                ItemStack TEMP_STACK = stack.copy();
                TEMP_STACK.setCount(1);
                stack.setCount(stack.getCount()-1);
                setStack(TEMP_STACK);
            }
            }

        return false;
    }


}
