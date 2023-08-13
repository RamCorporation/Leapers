package net.ramgames.leapers.item.tooltip;

import net.minecraft.client.item.TooltipData;
import net.minecraft.util.Identifier;

public class LeaperTooltipData implements TooltipData {

    private final Identifier coreTextureLocation;
    private final Identifier handleTextureLocation;
    private final Identifier fixtureTextureLocation;
    private final Identifier crystalTextureLocation;
    private final int[] coreCharges;
    private final int[] handleDurability;
    private final int[] fixtureTransmittance;
    private final int[] crystalStability;

    public LeaperTooltipData(Identifier coreTextureLocation, Identifier handleTextureLocation, Identifier fixtureTextureLocation, Identifier crystalTextureLocation, int[] coreCharges, int[] handleDurability, int[] fixtureTransmittance, int[] crystalStability) {
        this.coreTextureLocation = coreTextureLocation;
        this.handleTextureLocation = handleTextureLocation;
        this.fixtureTextureLocation = fixtureTextureLocation;
        this.crystalTextureLocation = crystalTextureLocation;
        this.coreCharges = coreCharges;
        this.handleDurability = handleDurability;
        this.fixtureTransmittance = fixtureTransmittance;
        this.crystalStability = crystalStability;
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
}
