package net.ramgames.leapers.events;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.ramgames.leapers.ModNetworking;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.leaption.ClientLeaptionManager;

public class ClientTickEvent {

    private static boolean wasPrevPressed = false;

    public static void start(MinecraftClient minecraftClient) {

        if(ClientLeaptionManager.INSTANCE.isLeaping()) ClientLeaptionManager.INSTANCE.tick();
        if(minecraftClient.player == null || minecraftClient.player.getMainHandStack().getItem() != ModItems.LEAPER) return;
        ClientPlayerEntity player = minecraftClient.player;
        boolean pressed = minecraftClient.options.useKey.isPressed();
        if(pressed) {
            if(!wasPrevPressed) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeItemStack(player.getMainHandStack());
                ClientPlayNetworking.send(ModNetworking.REQUEST_LEAPTION, buf);
            }
            wasPrevPressed = true;
        } else if (wasPrevPressed) {
            ClientLeaptionManager.INSTANCE.cancelLeap();
            ClientPlayNetworking.send(ModNetworking.ABANDON_LEAPTION, PacketByteBufs.create());
            wasPrevPressed = false;
        }

    }

}
