package net.ramgaming.leapers.recipes.surging;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.ramgaming.leapers.Leapers;
import net.ramgaming.leapers.item.inventory.RecipeInventory;

import java.util.HashMap;

public class SurgingRecipe implements Recipe<RecipeInventory> {
    public static final Identifier TYPE_ID = new Identifier(Leapers.MOD_ID,"surging");
    public static final RecipeType<SurgingRecipe> TYPE = new RecipeType<>() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };
    private final RecipeInventory inputs;
    private final Identifier id;
    private final ItemStack outputStack;
    public SurgingRecipe(RecipeInventory ingredients, ItemStack outputStack, Identifier id) {
        this.inputs = ingredients;
        this.outputStack = outputStack;
        this.id = id;
    }

    public RecipeInventory getInputs() {
        return this.inputs;
    }

    @Override
    public boolean matches(RecipeInventory given, World world) {
        HashMap<Item, Integer> clone = inputs.sortedHash();
        for(Item i: given.sortedHash().keySet()) {
            if(clone.containsKey(i)) {
                if(clone.get(i) > 1) {
                    clone.put(i,clone.get(i)-1);
                } else {
                    clone.remove(i);
                }
            } else {
                return false;
            }
        }
        return clone.isEmpty();
    }

    @Override
    public ItemStack craft(RecipeInventory inventory) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return outputStack;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SurgingRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

}
