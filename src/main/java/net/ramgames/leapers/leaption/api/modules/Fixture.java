package net.ramgames.leapers.leaption.api.modules;

import net.minecraft.util.Identifier;
import net.ramgames.leapers.leaption.api.data.Entries;

public class Fixture extends Entries.LeaperEntry {
    private final FixtureType type;
    private final int maxTransmittance;
    public Fixture(Identifier leaperTexture, Identifier tooltipTexture, Identifier leapStoneTexture, FixtureType type, int maxTransmittance) {
        super(leaperTexture, tooltipTexture, leapStoneTexture);
        this.type = type;
        this.maxTransmittance = maxTransmittance;
    }

    public FixtureType getType() {
        return type;
    }

    public int getMaxTransmittance() {
        return maxTransmittance;
    }
}
