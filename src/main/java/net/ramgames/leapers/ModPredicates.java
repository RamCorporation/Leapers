package net.ramgames.leapers;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.ramgames.leapers.api.data.LeaperRegistries;
import net.ramgames.leapers.api.modules.Core;
import net.ramgames.leapers.api.modules.Crystal;
import net.ramgames.leapers.api.modules.Fixture;
import net.ramgames.leapers.api.modules.Handle;
import net.ramgames.leapers.item.ModItems;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ModPredicates {

    private static final HashMap<String, Float> encodedPredicateCache = new HashMap<>();

    public static float getOrGenSeed(String string1, String string2, String string3, String string4) {
        String conglomeratedName = string1+'_'+string2+'_'+string3+'_'+string4;
        if(!encodedPredicateCache.containsKey(conglomeratedName)) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] digest = messageDigest.digest(conglomeratedName.getBytes());
                long seed = 0;
                for(int i = 0; i < 7; i++) {
                    seed = (seed << 8) | (digest[i] & 0xFF);
                }
                encodedPredicateCache.put(conglomeratedName, Float.parseFloat("0."+ seed));
            } catch (NoSuchAlgorithmException e) {
                Leapers.LOGGER.error(e.getMessage());
            }
        }
        return encodedPredicateCache.get(conglomeratedName);
    }
    public static void register() {
        ModelPredicateProviderRegistry.register(ModItems.LEAPER, new Identifier("model_seed"), (itemStack, clientWorld, livingEntity, rand) -> {
            if(itemStack.getNbt() == null) return 0.0F;
            NbtCompound nbt = itemStack.getNbt();
            if(!nbt.contains("core")) return 0.0F;
            Identifier coreID = new Identifier(nbt.getString("core"));
            if(!LeaperRegistries.CORES.contains(coreID)) return 0.0F;
            if(!nbt.contains("handle")) return 0.0F;
            Identifier handleID = new Identifier(nbt.getString("handle"));
            if(!LeaperRegistries.HANDLES.contains(handleID)) return 0.0F;
            if(!nbt.contains("fixture")) return 0.0F;
            Identifier fixtureID = new Identifier(nbt.getString("fixture"));
            if(!LeaperRegistries.FIXTURES.contains(fixtureID)) return 0.0F;
            if(!nbt.contains("crystal")) return 0.0F;
            Identifier crystalID = new Identifier(nbt.getString("crystal"));
            if(!LeaperRegistries.CRYSTALS.contains(crystalID)) return 0.0F;

            Core core = LeaperRegistries.CORES.query(coreID);
            Handle handle = LeaperRegistries.HANDLES.query(handleID);
            Fixture fixture = LeaperRegistries.FIXTURES.query(fixtureID);
            Crystal crystal = LeaperRegistries.CRYSTALS.query(crystalID);
            //if(livingEntity != null) livingEntity.sendMessage(Text.of(String.valueOf(getOrGenSeed(core.getName(), handle.getName(), fixture.getName(), crystal.getName()))));
            //return getOrGenSeed(core.getName(), handle.getName(), fixture.getName(), crystal.getName());
            return 0.0F;
        });
    }
}
