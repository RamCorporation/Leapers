package net.ramgaming.leapers.block.custom;

import net.minecraft.util.StringIdentifiable;

public enum MirrorDirections implements StringIdentifiable {
    UP("up"),
    DOWN("down"),
    STANDING("standing");

    public final String name;
    MirrorDirections(String name) {
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
