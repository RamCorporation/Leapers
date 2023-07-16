package net.ramgaming.leapers.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.block.ModBlocks;

public class ModBlockEntities {
    public static BlockEntityType<CrystalCutterBlockEntity> CRYSTAL_CUTTER;
    public static BlockEntityType<RedstoneRayBlockEntity> REDSTONE_RAY;
    public static BlockEntityType<LeapPadBlockEntity> LEAP_PAD;

    public static void onInitialize() {
        CRYSTAL_CUTTER = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Leapers.MOD_ID, "crystal_cutter"),
                FabricBlockEntityTypeBuilder.create(CrystalCutterBlockEntity::new,ModBlocks.CRYSTAL_CUTTER).build());
        REDSTONE_RAY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Leapers.MOD_ID, "redstone_ray"),
                FabricBlockEntityTypeBuilder.create(RedstoneRayBlockEntity::new,ModBlocks.REDSTONE_RAY).build());
        LEAP_PAD = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Leapers.MOD_ID, "leap_pad"),
                FabricBlockEntityTypeBuilder.create(LeapPadBlockEntity::new,ModBlocks.LEAP_PAD).build());

    }
}
