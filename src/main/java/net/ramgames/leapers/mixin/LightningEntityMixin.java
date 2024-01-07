package net.ramgames.leapers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ramgames.leapers.blocks.ModBlocks;
import net.ramgames.leapers.blocks.custom.FusionTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin extends Entity {

    public LightningEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow protected abstract BlockPos getAffectedBlockPos();

    @Inject(method = "powerLightningRod", at = @At("TAIL"))
    private void powerFusionTable(CallbackInfo ci) {
        BlockPos blockPos = getAffectedBlockPos();
        BlockState state = getWorld().getBlockState(blockPos);

        if(state.isOf(ModBlocks.FUSION_TABLE)) ((FusionTableBlock)state.getBlock()).setPowered(state, this.getWorld(), blockPos);
    }

}
