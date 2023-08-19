package net.ramgames.leapers.api.data;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LeaperRegistry<T extends Entries>{

    public static final Logger LOGGER = LoggerFactory.getLogger("Leaption API");

    private final HashMap<Identifier, T> values = new HashMap<>();

    protected void emptyRegistry() {
        new HashSet<>(values.keySet()).forEach(values::remove);
    }

    protected void register(Identifier identifier, T value) {
        if (contains(identifier)) LOGGER.error("duplicate registry attempt for item '"+identifier+"'"); else values.put(identifier, value);
    }

    public boolean contains(Identifier identifier) {
        return values.containsKey(identifier);
    }

    public Set<T> valueSet() {
        return new HashSet<>(values.values());
    }

    public Set<Identifier> keySet() {
        return values.keySet();
    }


    public T query(Identifier identifier) {
        return values.get(identifier);
    }

    public static <T extends Entries> void register(LeaperRegistry<T> registry, Identifier identifier, T entry) {
        registry.register(identifier, entry);
    }
}
