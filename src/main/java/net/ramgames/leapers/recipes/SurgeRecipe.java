package net.ramgames.leapers.recipes;

import com.mojang.serialization.Codec;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

public class SurgeRecipe implements Recipe<Inventory> {
    @Override
    public boolean matches(Inventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }

    public static class Serializer implements RecipeSerializer<SurgeRecipe> {


        @Override
        public Codec<SurgeRecipe> codec() {
            return null;
        }

        @Override
        public SurgeRecipe read(PacketByteBuf buf) {
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, SurgeRecipe recipe) {

        }
    }
}
