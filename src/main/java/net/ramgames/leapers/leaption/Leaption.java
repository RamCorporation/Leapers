package net.ramgames.leapers.leaption;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.ramgames.leapers.leaption.api.modules.CrystalType;
import net.ramgames.leapers.leaption.api.modules.FixtureType;

import java.util.UUID;

public class Leaption {
    private final UUID playerUUID;
    private final UUID leapGhostUUID;
    private final CrystalType crystalType;
    private final FixtureType fixtureType;
    private final int deltaX;
    private final int deltaZ;
    private final int dischargeTime;
    private int deltaTicks = 0;
    private BlockPos prevPos;
    private Vec3d beamPos;


    public Leaption(UUID playerUUID, UUID leapGhostUUID, BlockPos playerBlockPos, CrystalType crystalType, FixtureType fixtureType, int deltaX, int deltaZ, int dischargeTime) {
        this.playerUUID = playerUUID;
        this.leapGhostUUID = leapGhostUUID;
        this.crystalType = crystalType;
        this.fixtureType = fixtureType;
        this.deltaX = deltaX;
        this.deltaZ = deltaZ;
        this.dischargeTime = dischargeTime;
        this.prevPos = playerBlockPos;

        //TEMP
        this.beamPos = new Vec3d(prevPos.getX()+deltaX, prevPos.getY(), prevPos.getZ()+deltaZ);
    }

    public void tick(BlockPos currentPlayerPosition) {
        if(deltaTicks < dischargeTime) deltaTicks++;
        if(prevPos != currentPlayerPosition) prevPos = currentPlayerPosition;
    }
    public boolean readyToLeap() {
        return deltaTicks == dischargeTime;
    }
    public float getDischargeProgress() {
        return deltaTicks/(float) this.dischargeTime;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public UUID getLeapGhostUUID() {
        return leapGhostUUID;
    }

    public CrystalType getCrystalType() {
        return crystalType;
    }

    public FixtureType getFixtureType() {
        return fixtureType;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaZ() {
        return deltaZ;
    }

    public int getDischargeTime() {
        return dischargeTime;
    }

    public int getDeltaTicks() {
        return deltaTicks;
    }

    public BlockPos getPrevPos() {
        return prevPos;
    }

    public Vec3d getBeamPos() {
        return beamPos;
    }
}
