package net.ramgames.leapers.beams;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class AllureBeam extends AbstractBeam{

    private int power;

    public AllureBeam(BlockPos pos, Direction direction, int power) {
        super(pos, direction);
        this.power = power;
    }


    public int getPower() {
        return this.power;
    }

    @Override
    protected void outOfBounds(World world, BlockState state) {

    }



    @Override
    protected void update(World world, BlockState state, BlockState nextState) {
        if(world.getLightLevel(this.getPos()) < power) {
            power = world.getLightLevel(this.getPos());
        }
    }

    @Override
    protected void preUpdate(World world, BlockState state, BlockState nextState) {

    }

    @Override
    protected void postUpdate(World world, BlockState state, BlockState nextState) {

    }

    @Override
    protected void onStop(World world, BlockState state, BlockState nextState) {

    }


}
