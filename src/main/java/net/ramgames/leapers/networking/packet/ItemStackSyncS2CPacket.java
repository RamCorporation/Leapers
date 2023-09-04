package net.ramgames.leapers.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.ramgames.leapers.blocks.entity.CrystalInspectorBlockEntity;

public class ItemStackSyncS2CPacket {

    public static void receive(net.minecraft.client.MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {
        BlockPos position = buf.readBlockPos();
        if(minecraftClient.world == null) return;
        if(minecraftClient.world.getBlockEntity(position) instanceof CrystalInspectorBlockEntity blockEntity) {
            ItemStack stack = buf.readItemStack();
            blockEntity.setInventory(stack);
            blockEntity.markDirty();
        }
    }
}

