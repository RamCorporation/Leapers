package net.ramgames.leapers.block.entity.client;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.ramgames.leapers.block.entity.CrystalCutterBlockEntity;

public class  CrystalCutterBlockEntityRenderer implements BlockEntityRenderer<CrystalCutterBlockEntity> {
    public CrystalCutterBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(CrystalCutterBlockEntity entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {
        /**
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = entity.getRenderStack(entity.getWorld());
        matrices.push();
        matrices.translate(0.5f, 0.645f, 0.5f);
        matrices.scale(0.2f, 0.2f, 0.2f);
        matrices.multiply(Vector3f..getDegreesQuaternion(-90));

        switch (entity.getCachedState().get(CrystalCutterBlock.FACING)) {
            case NORTH -> matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180));
            case EAST -> matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(270));
            case SOUTH -> matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(0));
            case WEST -> matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(90));
        }

        itemRenderer.renderItem(itemStack, ModelTransformation.Mode.GUI, getLightLevel(entity.getWorld(), entity.getPos()),
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 1);
        matrices.pop();
         **/
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}