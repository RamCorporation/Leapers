package net.ramgames.leapers.leaption.api.modules;

import net.minecraft.util.Identifier;
import net.ramgames.leapers.leaption.api.data.Entries;

public class Handle extends Entries.LeaperEntry {
    private final int maxRange;
    private final int maxDurability;
    public Handle(Identifier leaperTexture, Identifier tooltipTexture, int maxRange, int maxDurability) {
        super(leaperTexture, tooltipTexture, null);
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
