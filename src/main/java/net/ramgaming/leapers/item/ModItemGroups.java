package net.ramgaming.leapers.item;

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;

import java.util.HashMap;

public class ModItemGroups {
    public static final HashMap<Item,ItemGroup> ItemsToGroup = new HashMap<>();

    public static final ItemGroup LEAPER_GROUP = registerItemGroup("leaper_group",ModItems.SPECTRAL_CRYSTAL);

    public static void onInitialize() {
        for(Item item: ItemsToGroup.keySet()) {
            ItemGroupEvents.modifyEntriesEvent(ItemsToGroup.get(item)).register(entries -> entries.add(item));
        }
    }
    private static ItemGroup registerItemGroup(String path,Item icon) {
        return FabricItemGroup.builder(new Identifier(Leapers.MOD_ID,path)).icon(Suppliers.ofInstance(new ItemStack(icon))).build();
    }
    public static void appendToTab(Item item, ItemGroup group) {
        ItemsToGroup.put(item,group);
    }
}
