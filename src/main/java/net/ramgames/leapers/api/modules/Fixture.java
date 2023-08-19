package net.ramgames.leapers.api.modules;

import net.ramgames.leapers.api.data.Entries;

public class Fixture extends Entries.LeaperEntry {
    private final TransportType type;
    private final int maxTransmittance;
    public Fixture(String leaperTexture, String tooltipTexture, TransportType type, int maxTransmittance) {
        super(leaperTexture, tooltipTexture);
        this.type = type;
        this.maxTransmittance = maxTransmittance;
    }

    public TransportType getType() {
        return type;
    }

    public int getMaxTransmittance() {
        return maxTransmittance;
    }
}
