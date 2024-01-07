package net.ramgames.leapers.recipes;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;

public interface ModRecipeSerializers {

    RecipeSerializer<SurgeRecipe> SURGE_RECIPE_SERIALIZER = RecipeSerializer.register("surge", new SurgeRecipe.Serializer());

}
