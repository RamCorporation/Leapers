package net.ramgaming.leapers.recipes.surging;

import I;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.ramgaming.leapers.item.inventory.RecipeInventory;

public class SurgingRecipeSerializer implements RecipeSerializer<SurgingRecipe> {
    public static final SurgingRecipeSerializer INSTANCE = new SurgingRecipeSerializer();



    @Override
    public SurgingRecipe read(Identifier id, JsonObject json) {
        SurgingRecipeJsonFormat recipeJson = new Gson().fromJson(json,SurgingRecipeJsonFormat.class);
        /*HashMap<Item,Integer> ingredients = new HashMap<>();
        List<ItemStack> raw_ingredients = new ArrayList<>();
        for(var i = 0; i < recipeJson.ingredients.size(); i++) {
            raw_ingredients.add(new ItemStack(ItemRegistryInterpreter.itemFromString(recipeJson.ingredients.getAsJsonArray().get(i).getAsString()),recipeJson.ingredients.getAsJsonObject().get("count").getAsInt()));

        }

        for(ItemStack ingredient: raw_ingredients) {
            if(!ingredients.containsKey(ingredient.getItem())) {
                ingredients.put(ingredient.getItem(),ingredient.getCount());
            } else {
                ingredients.put(ingredient.getItem(),ingredient.getCount());
            }
        }
        raw_ingredients = new ArrayList<>();
        for(Item item: ingredients.keySet()) {
            raw_ingredients.add(new ItemStack(item,ingredients.get(item)));
        }
        RecipeInventory recipeInventory = new RecipeInventory(raw_ingredients.size());
        for(var i = 0; i < raw_ingredients.size(); i++) {
            recipeInventory.setStack(i,raw_ingredients.get(i));
        }
        */
        Item outputItem = Registries.ITEM.getOrEmpty(new Identifier(recipeJson.result)).get();
        ItemStack output = new ItemStack(outputItem, recipeJson.count);
        return new SurgingRecipe(new RecipeInventory(1),output, id);
    }

    @Override
    public SurgingRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient[] ingredients = new Ingredient[9];
        for(var i = 0; i < 9; i++) {
            ingredients[i] = Ingredient.fromPacket(buf);
        }
        ItemStack output = buf.readItemStack();
        return new SurgingRecipe(new RecipeInventory(1),output, id);
        //ingredients
    }

    @Override
    public void write(PacketByteBuf buf, SurgingRecipe recipe) {
        for(Ingredient ingredient: recipe.getIngredients()) {
            ingredient.write(buf);
        }
        buf.writeItemStack(recipe.getOutput());
    }
}
