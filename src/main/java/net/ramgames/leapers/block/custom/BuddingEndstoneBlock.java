package net.ramgames.leapers.block.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.dimension.DimensionTypes;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.ModTags;
import net.ramgames.leapers.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;

public class BuddingEndstoneBlock extends Block {
    public BuddingEndstoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(random.nextInt(5) != 0) return;
        Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
        BlockPos blockPos = pos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        if(blockState.isIn(ModTags.CRYSTAL_BUDS)) {
            if(!blockState.get(CrystalClusterBlock.CAN_GROW)) return;
            boolean randomCheck = random.nextInt(3) == 0;
            if(blockState.isIn(ModTags.UMBER_BUDS)) {
                if(direction == Direction.UP) return;
                changeBud(!umberCheck(world, blockPos), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_UMBER_BUD, ModBlocks.MEDIUM_UMBER_BUD, ModBlocks.LARGE_UMBER_BUD, ModBlocks.UMBER_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.AERIS_BUDS)) {
                changeBud(!aerisCheck(blockState), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_AERIS_BUD, ModBlocks.MEDIUM_AERIS_BUD, ModBlocks.LARGE_AERIS_BUD, ModBlocks.AERIS_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.FERVIS_BUDS)) {
                changeBud(!fervisCheck(world), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_FERVIS_BUD, ModBlocks.MEDIUM_FERVIS_BUD, ModBlocks.LARGE_FERVIS_BUD, ModBlocks.FERVIS_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.ALLURE_BUDS)) {
                changeBud(!allureCheck(world), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_ALLURE_BUD, ModBlocks.MEDIUM_ALLURE_BUD, ModBlocks.LARGE_ALLURE_BUD, ModBlocks.ALLURE_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.GALVA_BUDS)) {
                changeBud(!galvaCheck(world), randomCheck,world, blockState, blockPos, direction, ModBlocks.SMALL_GALVA_BUD, ModBlocks.MEDIUM_GALVA_BUD, ModBlocks.LARGE_GALVA_BUD, ModBlocks.GALVA_CLUSTER);
                return;
            }
            return;
        }
        List<Integer> checklist = possibleGrowths(world, blockState, blockPos);
        if(checklist.size() == 0) return;
        int index = (int) (random.nextDouble() * checklist.size());
        switch (checklist.get(index)) {
            case 0 -> {
                BlockState blockState2 = ModBlocks.SMALL_UMBER_BUD.getDefaultState().with(CrystalClusterBlock.CAN_GROW, true).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
            case 1 -> {
                BlockState blockState2 = ModBlocks.SMALL_AERIS_BUD.getDefaultState().with(CrystalClusterBlock.CAN_GROW, true).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
            case 2 -> {
                BlockState blockState2 = ModBlocks.SMALL_FERVIS_BUD.getDefaultState().with(CrystalClusterBlock.CAN_GROW, true).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
            case 3 -> {
                BlockState blockState2 = ModBlocks.SMALL_ALLURE_BUD.getDefaultState().with(CrystalClusterBlock.CAN_GROW, true).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
            case 4 -> {
                BlockState blockState2 = ModBlocks.SMALL_GALVA_BUD.getDefaultState().with(CrystalClusterBlock.CAN_GROW, true).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }
            default -> Leapers.LOGGER.info("no growth case for "+ index);
        }
    }

    List<Integer> possibleGrowths(ServerWorld world, BlockState blockState, BlockPos blockPos) {
        List<Integer> checklist = new ArrayList<>();
        if(blockState.isAir()) {
            if(umberCheck(world, blockPos)) checklist.add(0);
            if(fervisCheck(world)) checklist.add(2);
            if(allureCheck(world)) checklist.add(3);
            if(galvaCheck(world)) checklist.add(4);
        }
        if(blockState.isOf(Blocks.WATER) && blockState.getFluidState().getLevel() == 8) if(aerisCheck(blockState)) checklist.add(1);
        return checklist;
    }

    void changeBud(boolean canGrow, boolean recede, ServerWorld world, BlockState blockState, BlockPos blockPos, Direction direction, Block smallBud, Block mediumBud, Block largeBud, Block cluster) {
        if (canGrow) growWithSet(world, blockState, blockPos, direction, smallBud, mediumBud, largeBud, cluster);
        else if(recede) recedeWithSet(world, blockState, blockPos, direction, smallBud, mediumBud, largeBud);
    }

    void growWithSet(ServerWorld world, BlockState blockState, BlockPos blockPos, Direction direction, Block smallBud, Block mediumBud, Block largeBud, Block cluster) {
        Block block = null;
        if(blockState.isOf(smallBud)) block = mediumBud;
        else if (blockState.isOf(mediumBud)) block = largeBud;
        else if (blockState.isOf(largeBud)) block = cluster;
        if(block != null) {
            BlockState blockState2 = block.getDefaultState().with(CrystalClusterBlock.CAN_GROW, blockState.get(CrystalClusterBlock.CAN_GROW)).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
            world.setBlockState(blockPos, blockState2);
        }
    }

    void recedeWithSet(ServerWorld world, BlockState blockState, BlockPos blockPos, Direction direction, Block smallBud, Block mediumBud, Block largeBud) {
        Block block = null;
        if(blockState.isOf(smallBud)) block = Blocks.AIR;
        else if (blockState.isOf(mediumBud)) block = smallBud;
        else if (blockState.isOf(largeBud)) block = mediumBud;
        if(block != null) {
            BlockState blockState2 = block.getDefaultState().with(CrystalClusterBlock.CAN_GROW, blockState.get(CrystalClusterBlock.CAN_GROW)).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
            world.setBlockState(blockPos, blockState2);
        }
    }

    void growWithProperty(ServerWorld world, BlockState blockState, BlockPos blockPos, Property<Integer> property) {
        world.setBlockState(blockPos, blockState.with(property, blockState.get(property)+1));
    }

    boolean galvaCheck(ServerWorld world) {
        return world.getTimeOfDay() % 24000 <= 12000 && world.getDimensionKey() == DimensionTypes.OVERWORLD;
    }

    boolean allureCheck(ServerWorld world) {
        return world.getDimensionKey() == DimensionTypes.THE_NETHER;
    }

    boolean fervisCheck(ServerWorld world) {
        return world.getTimeOfDay() % 24000 > 12000 && world.getDimensionKey() == DimensionTypes.OVERWORLD;
    }

    boolean aerisCheck(BlockState state) {
        boolean pass = state.getFluidState().getLevel() == 8;
        return pass;
    }

    boolean umberCheck(ServerWorld world, BlockPos pos) {
        if(pos.getY() > 32) return false;
        int bottomLimit = world.getBottomY();
        int x = pos.getX();
        int z = pos.getZ();
        boolean mayEarn = true;
        BlockPos copyPos;
        for(var i = pos.getY()-1; i >= bottomLimit && mayEarn; i--) {
            copyPos = new BlockPos(x, i, z);
            if(!world.getBlockState(copyPos).isAir()) mayEarn = false;
        }
        return mayEarn;
    }


    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
}
