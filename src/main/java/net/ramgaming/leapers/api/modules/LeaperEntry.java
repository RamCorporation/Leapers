package net.ramgaming.leapers.api.modules;

import java.math.BigDecimal;

public abstract class LeaperEntry {
    private final String name;
    private final String texturePath;

    public LeaperEntry(String name, String texturePath) {
        this.name = name;
        this.texturePath = texturePath;
    }

    public String getName() {
        return name;
    }

    public String getTexturePath() {
        return texturePath;
    }
    public float getModelID() {
        StringBuilder builder = new StringBuilder("0.");
        for(var i = 0; i < name.length(); i++) if (i % 2 != 0) builder.append((int) name.charAt(i));
        return new BigDecimal(builder.toString()).floatValue();
    }
}
