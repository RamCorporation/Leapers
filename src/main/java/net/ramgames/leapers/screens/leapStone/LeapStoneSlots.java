package net.ramgames.leapers.screens.leapStone;

import com.mojang.datafixers.util.Pair;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.api.data.LeaperRegistries;
import org.jetbrains.annotations.Nullable;

public interface LeapStoneSlots {

    Identifier BLOCK_ATLAS = new Identifier("textures/atlas/blocks.png");

    class CoreSlot extends Slot {
        public static final Identifier TEXTURE = new Identifier(Leapers.MOD_ID, "item/cores/core_icon");

        public CoreSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return LeaperRegistries.CORES.containsId(Registries.ITEM.getId(stack.getItem()));
        }

        @Override
        public int getMaxItemCount() {
            return 1;
        }

        @Override
        public int getMaxItemCount(ItemStack stack) {
            return 1;
        }

        @Nullable
        @Override
        public Pair<Identifier, Identifier> getBackgroundSprite() {
            return Pair.of(BLOCK_ATLAS, TEXTURE);
        }
    }

    class FixtureSlot extends Slot {

        public static final Identifier TEXTURE = new Identifier(Leapers.MOD_ID, "item/fixtures/fixture_icon");

        public FixtureSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return LeaperRegistries.FIXTURES.containsId(Registries.ITEM.getId(stack.getItem()));
        }

        @Override
        public int getMaxItemCount() {
            return 1;
        }

        @Override
        public int getMaxItemCount(ItemStack stack) {
            return 1;
        }

        @Nullable
        @Override
        public Pair<Identifier, Identifier> getBackgroundSprite() {
            return Pair.of(BLOCK_ATLAS, TEXTURE);
        }
    }

    class CrystalSlot extends Slot {

        public static final Identifier TEXTURE = new Identifier("item/empty_slot_amethyst_shard");

        public CrystalSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return LeaperRegistries.CRYSTALS.containsId(Registries.ITEM.getId(stack.getItem()));
        }

        @Override
        public int getMaxItemCount() {
            return 1;
        }

        @Override
        public int getMaxItemCount(ItemStack stack) {
            return 1;
        }

        @Nullable
        @Override
        public Pair<Identifier, Identifier> getBackgroundSprite() {
            return Pair.of(BLOCK_ATLAS, TEXTURE);
        }
    }

}
