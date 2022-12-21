package net.ramgaming.leapers;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterTags {
    public static final TagKey<Item> LEAPER_TAG = of("leapers:leaper");
    public static final TagKey<Item> LEAPING_CRYSTAL = of("leapers:leaping_crystal");
    public static final TagKey<Item> LEAPER_HANDLE = of("leapers:leaper_handle");
    public static final TagKey<Item> UNCUT_CRYSTALS = of("leapers:uncut_crystals");
    public static final TagKey<Item> AERIS_TYPE = of("leapers:aeris_type");
    public static final TagKey<Item> MEMORIA_TYPE = of("leapers:memoria_type");
    public static final TagKey<Item> UMBER_TYPE = of("leapers:umber_type");
    public static final TagKey<Item> LEAPERS_CRYSTALS = of("leapers:leapers_crystals");
    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(id));
    }
    public static void onInitialize() {
        Leapers.LOGGER.debug("Registering Tags for "+ Leapers.MOD_ID);
    }

}
