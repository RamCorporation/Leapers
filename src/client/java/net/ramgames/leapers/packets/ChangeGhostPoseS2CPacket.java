package net.ramgames.leapers.packets;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.ramgames.leapers.blocks.entity.CrystalInspectorBlockEntity;

public class ChangeGhostPoseS2CPacket {

    public static void receive(net.minecraft.client.MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf buf, PacketSender packetSender) {
        Entity entity = minecraftClient.world.getEntityById(buf.readVarInt());
        if(entity != null) entity.setPose(buf.readEnumConstant(EntityPose.class));
    }

}
