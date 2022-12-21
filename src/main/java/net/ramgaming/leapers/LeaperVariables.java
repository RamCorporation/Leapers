package net.ramgaming.leapers;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LeaperVariables {
    public static List<BlockPos> KnownLeapPads = new ArrayList<>();
    public static HashMap<BlockPos, UUID> UuidLeapPads = new HashMap<>();
    public static BlockPos getLeapPad(World world, UUID uuid) {
        if(world.isClient()) {
            for (BlockPos knownLeapPad : KnownLeapPads) {
                if(UuidLeapPads.get(knownLeapPad).toString().equals(uuid.toString())) {
                    return knownLeapPad;
                }
            }
        }
        return null;
    }

}
