package net.ramgames.leapers.api.modules;

public enum TransportType {
    RELATIVE,
    FIXED,
    DYNAMIC
    ;

    public static TransportType fromString(String string) {
        return switch (string) {
            case "relative" -> RELATIVE;
            case "fixed" -> FIXED;
            case "dynamic" -> DYNAMIC;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }
}
