package net.ramgames.leapers.leaption;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityPose;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.ModNetworking;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;
import net.ramgames.leapers.leaption.api.modules.CrystalType;
import net.ramgames.leapers.leaption.api.modules.FixtureType;

import java.util.UUID;

public class Leaption {
    private final UUID playerUUID;
    private final UUID leapGhostUUID;
    private final CrystalType crystalType;
    private final FixtureType fixtureType;
    private final int specifiedX;
    private final int specifiedZ;
    private final int dischargeTime;
    private int progressTicks = 0;
    private Vec3d cachedPlayerVec;
    private EntityPose cachedPlayerPose;
    private float[] cachedPlayerLooking = new float[]{Float.MIN_VALUE,Float.MIN_VALUE};
    private float cachePlayerBodyYaw;
    private float cachedPlayerYaw;
    private Vec3d cachedDestinationVec;

    public Leaption(ServerPlayerEntity serverPlayer, UUID leapGhostUUID, CrystalType crystalType, FixtureType fixtureType, int specifiedX, int specifiedZ, int dischargeTime) {
        this.playerUUID = serverPlayer.getUuid();
        this.leapGhostUUID = leapGhostUUID;
        this.crystalType = crystalType;
        this.fixtureType = fixtureType;
        this.specifiedX = specifiedX;
        this.specifiedZ = specifiedZ;
        this.dischargeTime = dischargeTime;
        tick(serverPlayer);
    }

    public void tick(ServerPlayerEntity serverPlayer) {
        ServerWorld world = (ServerWorld) serverPlayer.getWorld();
        LeapGhostEntity leapGhostEntity = (LeapGhostEntity) world.getEntity(leapGhostUUID);
        if (leapGhostEntity == null) return;
        leapGhostEntity.updateGhostAppearance(serverPlayer);
        Vec3d tempPlayerVec = serverPlayer.getPos();
        if (cachedPlayerVec != tempPlayerVec) {
            leapGhostEntity.setPosition(tempPlayerVec.x + specifiedX, tempPlayerVec.y, tempPlayerVec.z + specifiedZ);
            cachedPlayerVec = tempPlayerVec;
        }
        EntityPose tempPlayerPose = serverPlayer.getPose();

        if (cachedPlayerPose != tempPlayerPose) {
            leapGhostEntity.setPose(tempPlayerPose);
            Leapers.LOGGER.info("{}", leapGhostEntity.getPose());
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(leapGhostEntity.getId());
            buf.writeEnumConstant(tempPlayerPose);
            ServerPlayNetworking.send(serverPlayer, ModNetworking.CHANGE_GHOST_POSE, buf);
            cachedPlayerPose = tempPlayerPose;
        }

        float tempPitch = serverPlayer.getPitch();
        if (cachedPlayerLooking[0] != tempPitch) {
            leapGhostEntity.setPitch(tempPitch);
            leapGhostEntity.prevPitch = tempPitch;
            cachedPlayerLooking[0] = tempPitch;
        }
        float tempYaw = serverPlayer.getYaw();
        if (cachedPlayerYaw != tempYaw) {
            leapGhostEntity.setYaw(tempYaw);
            leapGhostEntity.prevYaw = tempYaw;
            cachedPlayerYaw = tempYaw;
        }
        float tempHeadYaw = serverPlayer.getHeadYaw();
        if (cachedPlayerLooking[1] != tempHeadYaw) {
            leapGhostEntity.setHeadYaw(tempHeadYaw);
            leapGhostEntity.prevHeadYaw = tempHeadYaw;
            cachedPlayerLooking[1] = tempHeadYaw;
        }
        float tempBodyYaw = serverPlayer.getBodyYaw();
        if(cachePlayerBodyYaw != tempBodyYaw) {
            leapGhostEntity.setBodyYaw(tempBodyYaw);
            leapGhostEntity.prevBodyYaw = tempBodyYaw;
            cachePlayerBodyYaw = tempBodyYaw;
        }

    }


    public boolean readyToLeap() {
        return progressTicks == dischargeTime;
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

    public int getSpecifiedX() {
        return specifiedX;
    }

    public int getSpecifiedZ() {
        return specifiedZ;
    }

    public int getDischargeTime() {
        return dischargeTime;
    }

    public int getProgressTicks() {
        return progressTicks;
    }

    public Vec3d getCachedPlayerVec() {
        return cachedPlayerVec;
    }

    public EntityPose getCachedPlayerPose() {
        return cachedPlayerPose;
    }

    public Vec3d getCachedDestinationVec() {
        return cachedDestinationVec;
    }
}