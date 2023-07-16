package net.ramgaming.leapers.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.ramgaming.leapers.block.entity.CrystalCutterBlockEntity;

public class ItemStackSyncS2CPacket {

    public static void receive(net.minecraft.client.MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {

        BlockPos position = buf.readBlockPos();
        if(minecraftClient.world == null) return;
        if(minecraftClient.world.getBlockEntity(position) instanceof CrystalCutterBlockEntity blockEntity) {
            blockEntity.setInventory(buf.readItemStack());
            blockEntity.markDirty();
        }
    }
}

