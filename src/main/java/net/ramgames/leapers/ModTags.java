package net.ramgames.leapers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public interface ModTags {

    TagKey<Block> CRYSTAL_BUDS = registerBlockTag("leapers:crystal_buds");
    TagKey<Block> AERIS_BUDS = registerBlockTag("leapers:aeris_buds");
    TagKey<Block> FERVIS_BUDS = registerBlockTag("leapers:fervis_buds");
    TagKey<Block> ALLURE_BUDS = registerBlockTag("leapers:allure_buds");
    TagKey<Block> GALVA_BUDS = registerBlockTag("leapers:galva_buds");
    TagKey<Block> HORA_BUDS = registerBlockTag("leapers:hora_buds");
    TagKey<Block> MEMORIA_BUDS = registerBlockTag("leapers:memoria_buds");
    TagKey<Block> UMBER_BUDS = registerBlockTag("leapers:umber_buds");
    TagKey<Item> LEAPER_CRYSTALS = registerItemTag("leapers:leaper_crystals");
    TagKey<Item> CUT_CRYSTALS = registerItemTag("leapers:cut_crystals");
    TagKey<Item> CRYSTALS = registerItemTag("leapers:leaper_crystals");
    private static TagKey<Item> registerItemTag(String id) {
        return TagKey.of(Registries.ITEM.getKey(),new Identifier(id));
    }
    private static TagKey<Block> registerBlockTag(String id) {
        return TagKey.of(Registries.BLOCK.getKey(),new Identifier(id));
    }
    static void onInitialize() {
        Leapers.LOGGER.debug("Registering Tags for "+ Leapers.MOD_ID);
    }

}
