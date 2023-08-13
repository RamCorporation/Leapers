package net.ramgames.leapers.api.modules;

public class Crystal extends LeaperEntry {
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
