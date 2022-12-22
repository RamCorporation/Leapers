package net.ramgaming.leapers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModItemGroups {
    public static final HashMap<Item,ItemGroup> ItemsToGroup = new HashMap<>();
    public static final List<Item> OrderToGroup = new ArrayList<>();
    public static final ItemGroup LEAPER_GROUP = registerItemGroup("leaper_group",ModItems.SPECTRAL_CRYSTAL);
    public static void onInitialize() {
        for(Item item: OrderToGroup) {
            ItemGroupEvents.modifyEntriesEvent(ItemsToGroup.get(item)).register(entries -> entries.add(item));
            Leapers.LOGGER.info(String.format("appending item '%s' to tab '%s'",item.getName().getString(),ItemsToGroup.get(item).getDisplayName().getString()));
        }
    }
    private static ItemGroup registerItemGroup(String path,Item icon) {
        return FabricItemGroup.builder(new Identifier(Leapers.MOD_ID,path)).icon(() -> new ItemStack(ModItems.SPECTRAL_CRYSTAL)).build();
    }
    public static void appendToTab(Item item, ItemGroup group) {
        ItemsToGroup.put(item,group);
        OrderToGroup.add(item);
    }
}
