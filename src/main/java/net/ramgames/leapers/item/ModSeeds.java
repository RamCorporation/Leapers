package net.ramgames.leapers.item;

import net.ramgames.leapers.Leapers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

public class ModSeeds {

    private static final HashMap<String, Float> modelSeedCache = new HashMap<>();
    private static final HashMap<UUID, Long> UUIDSeedCache = new HashMap<>();

    public static float getOrGenModelSeed(String string1, String string2, String string3, String string4) {
        String conglomeratedName = string1+'_'+string2+'_'+string3+'_'+string4;
        if(!modelSeedCache.containsKey(conglomeratedName)) modelSeedCache.put(conglomeratedName, Float.parseFloat("0."+ generator(conglomeratedName)));
        return modelSeedCache.get(conglomeratedName);
    }

    private static long generator(String conglomeratedString) {
        long seed = 0;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(conglomeratedString.getBytes());
            for(int i = 0; i < 7; i++) {
                seed = (seed << 8) | (digest[i] & 0xFF);
            }
        } catch (NoSuchAlgorithmException e) {
            Leapers.LOGGER.error(e.getMessage());
        }
        return seed;
    }

    public static long getOrGenUUIDSeed(UUID uuid) {
        if(!UUIDSeedCache.containsKey(uuid)) UUIDSeedCache.put(uuid, generator(uuid.toString()));
        return UUIDSeedCache.get(uuid);
    }

}
