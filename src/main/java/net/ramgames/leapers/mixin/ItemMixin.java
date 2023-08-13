package net.ramgames.leapers.mixin;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.api.data.LeaperRegistries;
import net.ramgames.leapers.api.modules.Core;
import net.ramgames.leapers.api.modules.Crystal;
import net.ramgames.leapers.api.modules.Fixture;
import net.ramgames.leapers.api.modules.Handle;
import net.ramgames.leapers.item.ModItems;
import net.ramgames.leapers.item.custom.LeaperItem;
import net.ramgames.leapers.item.tooltip.LeaperTooltipData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "getTooltipData", at = @At("HEAD"), cancellable = true)
    private void getTooltipDataMixin(ItemStack stack, CallbackInfoReturnable<Optional<TooltipData>> cir) {
        if(stack.getItem() != ModItems.LEAPER) return;
        if(stack.getNbt() == null) return;
        NbtCompound nbt = stack.getNbt();
        if(!LeaperItem.containsProperNbt(nbt)) return;
        Core core = LeaperRegistries.CORES.query(new Identifier(nbt.getString("core")));
        Handle handle = LeaperRegistries.HANDLES.query(new Identifier(nbt.getString("handle")));
        Fixture fixture = LeaperRegistries.FIXTURES.query(new Identifier(nbt.getString("fixture")));
        Crystal crystal = LeaperRegistries.CRYSTALS.query(new Identifier(nbt.getString("crystal")));
        if(core == null || handle == null || fixture == null || crystal == null) return;
        cir.setReturnValue(Optional.of(new LeaperTooltipData(
                new Identifier(core.getTooltipTexture()+".png"),
                new Identifier(handle.getTooltipTexture()+".png"),
                new Identifier(fixture.getTooltipTexture()+".png"),
                new Identifier(crystal.getTooltipTexture()+".png"),
                new int[] {nbt.getInt("Charges"), core.getMaxCharges()},
                new int[] {nbt.getInt("Damage"), handle.getMaxDurability()},
                new int[] {nbt.getInt("Transmittance"), fixture.getMaxTransmittance()},
                new int[] {nbt.getInt("Stability"), crystal.getMaxStability()}
        )));
    }

}
