package net.ramgaming.leapers.recipes;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.recipes.surging.SurgingRecipe;
import net.ramgaming.leapers.recipes.surging.SurgingRecipeSerializer;

public class RegisterRecipeTypes {
    public static void onInitialize() {
        register(SurgingRecipe.TYPE_ID, SurgingRecipeSerializer.INSTANCE,SurgingRecipe.TYPE);

    }
    private static void register(Identifier id, RecipeSerializer<?> serializer, RecipeType<?> recipe) {
        Registry.register(Registries.RECIPE_SERIALIZER,  id, serializer);
        Registry.register(Registries.RECIPE_TYPE,id,recipe);
    }
}
