package net.ramgaming.leapers.api.data;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Set;

class LeaperRegistry<T>{

    private final HashMap<Item, T> values = new HashMap<>();

    protected void emptyRegistry() {
        values.keySet().forEach(values::remove);
    }

    protected void register(Item item, T value) {
        if (contains(item)) LeaperResourceManager.LOGGER.error("duplicate registry attempt for item '"+item+"'"); else values.put(item, value);
    }

    public boolean contains(Item item) {
        return values.containsKey(item);
    }

    public Set<T> valueSet() {
        return (Set<T>) values.values();
    }

    public Set<Item> keySet() {
        return values.keySet();
    }


    public T query(Item item) {
        return values.get(item);
    }
}
