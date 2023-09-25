package net.ramgames.leapers.leaption;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
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

    private LeaptionManager() {}

    private final HashMap<UUID, Leaption> leaptionEntries = new HashMap<>();


    public void tick(MinecraftServer server) {
        PlayerManager playerManager = server.getPlayerManager();
        leaptionEntries.forEach((uuid, leaption) -> {

            leaption.tick(playerManager.getPlayer(uuid));
        });
    }

    public boolean isPlayerLeaping(UUID uuid) {
        return leaptionEntries.containsKey(uuid);
    }

    public void createLeaption(MinecraftServer server, ServerPlayerEntity player, LeapGhostEntity leapGhostEntity, Leaption leaption) {
        player.getServerWorld().spawnEntity(leapGhostEntity);
        leaptionEntries.put(player.getUuid(), leaption);
        tick(server);
    }

    public void handleShutdown(MinecraftServer server) {
        PlayerManager playerManager = server.getPlayerManager();
        leaptionEntries.forEach((uuid, leaption) -> cancelLeaption(server, playerManager.getPlayer(uuid)));
    }

    public void cancelLeaption(MinecraftServer server, ServerPlayerEntity serverPlayer) {
        Entity entity = ((ServerWorld) serverPlayer.getWorld()).getEntity(leaptionEntries.get(serverPlayer.getUuid()).getLeapGhostUUID());
        if(entity != null) entity.remove(Entity.RemovalReason.DISCARDED);
        leaptionEntries.remove(serverPlayer.getUuid());
    }
}
