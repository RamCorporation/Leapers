package net.ramgames.leapers.api.modules;

import net.ramgames.leapers.api.data.Entries;

public class Handle extends Entries.LeaperEntry {
    private final int maxRange;
    private final int maxDurability;
    public Handle(String leaperTexture, String tooltipTexture, int maxRange, int maxDurability) {
        super(leaperTexture, tooltipTexture);
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
