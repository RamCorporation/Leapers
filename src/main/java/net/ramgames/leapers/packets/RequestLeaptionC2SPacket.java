package net.ramgames.leapers.packets;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.ModNetworking;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;
import net.ramgames.leapers.items.custom.LeaperItem;
import net.ramgames.leapers.leaption.Leaption;
import net.ramgames.leapers.leaption.api.modules.CrystalType;
import net.ramgames.leapers.leaption.api.modules.FixtureType;

import static net.ramgames.leapers.leaption.LeaptionManager.INSTANCE;


public class RequestLeaptionC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        Leapers.LOGGER.info("requested");
        PacketByteBuf agreementBuf = PacketByteBufs.create();
        boolean cont = !INSTANCE.isPlayerLeaping(player.getUuid());

        agreementBuf.writeBoolean(cont);
        if(cont) {
            LeapGhostEntity leapGhostEntity = new LeapGhostEntity(player.getWorld(), player);
            leapGhostEntity.updateGhostAppearance(player);
            leapGhostEntity.setCustomName(player.getName());
            ItemStack itemStack = buf.readItemStack();
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
            agreementBuf.writeInt(dischargeTime);
            INSTANCE.createLeaption(server, player, leapGhostEntity, leaption);
        }
        ServerPlayNetworking.send(player, ModNetworking.LEAPTION_AGREEMENT, agreementBuf);
    }
}
