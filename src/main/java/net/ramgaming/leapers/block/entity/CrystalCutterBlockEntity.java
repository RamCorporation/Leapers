package net.ramgaming.leapers.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ramgaming.leapers.RegisterTags;
import net.ramgaming.leapers.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class CrystalCutterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
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
    public Text getDisplayName() {
        return Text.literal("Crystal Cutter");
    }



    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }



    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        player.sendMessage(Text.literal("CLICKED!"));
        if(inventory.get(0) == ItemStack.EMPTY && player.getMainHandStack().isIn(RegisterTags.UNCUT_CRYSTALS)) {
            ItemStack stack = new ItemStack(player.getMainHandStack().getItem());
            stack.setCount(player.getMainHandStack().getCount()-1);
            player.setStackInHand(Hand.MAIN_HAND, stack);
            setStack(0,new ItemStack(player.getMainHandStack().getItem()));
        }
        if(inventory.get(0) != ItemStack.EMPTY && player.getStackInHand(Hand.MAIN_HAND) == ItemStack.EMPTY) {
            player.setStackInHand(Hand.MAIN_HAND,inventory.get(0));
            setStack(0, ItemStack.EMPTY);
        }
        markDirty();
        return null;
    }

    public static boolean hasRecipeItem(CrystalCutterBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for(int i = 0;i < entity.size();i++) {
            inventory.setStack(i,entity.getStack(i));
        }
        boolean hasCrystal = entity.getStack(0).getItem() == ModItems.UNCUT_AERIS_CRYSTAL;
        return hasCrystal;
    }
    public static void tick(World world, BlockPos blockPos, BlockState state, CrystalCutterBlockEntity entity) {
        if(world.isClient()) {return;}
        if(hasRecipeItem(entity)) {
            entity.progress ++;
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
            ItemStack item = new ItemStack(ModItems.AERIS_CRYSTAL);
            NbtCompound nbt = new NbtCompound();
            item.setNbt(nbt);
            nbt.putIntArray("leapingPos",new int[]{entity.getPos().getX(),entity.getPos().getZ()});
            entity.setStack(0,item);
        }
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

}
