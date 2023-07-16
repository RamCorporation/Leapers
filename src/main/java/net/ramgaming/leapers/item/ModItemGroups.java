package net.ramgaming.leapers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;

import java.util.LinkedHashMap;

public interface ModItemGroups {
    LinkedHashMap<Item,ItemGroup> ItemsToGroup = new LinkedHashMap<>();
    ItemGroup LEAPER_GROUP = registerItemGroup("leaper_group",ModItems.SPECTRAL_CRYSTAL);
    public static void onInitialize() {
        ItemsToGroup.forEach((item, group) -> {
            ItemGroupEvents.modifyEntriesEvent(Registries.ITEM_GROUP.getKey(group).get()).register(entries -> entries.add(item));
            Leapers.LOGGER.info(String.format("appending item '%s' to tab '%s'",item.getName().getString(),ItemsToGroup.get(item).getDisplayName().getString()));
        });
    }
    private static ItemGroup registerItemGroup(String path,Item icon) {
        ItemGroup group = FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SPECTRAL_CRYSTAL)).displayName(Text.translatable(path)).build();
        Registry.register(Registries.ITEM_GROUP, new Identifier(Leapers.MOD_ID,path), group);
        return group;
    }
    static void appendToTab(Item item, ItemGroup group) {
        ItemsToGroup.put(item,group);
    }
}
