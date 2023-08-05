package net.ramgames.leapers;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public interface RegisterTags {
    TagKey<Item> LEAPER_HANDLES = of("leapers:leaper_handles");
    TagKey<Item> LEAPER_CORES = of("leapers:leaper_cores");
    TagKey<Item> LEAPER_FIXTURES = of("leapers:leaper_fixtures");
    TagKey<Item> LEAPER_CRYSTALS = of("leapers:leaper_crystals");
    TagKey<Item> CUT_CRYSTALS = of("leapers:cut_crystals");
    TagKey<Item> CRYSTALS = of("leapers:leaper_crystals");
    private static TagKey<Item> of(String id) {
        return TagKey.of(Registries.ITEM.getKey(),new Identifier(id));
    }
    static void onInitialize() {
        Leapers.LOGGER.debug("Registering Tags for "+ Leapers.MOD_ID);
    }

}
