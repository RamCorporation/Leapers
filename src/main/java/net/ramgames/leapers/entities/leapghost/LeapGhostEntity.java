package net.ramgames.leapers.entities.leapghost;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.entities.ModServerEntities;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class LeapGhostEntity extends LivingEntity {

    public DefaultedList<ItemStack> main;
    public DefaultedList<ItemStack> armor;
    public DefaultedList<ItemStack> offHand;

    public PlayerEntity player;


    public LeapGhostEntity(EntityType<? extends LivingEntity> entityType, World world, PlayerEntity player) {
        super(entityType, world);
        this.player = player;
        if(player != null) setParams();
        this.setInvulnerable(true);
    }
    public LeapGhostEntity(World world, PlayerEntity player) {
        this(ModServerEntities.LEAP_GHOST, world, player);
    }

    private void setParams() {
        main = DefaultedList.ofSize(1, player.getMainHandStack());
        offHand = DefaultedList.ofSize(1, player.getOffHandStack());
        armor = DefaultedList.ofSize(4);
        player.getArmorItems().forEach(armor::add);
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new LeapGhostSpawnPacket(this, this.player);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        this.player = ((LeapGhostSpawnPacket) packet).getPlayer();
        if(player != null) setParams();
        super.onSpawnPacket(packet);
    }


    @Override
    public Iterable<ItemStack> getArmorItems() {
        if(armor == null) return new Iterable<>() {
            @NotNull
            @Override
            public Iterator<ItemStack> iterator() {
                return new Iterator<>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public ItemStack next() {
                        return ItemStack.EMPTY;
                    }
                };
            }
        };
        return armor;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        if(main == null) return ItemStack.EMPTY;
        if(slot == EquipmentSlot.MAINHAND) return main.get(0);
        if(slot == EquipmentSlot.OFFHAND) return offHand.get(0);
        return armor.get(slot.getEntitySlotId());
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        if(slot == EquipmentSlot.MAINHAND) {
            main.set(0, stack);
            return;
        }
        if(slot == EquipmentSlot.OFFHAND) {
            offHand.set(0, stack);
            return;
        }
        armor.set(Math.abs(slot.getArmorStandSlotId() - 4), stack);
    }

    @Override
    public Arm getMainArm() {
        if(player == null) return Arm.RIGHT;
        return player.getMainArm();
    }



    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    public void updateGhostAppearance(ServerPlayerEntity player) {
        this.equipStack(EquipmentSlot.HEAD, player.getEquippedStack(EquipmentSlot.HEAD));
        this.equipStack(EquipmentSlot.CHEST, player.getEquippedStack(EquipmentSlot.CHEST));
        this.equipStack(EquipmentSlot.LEGS, player.getEquippedStack(EquipmentSlot.LEGS));
        this.equipStack(EquipmentSlot.FEET, player.getEquippedStack(EquipmentSlot.FEET));
        this.equipStack(EquipmentSlot.MAINHAND, player.getEquippedStack(EquipmentSlot.MAINHAND));
        this.equipStack(EquipmentSlot.OFFHAND, player.getEquippedStack(EquipmentSlot.OFFHAND));
        //this.setYaw(player.getYaw());
        //this.setPitch(player.getPitch());
    }
}
