package net.ramgaming.leapers.api.modules;

import java.math.BigDecimal;

public record FixtureModule(String name, TransportType type, int durability) {
    public float getID() {
        StringBuilder builder = new StringBuilder("0.");
        for(var i = 0; i < name.length(); i++) if (i % 2 != 0) builder.append((int) name.charAt(i));
        return new BigDecimal(builder.toString()).floatValue();
    }
}
