package net.ramgaming.leapers.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.ramgaming.leapers.RegisterTags.AERIS_TYPE;
import static net.ramgaming.leapers.RegisterTags.LEAPER_TAG;

public class Leap {
    public static TypedActionResult<ItemStack> Start(PlayerEntity player, World world, Hand hand) {
        if (!world.isClient() && hand == Hand.MAIN_HAND) {
            player.sendMessage(Text.literal("CLICKED"));
            ItemStack MH_STACK = player.getStackInHand(Hand.MAIN_HAND);
            Item MH_ITEM = MH_STACK.getItem();
            if (MH_STACK.isIn(LEAPER_TAG)) {
                player.sendMessage(Text.literal("CLICKED LEAPER"));
                if(MH_STACK.isIn(AERIS_TYPE)) {
                    player.sendMessage(Text.literal("AERIS LEAPER"));
                    AerisType(player, world, hand);
                }
            }
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }
    private static void AerisType(PlayerEntity player, World world, Hand hand){
        player.getItemCooldownManager().set(player.getMainHandStack().getItem(), 100);
        if(canSeeSky(player,world)) {
            player.sendMessage(Text.literal("Can see sky"));
            NbtCompound NBT = player.getMainHandStack().getNbt();
            if(NBT == null) {
                player.sendMessage(Text.literal("NBT is null"), false);
            }else {
                if(NBT.getIntArray("leapingPos") != null) {
                    player.sendMessage(Text.literal("found leapingPos"), false);
                    int[] posers = NBT.getIntArray("leapingPos");
                    int yer = world.getTopY();
                    while(world.getBlockState(new BlockPos(posers[0],yer,posers[1])).isAir()) {
                        yer -= 1;
                        player.sendMessage(Text.literal("yer is "+ yer));
                        if(yer < world.getBottomY()) {
                            player.sendMessage(Text.literal("WHILE loop fail"));
                            break;
                        }
                    }
                    yer += 1;
                    player.sendMessage(Text.literal(String.format("moving player to: (%s,%s,%s)",player.getX(),player.getY(),player.getZ())), false);

                    if (player.hasVehicle()) {
                        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
                        serverPlayerEntity.requestTeleportAndDismount((double) posers[0]+0.5, yer, (double) posers[1]+0.5);
                    } else {
                        player.requestTeleport((double) posers[0]+0.5, yer, (double) posers[1]+0.5);
                    }
                    player.sendMessage(Text.literal(String.format("moving player to: (%s,%s,%s)",player.getX(),player.getY(),player.getZ())), false);
                }
                else {
                    player.sendMessage(Text.literal("No Leaping Cords Set"), false);
                }
            }


        } else {
            player.sendMessage(Text.literal("Can't Leap Underground"), false);
        }
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
    private static int averageLightLevel(World world) {
        int time = (int) world.getTime();
        if(between(time,12501,23499)) {
            return 0;
        }
        int quant = 0;
        if(between(time,23500,23999) || time == 0) {
            quant = 500;
        } else {
            if(time > 7000) {
                quant =  15 - (-6500+time)/433;
            } else {
                quant =(500+time)/433;
            }

        }
        return quant;
    }
    private static boolean between(int amount, int min, int max) {
        return between(amount,min,max,true,true);
    }
    private static boolean between(int amount, int min, int max,boolean equalto1,boolean equalto2) {
        if(equalto1) {min -=1;}
        if(equalto2) {max +=1;}
        if(amount < max && amount > min) {
            return true;
        }
        return false;
    }
}
