package net.ramgames.leapers.leaption;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.ModNetworking;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.items.custom.LeaperItem;
import net.ramgames.leapers.leaption.api.modules.*;

import java.util.*;


public class LeaptionManager {

    public static final LeaptionManager INSTANCE = new LeaptionManager();

    private LeaptionManager() {}

    private final HashMap<UUID, Leaption> leaptionEntries = new HashMap<>();
    private final Set<UUID> playersUsingLeaper = new HashSet<>();

    public Set<UUID> playersLeaping() {
        return leaptionEntries.keySet();
    }


    public void tick(MinecraftServer server) {
        PlayerManager playerManager = server.getPlayerManager();
        playerManager.getPlayerList().forEach(player -> {
            UUID uuid = player.getUuid();
            if(!player.isUsingItem() && playersUsingLeaper.contains(uuid)) cancelLeaption(player);
            if(player.isUsingItem() && player.getMainHandStack().getItem() == ModItems.LEAPER && !player.getItemCooldownManager().isCoolingDown(player.getActiveItem().getItem())) {
                if(!playersUsingLeaper.contains(uuid)) requestLeaption(player);
                playersUsingLeaper.add(uuid);
            } else if(playersUsingLeaper.contains(uuid)) cancelLeaption(player);
        });
        Set<UUID> leapsToDiscard = new HashSet<>();
        leaptionEntries.forEach((uuid, leaption) -> {
            ServerPlayerEntity player = playerManager.getPlayer(uuid);
            if(player == null) leapDisjunction(server, uuid); else {
                leaption.tick(player);
                if(leaption.readyToLeap()) {
                    player.requestTeleportOffset(leaption.getSpecifiedX(), 0, leaption.getSpecifiedZ());
                    leapsToDiscard.add(uuid);
                    player.getItemCooldownManager().set(ModItems.LEAPER, 20);
                    player.stopUsingItem();
                }

            }

        });
        leapsToDiscard.forEach(uuid -> {
            ServerPlayerEntity player = playerManager.getPlayer(uuid);
            if (player == null) leapDisjunction(server, uuid);
            else cancelLeaption(player);
        });
    }

    public void requestLeaption(ServerPlayerEntity player) {
        Leapers.LOGGER.info("requested");
        PacketByteBuf agreementBuf = PacketByteBufs.create();
        boolean cont = !INSTANCE.isPlayerLeaping(player.getUuid());
        agreementBuf.writeBoolean(cont);
        if(cont) {
            LeapGhostEntity leapGhostEntity = new LeapGhostEntity(player.getWorld(), player);
            leapGhostEntity.updateGhostAppearance(player);
            leapGhostEntity.setCustomName(player.getName());
            ItemStack itemStack = player.getMainHandStack();
            NbtCompound nbtCompound = itemStack.getNbt();
            assert nbtCompound != null;
            CrystalType crystalType = LeaperItem.getCrystalEntry(nbtCompound).get().getType();
            FixtureType fixtureType = LeaperItem.getFixtureEntry(nbtCompound).get().getType();
            int dischargeTime = LeaperItem.getCoreEntry(nbtCompound).get().getDischargeTime();
            int[] specifications = LeaperItem.getCrystalSpecifications(nbtCompound).get();
            Leaption leaption = new Leaption(
                    player,
                    leapGhostEntity.getUuid(),
                    crystalType,
                    fixtureType,
                    specifications[0],
                    specifications[1],
                    dischargeTime
            );
            agreementBuf.writeVarInt(leapGhostEntity.getId());

            ServerPlayNetworking.send(player, ModNetworking.START_LEAPTION, agreementBuf);
            INSTANCE.createLeaption(player, leapGhostEntity, leaption);
        }
    }

    public boolean isPlayerLeaping(UUID uuid) {
        return leaptionEntries.containsKey(uuid);
    }

    public void createLeaption(ServerPlayerEntity player, LeapGhostEntity leapGhostEntity, Leaption leaption) {
        player.getServerWorld().spawnEntity(leapGhostEntity);
        leaptionEntries.put(player.getUuid(), leaption);
    }

    public void handleShutdown(MinecraftServer server) {
        PlayerManager playerManager = server.getPlayerManager();
        leaptionEntries.forEach((uuid, leaption) -> {
            ServerPlayerEntity player = playerManager.getPlayer(uuid);
            if(player == null) leapDisjunction(server, uuid); else cancelLeaption(player);
        });
    }


    public float getPlayerLeapProgress(UUID uuid) {
        Leaption leaption = leaptionEntries.get(uuid);
        if(leaption == null) {
            Leapers.LOGGER.info("null leaption!");
            return 0f;
        }
        return (float) leaption.getProgressTicks() / leaption.getDischargeTime();
    }

    public void cancelLeaption(ServerPlayerEntity serverPlayer) {
        playersUsingLeaper.remove(serverPlayer.getUuid());
        Entity entity = ((ServerWorld) serverPlayer.getWorld()).getEntity(leaptionEntries.get(serverPlayer.getUuid()).getLeapGhostUUID());
        if(entity != null) entity.remove(Entity.RemovalReason.DISCARDED);
        leaptionEntries.remove(serverPlayer.getUuid());
        ServerPlayNetworking.send(serverPlayer, ModNetworking.STOP_LEAPTION, PacketByteBufs.create());
    }

    public void leapDisjunction(MinecraftServer server, UUID uuid) {
        Leapers.LOGGER.error("leap disjunction occurred. Canceling leap for UUID: "+uuid);
        playersUsingLeaper.remove(uuid);
        server.getWorlds().forEach(world -> {
            Entity entity = world.getEntity(leaptionEntries.get(uuid).getLeapGhostUUID());
            if(entity != null) entity.remove(Entity.RemovalReason.DISCARDED);
        });
        leaptionEntries.remove(uuid);
    }
}
