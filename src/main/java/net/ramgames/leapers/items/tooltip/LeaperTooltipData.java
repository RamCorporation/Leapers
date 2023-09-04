package net.ramgames.leapers.items.tooltip;

import net.minecraft.client.item.TooltipData;
import net.minecraft.util.Identifier;

public class LeaperTooltipData implements TooltipData {

    private Identifier coreTextureLocation;
    private Identifier handleTextureLocation;
    private Identifier fixtureTextureLocation;
    private Identifier crystalTextureLocation;
    private int[] coreCharges;
    private int[] handleDurability;
    private int[] fixtureTransmittance;
    private int[] crystalStability;

    private final boolean preview;
    public LeaperTooltipData(Identifier coreTextureLocation, Identifier handleTextureLocation, Identifier fixtureTextureLocation, Identifier crystalTextureLocation, int[] coreCharges, int[] handleDurability, int[] fixtureTransmittance, int[] crystalStability) {
        this.preview = false;
        this.coreTextureLocation = coreTextureLocation;
        this.handleTextureLocation = handleTextureLocation;
        this.fixtureTextureLocation = fixtureTextureLocation;
        this.crystalTextureLocation = crystalTextureLocation;
        this.coreCharges = coreCharges;
        this.handleDurability = handleDurability;
        this.fixtureTransmittance = fixtureTransmittance;
        this.crystalStability = crystalStability;
    }
    public LeaperTooltipData() {
        this.preview = true;
    }


    public Identifier getCoreTextureLocation() {
        return coreTextureLocation;
    }

    public Identifier getHandleTextureLocation() {
        return handleTextureLocation;
    }

    public Identifier getFixtureTextureLocation() {
        return fixtureTextureLocation;
    }

    public Identifier getCrystalTextureLocation() {
        return crystalTextureLocation;
    }

    public int[] getCoreCharges() {
        return coreCharges;
    }

    public int[] getHandleDurability() {
        return handleDurability;
    }

    public int[] getFixtureTransmittance() {
        return fixtureTransmittance;
    }

    public int[] getCrystalStability() {
        return crystalStability;
    }

    public boolean isPreview() {
        return preview;
    }
}
