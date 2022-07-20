package net.ramgaming.leapers;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterTags {
    public static final TagKey<Item> LEAPER_TAG = of("leapers:leaper");
    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(id));
    }
    public static void onInitialize() {
        Leapers.LOGGER.debug("Registering Tags for "+ Leapers.MOD_ID);
    }
}
