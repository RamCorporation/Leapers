package net.ramgames.leapers.leaption.api.data;


import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Entries {
    static public abstract class LeaperEntry {
        private final Identifier leaperTexture;
        private final Identifier tooltipTexture;

        private final Identifier leapStoneTexture;

        public LeaperEntry(Identifier leaperTexture, Identifier tooltipTexture, Identifier leapStoneTexture) {
            this.leaperTexture = leaperTexture;
            this.tooltipTexture = tooltipTexture;
            this.leapStoneTexture = leapStoneTexture;
        }

        public Identifier getLeaperTexture() {
            return leaperTexture;
        }

        public Identifier getTooltipTexture() {
            return tooltipTexture;
        }

        public Identifier getLeapStoneTexture() {
            return leapStoneTexture;
        }
    }
    static public abstract class CrystalInspectorEntry {
        private final List<List<Pair<String, Formatting>>> rawTooltip;
        public CrystalInspectorEntry(List<List<Pair<String, Formatting>>> rawTooltip) {
            this.rawTooltip = rawTooltip;
        }

        public List<Text> getTooltip(NbtCompound nbtCompound) {
            List<Text> textList = new ArrayList<>();
            MutableText text;
            for(List<Pair<String, Formatting>> line : this.rawTooltip) {
                text = Text.empty();
                for(Pair<String, Formatting> component : line) text.append(Text.literal(implementNbt(component.getLeft(), nbtCompound)).formatted(component.getRight()));
                textList.add(text);
            }
            return textList;
        }

        private static String implementNbt(String input, NbtCompound nbtCompound) {
            Pattern pattern = Pattern.compile("\\$(.*?)\\$");
            Matcher matcher = pattern.matcher(input);
            StringBuilder output = new StringBuilder();
            while (matcher.find()) {
                String match = matcher.group(1);
                String replacement;
                if(nbtCompound == null) replacement = "null";
                else if(nbtCompound.contains(match)) replacement = nbtCompound.get(match).asString();
                else replacement = "null";
                matcher.appendReplacement(output, replacement);
            }
            matcher.appendTail(output);
            return output.toString();
        }
    }
}
