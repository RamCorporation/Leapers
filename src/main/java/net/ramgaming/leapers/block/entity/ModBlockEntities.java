package net.ramgaming.leapers.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.block.ModBlocks;

public class ModBlockEntities {
    public static BlockEntityType<CrystalCutterBlockEntity> SKY_GAZER;

    public static void registerAllBlockEntities() {
        SKY_GAZER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(Leapers.MOD_ID, "sky_gazer"),
                FabricBlockEntityTypeBuilder.create(CrystalCutterBlockEntity::new, ModBlocks.CRYSTAL_CUTTER).build(null));

    }
}
