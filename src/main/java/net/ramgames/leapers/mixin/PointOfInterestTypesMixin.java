package net.ramgames.leapers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.ramgames.leapers.blocks.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Mixin(PointOfInterestTypes.class)
public abstract class PointOfInterestTypesMixin {

    @Inject(method = "isPointOfInterest", at = @At("RETURN"), cancellable = true)
    private static void makeFusionTablePOI(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if(ModBlocks.FUSION_TABLE.equals(state.getBlock())) cir.setReturnValue(true);
    }

    @Inject(method = "getTypeForState", at = @At("RETURN"), cancellable = true)
    private static void makeFusionTableLightningRodPI(BlockState state, CallbackInfoReturnable<Optional<RegistryEntry<PointOfInterestType>>> cir) {
        if(ModBlocks.FUSION_TABLE.equals(state.getBlock())) cir.setReturnValue(PointOfInterestTypes.getTypeForState(Blocks.LIGHTNING_ROD.getDefaultState()));
    }


}
