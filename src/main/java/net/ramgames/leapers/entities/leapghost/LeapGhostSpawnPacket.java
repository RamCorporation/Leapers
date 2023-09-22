package net.ramgames.leapers.entities.leapghost;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;

public class LeapGhostSpawnPacket extends EntitySpawnS2CPacket {

    private final PlayerEntity player;
    public LeapGhostSpawnPacket(Entity entity, PlayerEntity player) {
        super(entity);
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }
}
