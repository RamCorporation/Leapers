package net.ramgames.leapers.api.modules;

public class Fixture extends LeaperEntry {
    private final TransportType type;
    private final int maxDurability;
    public Fixture(String texturePath, TransportType type, int maxDurability) {
        super(texturePath);
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
