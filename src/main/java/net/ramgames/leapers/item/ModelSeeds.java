package net.ramgames.leapers.item;

import net.ramgames.leapers.Leapers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ModelSeeds {

    private static final HashMap<String, Float> modelSeedCache = new HashMap<>();

    public static float getOrGenSeed(String string1, String string2, String string3, String string4) {
        String conglomeratedName = string1+'_'+string2+'_'+string3+'_'+string4;
        if(!modelSeedCache.containsKey(conglomeratedName)) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                byte[] digest = messageDigest.digest(conglomeratedName.getBytes());
                long seed = 0;
                for(int i = 0; i < 7; i++) {
                    seed = (seed << 8) | (digest[i] & 0xFF);
                }
                modelSeedCache.put(conglomeratedName, Float.parseFloat("0."+ seed));
            } catch (NoSuchAlgorithmException e) {
                Leapers.LOGGER.error(e.getMessage());
            }
        }
        return modelSeedCache.get(conglomeratedName);
    }
}
