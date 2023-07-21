package net.ramgaming.leapers.api.modules;

public class CrystalEntry extends LeaperEntry {
    private final CrystalType type;
    private final int maxDurability;
    public CrystalEntry(String name, String texturePath, CrystalType type, int maxDurability) {
        super(name, texturePath);
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
