package net.ramgames.leapers.leaption;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.ramgames.leapers.LeapersClient;
import net.ramgames.leapers.entities.leapghost.LeapGhostEntity;
import net.ramgames.leapers.leaption.api.modules.CrystalType;
import net.ramgames.leapers.leaption.api.modules.FixtureType;

@Environment(EnvType.CLIENT)
public class ClientLeaptionManager {

    public static ClientLeaptionManager INSTANCE =  new ClientLeaptionManager();

    private ClientLeaptionManager() {}

    private boolean isLeaping = false;
    private int leapGhostId = 0;

    public void startLeap(int leapGhostId) {
        isLeaping = true;
        this.leapGhostId = leapGhostId;
    }

    public void cancelLeap() {
        isLeaping = false;
        leapGhostId = 0;
    }

    public boolean isLeaping() {
        return isLeaping;
    }


    public LeapGhostEntity getLeapGhost() {
        return (LeapGhostEntity) MinecraftClient.getInstance().world.getEntityById(leapGhostId);
    }


}
