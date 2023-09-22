package net.ramgames.leapers.items.tooltip;

import net.minecraft.client.item.TooltipData;

public class SpyglassTooltipData implements TooltipData {

    private final String lensName;

    public SpyglassTooltipData(String lensName) {
        this.lensName = lensName;
    }

    public String getLensName() {
        return lensName;
    }
}
