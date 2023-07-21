package net.ramgaming.leapers.api.modules;

public class HandleEntry extends LeaperEntry {
    private final int maxRange;
    private final int maxDurability;
    public HandleEntry(String name, String texturePath, int maxRange, int maxDurability) {
        super(name, texturePath);
        this.maxRange = maxRange;
        this.maxDurability = maxDurability;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getMaxDurability() {
        return maxDurability;
    }
}
