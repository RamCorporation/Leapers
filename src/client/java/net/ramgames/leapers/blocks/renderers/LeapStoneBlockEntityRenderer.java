package net.ramgames.leapers.blocks.renderers;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.ramgames.leapers.blocks.entity.LeapStoneBlockEntity;

public class LeapStoneBlockEntityRenderer implements BlockEntityRenderer<LeapStoneBlockEntity> {

    public LeapStoneBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(LeapStoneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

    }
}
