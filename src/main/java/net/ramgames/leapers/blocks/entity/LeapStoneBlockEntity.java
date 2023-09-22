package net.ramgames.leapers.blocks.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.ramgames.leapers.blocks.ImplementedInventory;
import net.ramgames.leapers.screenhandlers.LeapStoneScreenHandler;
import org.jetbrains.annotations.Nullable;

public class LeapStoneBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3,ItemStack.EMPTY);

    public LeapStoneBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LEAP_STONE, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt,inventory);
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);

    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return super.toInitialChunkDataNbt();
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.leapers.leap_stone");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new LeapStoneScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
}
