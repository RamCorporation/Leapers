package net.ramgames.leapers.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.item.ModItems;
import net.ramgames.leapers.networking.ModMessages;

public class CrystalCutterBlockEntity extends BlockEntity implements ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1,ItemStack.EMPTY);
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 200;

    public CrystalCutterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_CUTTER,pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CrystalCutterBlockEntity.this.progress;
                    case 1 -> CrystalCutterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0: CrystalCutterBlockEntity.this.progress = value; break;
                    case 1: CrystalCutterBlockEntity.this.maxProgress = value; break;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }



    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }


    public static boolean hasRecipeItem(CrystalCutterBlockEntity entity) {
        return entity.getStack(0).getItem() == ModItems.AERIS_CRYSTAL;
    }
    public static void tick(World world, BlockPos blockPos, BlockState state, CrystalCutterBlockEntity entity) {
        if(world.isClient()) {return;}
        if(hasRecipeItem(entity)) {
            entity.progress++;
            markDirty(world,blockPos,state);
            if(entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            markDirty(world,blockPos,state);
        }

    }
    public void insertItem(ItemStack stack) {
        this.inventory.set(0,stack);
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(CrystalCutterBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0;i < entity.size();i++) {
            inventory.setStack(i,entity.getStack(i));
        }
        if(hasRecipeItem(entity)) {
            entity.removeStack(0);
            ItemStack item = new ItemStack(ModItems.CUT_AERIS_CRYSTAL);
            NbtCompound nbt = new NbtCompound();
            item.setNbt(nbt);
            nbt.putIntArray("leapingPos",new int[]{entity.getPos().getX(),entity.getPos().getZ()});
            entity.setStack(0,item);
            entity.markDirty();
        }
        Leapers.LOGGER.info(entity.getItems().toString());

    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt,inventory);
        nbt.putInt("crystal_cutter.progress",progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt,inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("crystal_cutter.progress");
    }
    public void setProgress(int value) {
        this.progress = value;
    }

    public ItemStack getRenderStack(World world) {
        Inventory inventory = (Inventory) world.getBlockEntity(this.getPos());


        assert inventory != null;
        Leapers.LOGGER.info(String.valueOf(inventory.getStack(0)));
        return inventory.getStack(0);
    }

    public void setInventory(ItemStack stack) {
        this.inventory.set(0,stack);
    }

    @Override
    public void markDirty() {
        if(!world.isClient()) {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeInt(inventory.size());
            for(int i = 0; i < inventory.size();i++) {
                data.writeItemStack(inventory.get(i));
            }
            data.writeBlockPos(getPos());
            for(ServerPlayerEntity player: PlayerLookup.tracking((ServerWorld) world, getPos())) {player.sendMessage(Text.literal("ITEM SYNC"));
                ServerPlayNetworking.send(player, ModMessages.ITEM_SYNC,data);
            }
        }
        super.markDirty();
    }
}
