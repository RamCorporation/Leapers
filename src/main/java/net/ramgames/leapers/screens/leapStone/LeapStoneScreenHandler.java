package net.ramgames.leapers.screens.leapStone;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import static net.ramgames.leapers.screens.ModScreenManager.LEAP_STONE_SCREEN_HANDLER;

public class LeapStoneScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    public LeapStoneScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3));
    }

    public LeapStoneScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(LEAP_STONE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 3);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        int m;
        int l;
        int index = 0;
        for (m = 0; m < 9; m++) {
            this.addSlot(new Slot(playerInventory, index++, 8 + m * 18, 143));
        }
        for (m = 0; m < 3; m++) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, index++, 8 + l * 18, 85 + m * 18));
            }
        }
        this.addSlot(new LeapStoneSlots.CrystalSlot(inventory, 0, 44, 8));
        this.addSlot(new LeapStoneSlots.FixtureSlot(inventory, 1, 44, 26));
        this.addSlot(new LeapStoneSlots.CoreSlot(inventory, 2, 44, 44));
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

}
