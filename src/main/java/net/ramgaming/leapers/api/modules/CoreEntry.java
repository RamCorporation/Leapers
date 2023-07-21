package net.ramgaming.leapers.api.modules;

public class CoreEntry extends LeaperEntry {
    private final int maxCharges;
    private final int chargeTime;
    private final int dischargeTime;
    public CoreEntry(String name, String texturePath, int maxCharges, int chargeTime, int dischargeTime) {
        super(name, texturePath);
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
