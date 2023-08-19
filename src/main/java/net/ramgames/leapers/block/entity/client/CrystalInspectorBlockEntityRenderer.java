package net.ramgames.leapers.block.entity.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.ramgames.leapers.block.custom.CrystalInspectorBlock;
import net.ramgames.leapers.block.entity.CrystalInspectorBlockEntity;
import org.joml.Quaternionf;

public class CrystalInspectorBlockEntityRenderer implements BlockEntityRenderer<CrystalInspectorBlockEntity> {
    public CrystalInspectorBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(CrystalInspectorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        if(entity.getWorld() == null) return;
        ItemStack itemStack = entity.getRenderStack();
        //Leapers.LOGGER.info("{}",itemStack);
        matrices.push();
        matrices.translate(0.5f, 0.0833f, 0.5f);
        matrices.scale(0.65f, 0.65f, 0.65f);
        Quaternionf quaternionf = new Quaternionf().rotateX(-1.570796f);
        switch (entity.getCachedState().get(CrystalInspectorBlock.FACING)) {
            case NORTH -> quaternionf.rotateZ(3.141593f);
            case EAST -> quaternionf.rotateZ(4.712389f);
            case WEST -> quaternionf.rotateZ(1.570796f);
        }
        matrices.multiply(quaternionf);
        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(), entity.getPos()), 0, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}