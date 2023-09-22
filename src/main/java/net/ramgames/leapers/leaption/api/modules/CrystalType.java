package net.ramgames.leapers.leaption.api.modules;

public enum CrystalType {
    DAY,
    NIGHT,
    REDSTONE,
    ;

    public static CrystalType fromString(String string) {
        return switch (string) {
            case "day" -> DAY;
            case "night" -> NIGHT;
            case "redstone" -> REDSTONE;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
