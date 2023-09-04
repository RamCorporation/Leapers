package net.ramgames.leapers.blocks.custom;

import net.minecraft.util.StringIdentifiable;

public enum MirrorDirections implements StringIdentifiable {
    STANDING("standing"),
    LEFT("left"),
    RIGHT("right"),
    UP("up"),

    BACK("back")

    ;

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
