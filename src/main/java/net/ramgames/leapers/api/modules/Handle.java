package net.ramgames.leapers.api.modules;

public class Handle extends LeaperEntry {
    private final int maxRange;
    private final int maxDurability;
    public Handle(String texturePath, int maxRange, int maxDurability) {
        super(texturePath);
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
