package net.ramgames.leapers.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class RedstoneRayBlockEntity extends BlockEntity {
    public RedstoneRayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_CUTTER, pos, state);
        this.outputSignal = 0;
    }
    private int outputSignal;

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("redstone_ray.outputSignal",outputSignal);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        outputSignal = nbt.getInt("redstone_ray.outputSignal");
    }
}
