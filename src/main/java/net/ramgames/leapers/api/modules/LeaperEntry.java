package net.ramgames.leapers.api.modules;

public abstract class LeaperEntry {
    private final String texturePath;

    public LeaperEntry(String texturePath) {
        this.texturePath = texturePath;
    }

    public String getTexturePath() {
        return texturePath;
    }
}
