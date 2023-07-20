package net.ramgaming.leapers.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

import java.util.HashMap;
import java.util.Set;

public interface ItemRegistryInterpreter {
    HashMap<String,Item> itemFromStringCACHE = new HashMap<>();
    static Set<RegistryKey<Item>> itemSet() {
        return Registries.ITEM.getKeys();
    }
    static Item itemFromString(String name) {
        if(itemFromStringCACHE.containsKey(name)) return itemFromStringCACHE.get(name);
        for(RegistryKey<Item> key: itemSet()) {
            if (key.getValue().toString().equals(name)) {
                itemFromStringCACHE.put(name,Registries.ITEM.get(key.getValue()));
                return Registries.ITEM.get(key.getValue());

            }
        }
        return null;
    }
}
