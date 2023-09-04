package net.ramgames.leapers.screens.crystalInspector;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.Slot;
import net.ramgames.leapers.api.data.LeaperRegistries;

public class CrystalSlot extends Slot {
    public CrystalSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return LeaperRegistries.CRYSTAL_INSPECTOR_TOOLTIP.containsId(Registries.ITEM.getId(stack.getItem()));
    }

    @Override
    public int getMaxItemCount() {
        return 1;
    }

    @Override
    public int getMaxItemCount(ItemStack stack) {
        return 1;
    }

    
}
