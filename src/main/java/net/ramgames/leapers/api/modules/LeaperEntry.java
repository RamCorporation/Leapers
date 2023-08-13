package net.ramgames.leapers.api.modules;

public abstract class LeaperEntry {
    private final String leaperTexture;
    private final String tooltipTexture;

    public LeaperEntry(String leaperTexture, String tooltipTexture) {
        this.leaperTexture = leaperTexture;
        this.tooltipTexture = tooltipTexture;
    }

    public String getLeaperTexture() {
        return leaperTexture;
    }

    public String getTooltipTexture() {
        return tooltipTexture;
    }

}
