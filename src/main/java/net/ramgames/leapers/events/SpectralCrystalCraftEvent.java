package net.ramgames.leapers.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.ramgames.leapers.items.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface SpectralCrystalCraftEvent {
    static void start(ServerWorld world) {
        List<LightningEntity> bolts = new ArrayList<>();
        List<ItemEntity> items = new ArrayList<>();
        for(Entity entity: world.iterateEntities()) {
            if(entity instanceof LightningEntity lightningEntity) {
                bolts.add(lightningEntity);
            }
            if(entity instanceof ItemEntity itemEntity) {
                items.add(itemEntity);
            }
        }
        if(!(bolts.size() == 0) && !(items.size() == 0)) {
            for (LightningEntity bolt : bolts) {
                boolean[] checklist = new boolean[8];
                UUID[] uuids = new UUID[9];
                for(ItemEntity itemE: items) {
                    if (((int) bolt.getX() == (int) itemE.getX()) && ((int) bolt.getY() == (int) itemE.getY()) && ((int) bolt.getZ() == (int) itemE.getZ())) {
                        Item item  = itemE.getStack().getItem();

                        if(item == ModItems.AERIS_CRYSTAL) {checklist[0] = true;uuids[0]=itemE.getUuid();}
                        if(item == ModItems.ALLURE_CRYSTAL) {checklist[1] = true;uuids[1]=itemE.getUuid();}
                        if(item == ModItems.HORA_CRYSTAL) {checklist[2] = true;uuids[2]=itemE.getUuid();}
                        if(item == ModItems.FERVIS_CRYSTAL) {checklist[3] = true;uuids[3]=itemE.getUuid();}
                        if(item == ModItems.GALVA_CRYSTAL) {checklist[4] = true;uuids[4]=itemE.getUuid();}
                        if(item == ModItems.UMBER_CRYSTAL) {checklist[5] = true;uuids[5]=itemE.getUuid();}
                        if(item == ModItems.MEMORIA_CRYSTAL) {checklist[6] = true;uuids[6]=itemE.getUuid();}
                        if(item == Items.AMETHYST_SHARD) {checklist[7] = true;uuids[7]=itemE.getUuid();}
                    }
                }
                if(allTrue(checklist)) {
                    Vec3d pos = Objects.requireNonNull(world.getEntity(uuids[0])).getPos();
                    ItemEntity itemEntity = new ItemEntity(world,pos.x,pos.y,pos.z,new ItemStack(ModItems.SPECTRAL_CRYSTAL));
                    itemEntity.setInvulnerable(true);
                    world.spawnEntity(itemEntity);
                }
            }

        }
    }
    private static boolean allTrue(boolean[] list) {
        for (boolean b : list) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
