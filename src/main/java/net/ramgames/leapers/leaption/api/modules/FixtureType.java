package net.ramgames.leapers.leaption.api.modules;

public enum FixtureType {
    RELATIVE,
    FIXED,
    DYNAMIC
    ;

    public static FixtureType fromString(String string) {
        return switch (string) {
            case "relative" -> RELATIVE;
            case "fixed" -> FIXED;
            case "dynamic" -> DYNAMIC;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
