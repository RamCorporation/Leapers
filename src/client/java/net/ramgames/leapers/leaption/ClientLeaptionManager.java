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
    private int leapTicks = 0;
    private int leapGhostId = 0;
    private int dischargeTime = 1;

    public void startLeap(int leapGhostId, int dischargeTime) {
        LeapersClient.LOGGER.info("started leap!");
        isLeaping = true;
        this.leapGhostId = leapGhostId;
        this.dischargeTime = dischargeTime;
    }

    public void cancelLeap() {
        isLeaping = false;
        this.leapTicks = 0;
    }

    public float getLeapProgress() {
        return  (float) leapTicks / dischargeTime;
    }

    public void tick() {
        if(leapTicks < dischargeTime) leapTicks++;
    }

    public boolean isLeaping() {
        return isLeaping;
    }

    public int getLeapGhostId() {
        return leapGhostId;
    }

    public LeapGhostEntity getLeapGhost() {
        return (LeapGhostEntity) MinecraftClient.getInstance().world.getEntityById(leapGhostId);
    }


}
