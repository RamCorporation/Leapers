package net.ramgaming.leapers;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public interface RegisterTags {
    TagKey<Item> LEAPER_TAG = of("leapers:leaper");
    TagKey<Item> LEAPING_CRYSTAL = of("leapers:leaping_crystal");
    TagKey<Item> LEAPER_HANDLE = of("leapers:leaper_handle");
    TagKey<Item> UNCUT_CRYSTALS = of("leapers:uncut_crystals");
    TagKey<Item> AERIS_TYPE = of("leapers:aeris_type");
    TagKey<Item> MEMORIA_TYPE = of("leapers:memoria_type");
    TagKey<Item> UMBER_TYPE = of("leapers:umber_type");
    TagKey<Item> LEAPERS_CRYSTALS = of("leapers:leapers_crystals");
    private static TagKey<Item> of(String id) {
        return TagKey.of(Registries.ITEM.getKey(),new Identifier(id));
    }
    static void onInitialize() {
        Leapers.LOGGER.debug("Registering Tags for "+ Leapers.MOD_ID);
    }

}
