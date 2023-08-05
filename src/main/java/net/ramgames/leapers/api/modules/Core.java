package net.ramgames.leapers.api.modules;

public class Core extends LeaperEntry {
    private final int maxCharges;
    private final int chargeTime;
    private final int dischargeTime;
    public Core(String texturePath, int maxCharges, int chargeTime, int dischargeTime) {
        super(texturePath);
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
