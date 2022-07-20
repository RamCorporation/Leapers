package net.ramgaming.leapers;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class tag implements ModInitializer {
    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(id));
    }
    public static final TagKey<Item> LEAPERS_TAG = tag.of("leapers:leaper");


    @Override
    public void onInitialize() {
        Leapers.LOGGER.debug("Registering Tags for "+ Leapers.MOD_ID);
    }
}
