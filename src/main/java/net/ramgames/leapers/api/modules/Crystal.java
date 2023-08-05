package net.ramgames.leapers.api.modules;

public class Crystal extends LeaperEntry {
    private final CrystalType type;
    private final int maxDurability;
    public Crystal(String texturePath, CrystalType type, int maxDurability) {
        super(texturePath);
        this.type = type;
        this.maxDurability = maxDurability;
    }

    public CrystalType getType() {
        return type;
    }

    public int getMaxDurability() {
        return maxDurability;
    }
}
