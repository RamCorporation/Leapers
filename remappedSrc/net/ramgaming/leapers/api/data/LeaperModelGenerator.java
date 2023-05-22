package net.ramgaming.leapers.api.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import I;
import java.math.BigDecimal;

public class LeaperModelGenerator extends FabricModelProvider {

    public LeaperModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    public static float genId(String string) {
        StringBuilder builder = new StringBuilder("0.");
        for(var i = 0; i < string.length(); i++) if (i % 2 != 0) builder.append((int) string.charAt(i));
        return new BigDecimal(builder.toString()).floatValue();
    }
}
