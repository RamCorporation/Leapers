package net.ramgames.leapers.blocks.custom;

import net.minecraft.util.StringIdentifiable;

public enum MirrorStances implements StringIdentifiable {
    UP("up"),
    DOWN("down"),
    FRONT("front");

    public final String name;
    MirrorStances(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
