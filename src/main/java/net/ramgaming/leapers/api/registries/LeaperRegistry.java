package net.ramgaming.leapers.api.registries;

import net.minecraft.item.Item;

import java.util.HashMap;

public class LeaperRegistry<T>{

    private final HashMap<Item, T> values = new HashMap<>();

    protected void emptyRegistry() {
        values.keySet().forEach(values::remove);
    }

    protected void register(Item item, T value) {
        values.put(item, value);
    }

    public boolean contains(Item item) {
        return values.containsKey(item);
    }

    public T query(Item item) {
        return values.get(item);
    }
}
