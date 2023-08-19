package net.ramgames.leapers.api.modules;

import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;
import net.ramgames.leapers.api.data.Entries;

import java.util.ArrayList;
import java.util.List;

public class CrystalInspectorTooltip extends Entries.CrystalInspectorEntry{
    private CrystalInspectorTooltip(List<List<Pair<String, Formatting>>> rawTooltip) {
        super(rawTooltip);
    }
    public static Builder build() {
        return new Builder();
    }

    public static class Builder {

        List<List<Pair<String, Formatting>>> rawTooltip = new ArrayList<>();
        List<Pair<String, Formatting>> currentLine = new ArrayList<>();
        Formatting currentColor = Formatting.WHITE;

        private Builder() {
        }

        public Builder newLine() {
            rawTooltip.add(currentLine);
            currentLine = new ArrayList<>();
            return this;
        }

        public Builder add(String text) {
            currentLine.add(new Pair<>(text, currentColor));
            return this;
        }
        public Builder add(String text, Formatting color) {
            this.currentColor = color;
            return add(text);
        }

        public CrystalInspectorTooltip finish() {
            rawTooltip.add(currentLine);
            return new CrystalInspectorTooltip(rawTooltip);
        }
    }
}
