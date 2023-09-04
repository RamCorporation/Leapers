package net.ramgames.leapers.blocks.entity.client;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.ramgames.leapers.blocks.entity.ModBlockEntities;

public class ModBlockEntityRenderers {

    public static void register() {
        BlockEntityRendererFactories.register(ModBlockEntities.CRYSTAL_INSPECTOR, CrystalInspectorBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.LEAP_STONE, LeapStoneBlockEntityRenderer::new);
    }

    public static int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

}
