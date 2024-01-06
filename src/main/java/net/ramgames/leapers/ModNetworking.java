package net.ramgames.leapers;


import net.minecraft.util.Identifier;


public interface ModNetworking {

    Identifier ITEM_SYNC = new Identifier(Leapers.MOD_ID,"item_sync");

    Identifier START_LEAPTION = new Identifier(Leapers.MOD_ID, "leaption_agreement");

    Identifier STOP_LEAPTION = new Identifier(Leapers.MOD_ID, "abandon_leaption");

    Identifier CHANGE_GHOST_POSE = new Identifier(Leapers.MOD_ID, "change_ghost_pose");

}