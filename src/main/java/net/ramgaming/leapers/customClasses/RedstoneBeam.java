package net.ramgaming.leapers.customClasses;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.ramgaming.leapers.block.ModBlocks;

public class RedstoneBeam {
    private BlockPos pos;
    private Direction direction;
    private boolean stillMoving;
    public RedstoneBeam(BlockPos pos, Direction direction) {
        this.pos = pos;
        this.direction = direction;
        this.stillMoving = true;
    }
    public void advance(World world) {
        switch (direction) {
            case NORTH -> pos.add(0,0,-1);
            case EAST -> pos.add(1,0,0);
            case SOUTH -> pos.add(0,0,1);
            case WEST -> pos.add(-1,0,0);
            case UP -> pos.add(0,1,0);
            case DOWN -> pos.add(0,-1,0);
        }
        BlockState state = world.getBlockState(pos);
        if(state.getBlock() == ModBlocks.MIRROR) {
            switch(state.get(DirectionProperty.of("facing"))) {
                case NORTH -> {
                    if(direction == Direction.SOUTH) {
                        //direction = Direction.UP
                    }
                }
            }
        }
    }
}
