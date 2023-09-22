package net.ramgames.leapers.entities.leapghost;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class LeapGhostRenderer extends LivingEntityRenderer<LeapGhostEntity, LeapGhostModel> {
    public LeapGhostRenderer(EntityRendererFactory.Context context, LeapGhostModel entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public Identifier getTexture(LeapGhostEntity entity) {
        if(entity.player == null) return new Identifier("minecraft:textures/missingno.png");
        return MinecraftClient.getInstance().getSkinProvider().getSkinTextures(new GameProfile(entity.player.getUuid(), entity.player.getName().getString())).texture();
    }
}
