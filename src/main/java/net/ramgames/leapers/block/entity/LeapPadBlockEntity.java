package net.ramgames.leapers.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.ramgames.leapers.LeaperVariables;

import java.util.UUID;

public class LeapPadBlockEntity extends BlockEntity {

    private UUID crystalID;

    public LeapPadBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LEAP_PAD, pos, state);
        this.crystalID = null;
    }


    public void setCrystalID(UUID uuid) {
        crystalID = uuid;
    }
    public UUID getCrystalID() {
        return crystalID;
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        if(crystalID != null) {
            nbt.putUuid("crystalUUID",crystalID);
        }
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        crystalID = nbt.getUuid("crystalUUID");
        LeaperVariables.KnownLeapPads.add(this.pos);
        LeaperVariables.UuidLeapPads.put(this.pos,crystalID);

    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return super.toInitialChunkDataNbt();
    }
}
