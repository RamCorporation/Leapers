package net.ramgames.leapers.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.ramgames.leapers.networking.ModMessages;
import net.ramgames.leapers.screen.ImplementedInventory;
import net.ramgames.leapers.screen.crystalInspector.CrystalInspectorScreenHandler;
import org.jetbrains.annotations.Nullable;

public class CrystalInspectorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1,ItemStack.EMPTY);
    public CrystalInspectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_INSPECTOR,pos, state);

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);
    }

    public ItemStack getRenderStack() {
        return this.inventory.get(0);
    }

    public void setInventory(ItemStack stack) {
        this.inventory.set(0,stack);
    }

    @Override
    public Text getDisplayName() {
        return Text.of("");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrystalInspectorScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public void markDirty() {
        if(!getWorld().isClient()) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeBlockPos(getPos());
            data.writeItemStack(this.inventory.get(0));
            for(ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) getWorld(), getPos())) {
                ServerPlayNetworking.send(player, ModMessages.ITEM_SYNC, data);
            }
        }
        super.markDirty();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return this.inventory.get(0).isEmpty();
    }
}
