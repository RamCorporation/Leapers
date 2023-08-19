package net.ramgames.leapers.api.modules;

import net.ramgames.leapers.api.data.Entries;

public class Core extends Entries.LeaperEntry {
    private final int maxCharges;
    private final int chargeTime;
    private final int dischargeTime;
    public Core(String leaperTexture, String tooltipTexture, int maxCharges, int chargeTime, int dischargeTime) {
        super(leaperTexture, tooltipTexture);
        this.maxCharges = maxCharges;
        this.chargeTime = chargeTime;
        this.dischargeTime = dischargeTime;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    public int getChargeTime() {
        return chargeTime;
    }

    public int getDischargeTime() {
        return dischargeTime;
    }
}
