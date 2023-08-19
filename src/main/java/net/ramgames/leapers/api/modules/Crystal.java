package net.ramgames.leapers.api.modules;

import net.ramgames.leapers.api.data.Entries;

public class Crystal extends Entries.LeaperEntry {
    private final CrystalType type;
    private final int maxStability;
    public Crystal(String leaperTexture, String tooltipTexture, CrystalType type, int maxStability) {
        super(leaperTexture, tooltipTexture);
        this.type = type;
        this.maxStability = maxStability;
    }

    public CrystalType getType() {
        return type;
    }

    public int getMaxStability() {
        return maxStability;
    }
}
