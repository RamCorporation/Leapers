package net.ramgames.leapers.leaption.api.modules;

import net.minecraft.util.Identifier;
import net.ramgames.leapers.leaption.api.data.Entries;

public class Core extends Entries.LeaperEntry {
    private final int maxCharges;
    private final int chargeTime;
    private final int dischargeTime;
    public Core(Identifier leaperTexture, Identifier tooltipTexture, Identifier leapStoneTexture, int maxCharges, int chargeTime, int dischargeTime) {
        super(leaperTexture, tooltipTexture, leapStoneTexture);
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
