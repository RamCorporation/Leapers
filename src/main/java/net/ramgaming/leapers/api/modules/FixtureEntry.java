package net.ramgaming.leapers.api.modules;

public class FixtureEntry extends LeaperEntry {
    private final TransportType type;
    private final int maxDurability;
    public FixtureEntry(String name, String texturePath, TransportType type, int maxDurability) {
        super(name, texturePath);
        this.type = type;
        this.maxDurability = maxDurability;
    }

    public TransportType getType() {
        return type;
    }

    public int getMaxDurability() {
        return maxDurability;
    }
}
