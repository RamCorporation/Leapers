package net.ramgames.leapers.blocks.custom;

import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.dimension.DimensionTypes;
import net.ramgames.leapers.Leapers;
import net.ramgames.leapers.ModTags;
import net.ramgames.leapers.blocks.ModBlocks;

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
            if(blockState.isOf(ModBlocks.SANDY_CLUSTER)) {
                changeWithProperty(world, blockState, blockPos, SandyClusterBlock.GROWTH);
                return;
            }
            if(blockState.isOf(ModBlocks.CRYSTALLIZED_SCULK)) {
                changeWithProperty(world, blockState, blockPos, CrystallizedSculkBlock.GROWTH);
                return;
            }
            if(!blockState.get(CrystalClusterBlock.CAN_GROW)) return;
            boolean randomCheck = random.nextInt(3) == 0;
            if(blockState.isIn(ModTags.UMBER_BUDS)) {
                if(direction == Direction.UP) return;
                changeWithSet(umberCheck(world, blockPos), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_UMBER_BUD, ModBlocks.MEDIUM_UMBER_BUD, ModBlocks.LARGE_UMBER_BUD, ModBlocks.UMBER_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.AERIS_BUDS)) {
                changeWithSet(aerisCheck(blockState), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_AERIS_BUD, ModBlocks.MEDIUM_AERIS_BUD, ModBlocks.LARGE_AERIS_BUD, ModBlocks.AERIS_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.FERVIS_BUDS)) {
                if(direction == Direction.DOWN) return;
                changeWithSet(fervisCheck(world, blockPos), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_FERVIS_BUD, ModBlocks.MEDIUM_FERVIS_BUD, ModBlocks.LARGE_FERVIS_BUD, ModBlocks.FERVIS_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.ALLURE_BUDS)) {
                changeWithSet(allureCheck(world), randomCheck, world, blockState, blockPos, direction, ModBlocks.SMALL_ALLURE_BUD, ModBlocks.MEDIUM_ALLURE_BUD, ModBlocks.LARGE_ALLURE_BUD, ModBlocks.ALLURE_CLUSTER);
                return;
            }
            if(blockState.isIn(ModTags.GALVA_BUDS)) {
                if(direction == Direction.DOWN) return;
                changeWithSet(galvaCheck(world, blockPos), randomCheck,world, blockState, blockPos, direction, ModBlocks.SMALL_GALVA_BUD, ModBlocks.MEDIUM_GALVA_BUD, ModBlocks.LARGE_GALVA_BUD, ModBlocks.GALVA_CLUSTER);
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
            case 5 -> {
                BlockState blockState2 = ModBlocks.SANDY_CLUSTER.getDefaultState().with(SandyClusterBlock.FACING, direction).with(SandyClusterBlock.GROWTH, 1);
                world.setBlockState(blockPos, blockState2);
                world.scheduleBlockTick(blockPos, ModBlocks.SANDY_CLUSTER, 2);
            }
            case 6 -> {
                BlockState blockState2 = ModBlocks.CRYSTALLIZED_SCULK.getDefaultState().with(CrystallizedSculkBlock.FACING, direction).with(CrystallizedSculkBlock.GROWTH, 1);
                world.setBlockState(blockPos, blockState2);
            }
            default -> Leapers.LOGGER.error("no growth case for "+ index);
        }
    }

    List<Integer> possibleGrowths(ServerWorld world, BlockState blockState, BlockPos blockPos) {
        List<Integer> checklist = new ArrayList<>();
        if(blockState.isAir()) {
            if(umberCheck(world, blockPos)) checklist.add(0);
            if(fervisCheck(world, blockPos)) checklist.add(2);
            if(allureCheck(world)) checklist.add(3);
            if(galvaCheck(world, blockPos)) checklist.add(4);
        }
        if(blockState.isOf(Blocks.SAND)) checklist.add(5);
        if(blockState.isOf(Blocks.SCULK)) checklist.add(6);
        if(blockState.isOf(Blocks.WATER) && blockState.getFluidState().getLevel() == 8) if(aerisCheck(blockState)) checklist.add(1);
        return checklist;
    }

    void changeWithSet(boolean conditionCheck, boolean recede, ServerWorld world, BlockState blockState, BlockPos blockPos, Direction direction, Block smallBud, Block mediumBud, Block largeBud, Block cluster) {
        if (conditionCheck) growWithSet(world, blockState, blockPos, direction, smallBud, mediumBud, largeBud, cluster);
        else if(recede) recedeWithSet(world, blockState, blockPos, direction, smallBud, mediumBud, largeBud);
    }

    void changeWithProperty(ServerWorld world, BlockState blockState, BlockPos blockPos, Property<Integer> property) {
        if(blockState.get(property) == 4 ) return;
        world.setBlockState(blockPos, blockState.with(property, blockState.get(property)+1));
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
            BlockState blockState2;
            if(block == Blocks.AIR) blockState2 = Blocks.AIR.getDefaultState();
            else blockState2 = block.getDefaultState().with(CrystalClusterBlock.CAN_GROW, blockState.get(CrystalClusterBlock.CAN_GROW)).with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
            world.setBlockState(blockPos, blockState2);
        }
    }

    boolean galvaCheck(ServerWorld world, BlockPos pos) {
        if(!(world.getDimensionKey() == DimensionTypes.OVERWORLD)) return false;
        if(!(world.getTimeOfDay() % 24000 <= 12000)) return false;
        if(pos.getY() < 128) return false;
        int upperLimit = world.getTopY();
        int x = pos.getX();
        int z = pos.getZ();
        boolean mayEarn = true;
        BlockPos copyPos;
        for(var i = pos.getY()+1; i >= upperLimit && mayEarn; i++) {
            copyPos = new BlockPos(x, i, z);
            if(!world.getBlockState(copyPos).isAir()) mayEarn = false;
        }
        return mayEarn;
    }

    boolean allureCheck(ServerWorld world) {
        return world.getDimensionKey() == DimensionTypes.THE_NETHER;
    }

    boolean fervisCheck(ServerWorld world, BlockPos pos) {
        if(!(world.getDimensionKey() == DimensionTypes.OVERWORLD)) return false;
        if(!(world.getTimeOfDay() % 24000 > 12000)) return false;
        if(pos.getY() < 128) return false;
        int upperLimit = world.getTopY();
        int x = pos.getX();
        int z = pos.getZ();
        boolean mayEarn = true;
        BlockPos copyPos;
        for(var i = pos.getY()+1; i >= upperLimit && mayEarn; i++) {
            copyPos = new BlockPos(x, i, z);
            if(!world.getBlockState(copyPos).isAir()) mayEarn = false;
        }
        return mayEarn;
    }

    boolean aerisCheck(BlockState state) {
        return state.getFluidState().getLevel() == 8;
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
