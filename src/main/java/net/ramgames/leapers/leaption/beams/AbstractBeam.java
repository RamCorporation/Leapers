package net.ramgames.leapers.leaption.beams;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.ramgames.leapers.blocks.ModBlocks;
import net.ramgames.leapers.blocks.custom.MirrorStances;

public abstract class AbstractBeam {
    private final BlockPos pos;
    private Direction direction;
    private boolean stillMoving;

    public AbstractBeam(BlockPos pos, Direction direction) {
        this.pos = pos;
        this.direction = direction;
        this.stillMoving = true;
    }
    protected abstract void outOfBounds(World world, BlockState state);
    protected abstract void update(World world, BlockState state, BlockState nextState);
    protected abstract void preUpdate(World world, BlockState state, BlockState nextState);
    protected abstract void postUpdate(World world, BlockState state, BlockState nextState);
    protected abstract void onStop(World world, BlockState state, BlockState nextState);
    private BlockPos ghostMove(Direction direction) {
        BlockPos ghostPos = this.pos;
        switch(direction) {
            case NORTH -> ghostPos.north();
            case EAST -> ghostPos.east();
            case SOUTH -> ghostPos.south();
            case WEST -> ghostPos.west();
        }
        return ghostPos;
    }
    private BlockPos ghostMove(BlockPos pos, Direction direction) {
        switch(direction) {
            case NORTH -> pos.north();
            case EAST -> pos.east();
            case SOUTH -> pos.south();
            case WEST -> pos.west();
        }
        return pos;
    }
    private void move() {
        switch(direction) {
            case NORTH -> this.pos.north();
            case EAST -> this.pos.east();
            case SOUTH -> this.pos.south();
            case WEST -> this.pos.west();
        }
    }
    protected BlockState getNextBlock(World world) {
        return world.getBlockState(pos.offset(direction));
    }
    private Direction MirrorUpdate(BlockState state, Direction direction) {
        Direction sDirection = state.get(DirectionProperty.of("facing"));
        switch(state.get(EnumProperty.of("pointing", MirrorStances.class))) {
            case UP -> {
                if(sDirection == direction.getOpposite()) {
                    return Direction.UP;
                }
                if(direction == Direction.DOWN) {
                    return sDirection;
                }
                this.stillMoving = false;
            }
            case DOWN -> {
                if(sDirection == direction.getOpposite()) {
                    return Direction.DOWN;
                }
                if(direction == Direction.UP) {
                   return sDirection;
                }
                this.stillMoving = false;
            }
            default -> {
                if(sDirection == direction.getOpposite()) {
                    return direction.rotateCounterclockwise(Direction.Axis.Y);
                }
                if(sDirection == direction.rotateCounterclockwise(Direction.Axis.Y)) {
                    return sDirection;
                }
                this.stillMoving = false;

            }
        }
        return sDirection;
    }
    public boolean isStillMoving() {
        return this.stillMoving;
    }
    public BlockPos getPos() {
        return this.pos;
    }
    public void advance(World world) {
        BlockState state = world.getBlockState(pos);
        BlockState nextState = getNextBlock(world);
        preUpdate(world,state,nextState);
        move();
        state = world.getBlockState(pos);
        nextState = getNextBlock(world);
        BlockPos nextPos = pos.offset(direction);
        if(!world.isChunkLoaded(ChunkSectionPos.getSectionCoord(nextPos.getX()), ChunkSectionPos.getSectionCoord(nextPos.getZ()))) {
            this.stillMoving = false;
            outOfBounds(world,state);
            return;
        }
        if(nextState.getBlock() != ModBlocks.MIRROR && !nextState.isAir() && !nextState.getBlock().canMobSpawnInside(nextState)) {
            this.stillMoving = false;
            onStop(world,state,nextState);
            return;
        }
        if(state.getBlock() == ModBlocks.MIRROR) {
            this.direction = MirrorUpdate(state,this.direction);
        }
        update(world,state,nextState);
        if(nextState.getBlock() == ModBlocks.MIRROR) {
            if(!canMoveTo(ghostMove(ghostMove(this.direction),MirrorUpdate(nextState,this.direction)),world.getBlockState(ghostMove(ghostMove(this.direction),MirrorUpdate(nextState,this.direction))))) {
                this.stillMoving = false;
                onStop(world,state,nextState);
                return;
            }
        }
        postUpdate(world,state,nextState);
    }
    private boolean canMoveTo(BlockPos statePos, BlockState state) {
        if(state.getBlock() == ModBlocks.MIRROR) {

        }
        return state.getBlock() != ModBlocks.MIRROR && !state.isAir() && !state.getBlock().canMobSpawnInside(state);
    }
}
