package net.ramgames.leapers.entities.leapghost;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.Leapers;

import static net.ramgames.leapers.entities.ModServerEntities.LEAP_GHOST;

public class ModClientEntities {

    public static void register() {
        EntityModelLayer entityModelLayer = new EntityModelLayer(new Identifier(Leapers.MOD_ID, "leap_ghost"), "main");
        EntityModelLayerRegistry.registerModelLayer(entityModelLayer, LeapGhostModel::getTexturedModelData);
        EntityRendererRegistry.register(LEAP_GHOST, (context) -> new LeapGhostRenderer(context, new LeapGhostModel(context.getPart(entityModelLayer)), true,0.5f));

    }


}
