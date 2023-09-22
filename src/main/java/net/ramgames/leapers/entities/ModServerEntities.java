package net.ramgames.leapers.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;

public class ModServerEntities {

    public static final EntityType<LeapGhostEntity> LEAP_GHOST = registerLeapGhostEntity("leap_ghost");


    public static EntityType<LeapGhostEntity> registerLeapGhostEntity(String entityId) {
        Identifier entityIdentifier = new Identifier(Leapers.MOD_ID, entityId);
        EntityType<LeapGhostEntity> entityType = Registry.register(Registries.ENTITY_TYPE,
                entityIdentifier,
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<LeapGhostEntity>) (type, world) -> new LeapGhostEntity(type, world, null)).dimensions(EntityDimensions.fixed(0.5f,2)).build()
        );
        FabricDefaultAttributeRegistry.register(entityType, LeapGhostEntity.createLivingAttributes());
        return entityType;
    }

    public static void registerEntities() {
        Leapers.LOGGER.info("loading entities...");
    }
}
