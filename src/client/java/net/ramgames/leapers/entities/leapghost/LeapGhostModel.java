package net.ramgames.leapers.entities.leapghost;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.ramgames.leapers.leaption.ClientLeaptionManager;

public class LeapGhostModel extends BipedEntityModel<LeapGhostEntity> {
    public LeapGhostModel(ModelPart root) {
        super(root, RenderLayer::getEntityTranslucent);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        ImmutableList.of(
                this.body,
                this.hat,
                this.head,
                this.leftArm,
                this.leftLeg,
                this.rightArm,
                this.rightLeg
        ).forEach((modelRenderer) -> modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha));
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = getModelData(Dilation.NONE, 0.0f);
        return TexturedModelData.of(modelData, 64, 64);
    }
}
