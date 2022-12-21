package net.ramgaming.leapers.events;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.ramgaming.leapers.RegisterTags.AERIS_TYPE;
import static net.ramgaming.leapers.RegisterTags.LEAPER_TAG;

public class LeapEvent{
    public static TypedActionResult<ItemStack> start(PlayerEntity player, World world, Hand hand) {
        if (!world.isClient() && hand == Hand.MAIN_HAND) {
            ItemStack MH_STACK = player.getStackInHand(Hand.MAIN_HAND);
            if (MH_STACK.isIn(LEAPER_TAG)) {
                if(MH_STACK.isIn(AERIS_TYPE)) {
                    AerisType(player, world, hand);
                }
            }
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }
    private static void AerisType(PlayerEntity player, World world, Hand hand){
        player.getItemCooldownManager().set(player.getMainHandStack().getItem(), 100);
        if(!canSeeSky(player,world)) {
            player.sendMessage(Text.literal("Can't Leap Underground").formatted(Formatting.RED), false);
            return;
        }
        NbtCompound NBT = player.getMainHandStack().getNbt();
        if(NBT == null) {
            return;
        }
        if(NBT.getIntArray("leapingPos") == null) {
            player.sendMessage(Text.literal("No Leaping Cords Set").formatted(Formatting.RED), true);
            return;
        }
            int[] posers = new int[]{NBT.getIntArray("leapingPos")[0],world.getTopY(),NBT.getIntArray("leapingPos")[1]};
            while(world.getBlockState(new BlockPos(posers[0],posers[1],posers[2])).isAir()) {
                posers[1] -= 1;
                if(posers[1] < world.getBottomY()) {
                    player.sendMessage(Text.literal("failed to complete leaping process").formatted(Formatting.RED));
                    break;
                }
            }
            posers[1] += 1;
            movePlayerTo(player,posers);
            player.fallDistance = 0;
            world.playSound(null,new BlockPos(posers[0],posers[1],posers[2]), SoundEvents.ENTITY_ENDER_EYE_DEATH, SoundCategory.PLAYERS,1f,1f);
            player.damage(DamageSource.GENERIC,calcLightLevelPenalty(world,new BlockPos(player.getX(),player.getY(),player.getZ()),new BlockPos(posers[0],posers[1],posers[2])));
    }
    private static boolean canSeeSky(PlayerEntity player,World world) {
        int[] poser = new int[]{(int) player.getX(),(int) player.getY(), (int) player.getZ()};
        while(poser[1] <= world.getTopY()) {
            if(!world.getBlockState(new BlockPos(poser[0],poser[1],poser[2])).isAir()) {
                return false;
            }
            poser[1] += 1;
            player.sendMessage(Text.literal(String.valueOf(poser[1])), false);
        }
        return true;
    }
    private static int calcLightLevelPenalty(World world,BlockPos start,BlockPos dest) {
        int time = (int) world.getTime();
        int quant = 0;
        if(between(time,23500,23999) || time == 0) {
            quant = 23500/433;
        } else {
            if(time <= 12500) {
                if (time > 7000) {
                    quant = 15 - (-6500 + time) / 433;
                } else {
                    quant = (500 + time) / 433;
                }
            }

        }
        if(quant < 12 ) {
            quant = 15-quant;
        } else {
            quant = 0;
        }
        int avgposlight = (world.getLightLevel(start)+world.getLightLevel(dest))/2;
        if(avgposlight < 12) {
            avgposlight = 15-avgposlight;
        } else {
            avgposlight = 0;
        }
        if (15-quant-avgposlight < 0) {
            return 0;
        }
        return 15-quant-avgposlight;
    }
    private static boolean between(int amount, int min, int max) {
        return between(amount,min,max,true,true);
    }
    private static boolean between(int amount, int min, int max,boolean equalto1,boolean equalto2) {
        if(equalto1) {min -=1;}
        if(equalto2) {max +=1;}
        return amount < max && amount > min;
    }
    private static void movePlayerTo(PlayerEntity player, int[] posers) {
        if (player.hasVehicle()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            serverPlayerEntity.requestTeleportAndDismount((double) posers[0]+0.5, posers[1], (double) posers[2]+0.5);
        } else {
            player.requestTeleport((double) posers[0]+0.5, posers[1], (double) posers[2]+0.5);
        }
    }
}
