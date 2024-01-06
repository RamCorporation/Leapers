package net.ramgames.leapers.entities.leapghost;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.*;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class LeapGhostRenderer extends LivingEntityRenderer<LeapGhostEntity, LeapGhostModel>  {
    public LeapGhostRenderer(EntityRendererFactory.Context context, LeapGhostModel entityModel, boolean slim, float f) {
        super(context, entityModel, f);
        this.addFeature(new ArmorFeatureRenderer<>(this, new ArmorEntityModel<>(context.getPart(slim ? EntityModelLayers.PLAYER_SLIM_INNER_ARMOR : EntityModelLayers.PLAYER_INNER_ARMOR)), new ArmorEntityModel<>(context.getPart(slim ? EntityModelLayers.PLAYER_SLIM_OUTER_ARMOR : EntityModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
    }

    @Override
    public Identifier getTexture(LeapGhostEntity entity) {
        if(entity.player == null) return new Identifier("minecraft:textures/missingno.png");
        return MinecraftClient.getInstance().getSkinProvider().getSkinTextures(new GameProfile(entity.player.getUuid(), entity.player.getName().getString())).texture();
    }
}
