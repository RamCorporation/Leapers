package net.ramgames.leapers.api.modules;

import net.minecraft.util.Identifier;
import net.ramgames.leapers.api.data.Entries;

public class Crystal extends Entries.LeaperEntry {
    private final CrystalType type;
    private final int maxStability;
    public Crystal(Identifier leaperTexture, Identifier tooltipTexture, Identifier leapStoneTexture, CrystalType type, int maxStability) {
        super(leaperTexture, tooltipTexture, leapStoneTexture);
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
