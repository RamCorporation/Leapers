package net.ramgaming.leapers.item.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class RecipeInventory implements Inventory {
    final HashMap<Integer,ItemStack> items;
    final int size;
    public RecipeInventory(int size,ItemStack defaultStack) {
        this.size = size;
        this.items = new HashMap<>();
        for(var i = 0; i < size; i++) {
            items.put(i,defaultStack);
        }
    }
    public RecipeInventory(int size) {
        this.size = size;
        this.items = new HashMap<>();
        for(var i = 0; i < size; i++) {
            items.put(i,ItemStack.EMPTY);
        }
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if(amount == 0) {
            return removeStack(slot);
        }
        ItemStack stack = this.items.get(slot);
        stack.increment(-amount);
        return this.items.put(slot, stack);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return this.items.remove(slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.items.put(slot,stack);
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {
        this.items.clear();
    }
    public HashMap<Item,Integer> sortedHash() {
        HashMap<Item,Integer> hash = new HashMap<>();
        for(Integer slot: this.items.keySet()) {
            ItemStack stack = this.items.get(slot);
            if(hash.containsKey(stack.getItem())) {
                ItemStack old = new ItemStack(stack.getItem(),hash.get(stack.getItem()));
                old.increment(stack.getCount());
                hash.put(stack.getItem(),old.getCount());
            } else {
                hash.put(stack.getItem(),stack.getCount());
            }
        }
        return hash;
    }
}
