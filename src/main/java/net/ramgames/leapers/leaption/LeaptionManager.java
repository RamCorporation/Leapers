package net.ramgames.leapers.leaption;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.items.custom.LeaperItem;
import net.ramgames.leapers.leaption.api.modules.Core;
import net.ramgames.leapers.leaption.api.modules.Crystal;
import net.ramgames.leapers.leaption.api.modules.Fixture;
import net.ramgames.leapers.leaption.api.modules.Handle;

import java.util.HashMap;
import java.util.UUID;

public class LeaptionManager {

    public static final LeaptionManager INSTANCE = new LeaptionManager();

    private final HashMap<UUID, Integer> leapGhostEntries = new HashMap<>();

    private final HashMap<UUID, Leaption> leaptionEntries = new HashMap<>();



    public void tickOrCreate(World world, PlayerEntity player) {
        UUID playerUUID = player.getUuid();
        if(leaptionEntries.containsKey(playerUUID)) {
            leaptionEntries.get(playerUUID).tick(player.getBlockPos());
            if(world.getEntityById(leapGhostEntries.get(playerUUID)) instanceof LeapGhostEntity leapGhostEntity) {
                updateLeapGhostEntity(leapGhostEntity, player);
            }
            return;
        }
        LeapGhostEntity leapGhostEntity = new LeapGhostEntity(world, player);
        leapGhostEntries.put(playerUUID, leapGhostEntity.getId());
        ItemStack leaperStack = player.getMainHandStack();
        if(leaperStack.getItem() != ModItems.LEAPER) throw new IllegalStateException("Item used was not a leaper!");
        if(LeaperItem.containsImproperNbt(leaperStack.getNbt())) throw new IllegalStateException("Improper NBT found");
        NbtCompound nbt = leaperStack.getNbt();
        Core core = LeaperItem.getCoreEntry(nbt).orElseThrow();
        Handle handle = LeaperItem.getHandleEntry(nbt).orElseThrow();
        Fixture fixture = LeaperItem.getFixtureEntry(nbt).orElseThrow();
        Crystal crystal = LeaperItem.getCrystalEntry(nbt).orElseThrow();
        int[] crystalDeltas = LeaperItem.getCrystalDeltas(nbt).orElseThrow();
        Leaption leaption = new Leaption(playerUUID, leapGhostEntity.getUuid(), player.getBlockPos(), crystal.getType(), fixture.getType(), crystalDeltas[0],crystalDeltas[1], core.getDischargeTime());
        leaptionEntries.put(playerUUID, leaption);
        updateLeapGhostEntity(leapGhostEntity, player);
        world.spawnEntity(leapGhostEntity);
    }

    public Leaption getLeaption(UUID uuid) {
        return leaptionEntries.get(uuid);
    }

    public boolean isLeaping(UUID playerUUID) {
        //Leapers.LOGGER.info(String.valueOf(leaptionEntries.containsKey(playerUUID)));
        return leaptionEntries.containsKey(playerUUID);
    }

    public void cancelLeap(World world, PlayerEntity player) {
        Leapers.LOGGER.info("canceling!");
        UUID playerUUID = player.getUuid();
        if(isLeaping(playerUUID)) {
            if(world.getEntityById(leapGhostEntries.get(playerUUID)) != null) world.getEntityById(leapGhostEntries.get(playerUUID)).remove(Entity.RemovalReason.DISCARDED);
            leapGhostEntries.remove(playerUUID);
            leaptionEntries.remove(playerUUID);
        }
    }

    public void handleShutdown(ServerWorld world) {
        Leapers.LOGGER.info("Stopping LeaptionManager");
        leapGhostEntries.values().forEach(leapGhostId -> world.getEntityById(leapGhostId).remove((Entity.RemovalReason.DISCARDED)));
    }

    private void updateLeapGhostEntity(LeapGhostEntity leapGhostEntity, PlayerEntity player) {
        leapGhostEntity.setPosition(leaptionEntries.get(player.getUuid()).getBeamPos());
        leapGhostEntity.equipStack(EquipmentSlot.HEAD, player.getEquippedStack(EquipmentSlot.HEAD));
        leapGhostEntity.equipStack(EquipmentSlot.CHEST, player.getEquippedStack(EquipmentSlot.CHEST));
        leapGhostEntity.equipStack(EquipmentSlot.LEGS, player.getEquippedStack(EquipmentSlot.LEGS));
        leapGhostEntity.equipStack(EquipmentSlot.FEET, player.getEquippedStack(EquipmentSlot.FEET));
        leapGhostEntity.equipStack(EquipmentSlot.MAINHAND, player.getEquippedStack(EquipmentSlot.MAINHAND));
        leapGhostEntity.equipStack(EquipmentSlot.OFFHAND, player.getEquippedStack(EquipmentSlot.OFFHAND));
        leapGhostEntity.setYaw(player.getYaw());
        leapGhostEntity.setPitch(player.getPitch());
    }


}
