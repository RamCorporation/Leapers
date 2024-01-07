package net.ramgames.leapers.screenhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public class FusionTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public FusionTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(10));
    }
    public FusionTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.FUSION_TABLE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 10);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        int m;
        int l;
        int index = 0;
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, index++, 8 + m * 18, 142));
        }
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, index++, 8 + l * 18, 84 + m * 18));
            }
        }
        index = 0;
        for(Integer i : new int[]{0,1,2,3,5,6,7,8}) {
            this.addSlot(new Slot(inventory, index++, 30+ (18*(i % 3)), 13+(18*(i/3))));
        }
        this.addSlot(new FusionTableSlots.UnofficialResultSlot(inventory, index++, 116, 10));
        this.addSlot(new FusionTableSlots.ResultSlot(inventory, index, 116, 51));
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
        return ModScreenHandlers.FUSION_TABLE_SCREEN_HANDLER;
    }

}
