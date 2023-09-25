package net.ramgames.leapers.mixin.client;

import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.leaption.api.data.LeaperRegistries;
import net.ramgames.leapers.leaption.api.modules.Core;
import net.ramgames.leapers.leaption.api.modules.Crystal;
import net.ramgames.leapers.leaption.api.modules.Fixture;
import net.ramgames.leapers.leaption.api.modules.Handle;
import net.ramgames.leapers.items.ModItems;
import net.ramgames.leapers.items.custom.LeaperItem;
import net.ramgames.leapers.items.tooltip.LeaperTooltipData;
import net.ramgames.leapers.items.tooltip.SpyglassTooltipData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "getTooltipData", at = @At("HEAD"), cancellable = true)
    private void getTooltipDataMixin(ItemStack stack, CallbackInfoReturnable<Optional<TooltipData>> cir) {
        if(stack.getItem() == ModItems.LEAPER) getLeaperTooltip(stack, cir);
        if(stack.getItem() == Items.SPYGLASS) getSpyglassTooltip(stack, cir);
    }

    @Unique
    private void getLeaperTooltip(ItemStack stack, CallbackInfoReturnable<Optional<TooltipData>> cir) {
        NbtCompound nbt = stack.getNbt();
        if(LeaperItem.containsImproperNbt(nbt)) {
            cir.setReturnValue(Optional.of(new LeaperTooltipData()));
            return;
        }
        Core core = LeaperRegistries.CORES.get(new Identifier(nbt.getString("Core")));
        Handle handle = LeaperRegistries.HANDLES.get(new Identifier(nbt.getString("Handle")));
        Fixture fixture = LeaperRegistries.FIXTURES.get(new Identifier(nbt.getString("Fixture")));
        Crystal crystal = LeaperRegistries.CRYSTALS.get(new Identifier(nbt.getString("Crystal")));
        if(core == null || handle == null || fixture == null || crystal == null) {
            cir.setReturnValue(Optional.of(new LeaperTooltipData()));
            return;
        }
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

    @Unique
    private void getSpyglassTooltip(ItemStack stack, CallbackInfoReturnable<Optional<TooltipData>> cir) {
        if(stack.getNbt() == null) {
            cir.setReturnValue(Optional.of(new SpyglassTooltipData("Amethyst")));
            return;
        }
        NbtCompound nbt = stack.getNbt();
        cir.setReturnValue(Optional.of(new SpyglassTooltipData(!nbt.contains("lens") ? "Amethyst" : nbt.getString("lens"))));
    }

}
