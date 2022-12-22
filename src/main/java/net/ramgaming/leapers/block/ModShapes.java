package net.ramgaming.leapers.block;

import net.minecraft.block.Block;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.stream.Stream;

public class ModShapes {

    /** REDSTONE RAY **/

    public static final VoxelShape REDSTONE_RAY_NORTH = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(5, 4, 0, 11, 10, 4),
            Block.createCuboidShape(4, 3, 2, 12, 11, 4),
            Block.createCuboidShape(2, 2, 4, 4, 11, 6),
            Block.createCuboidShape(12, 2, 4, 14, 11, 6),
            Block.createCuboidShape(12, 2, 4, 14, 11, 6),
            Block.createCuboidShape(2, 2, 12, 4, 11, 14),
            Block.createCuboidShape(12, 2, 12, 14, 11, 14),
            Block.createCuboidShape(4, 8, 12, 12, 10, 14),
            Block.createCuboidShape(4, 8, 4, 12, 10, 6),
            Block.createCuboidShape(2, 8, 6, 4, 10, 12),
            Block.createCuboidShape(12, 8, 6, 14, 10, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape REDSTONE_RAY_EAST = Stream.of(
            Block.createCuboidShape(4, 8, 12, 10, 10, 14),
            Block.createCuboidShape(8, 2, 7, 10, 8, 9),
            Block.createCuboidShape(12, 4, 5, 16, 10, 11),
            Block.createCuboidShape(12, 3, 4, 14, 11, 12),
            Block.createCuboidShape(10, 2, 2, 12, 11, 4),
            Block.createCuboidShape(10, 2, 12, 12, 11, 14),
            Block.createCuboidShape(10, 2, 12, 12, 11, 14),
            Block.createCuboidShape(2, 2, 2, 4, 11, 4),
            Block.createCuboidShape(2, 2, 12, 4, 11, 14),
            Block.createCuboidShape(2, 8, 4, 4, 10, 12),
            Block.createCuboidShape(10, 8, 4, 12, 10, 12),
            Block.createCuboidShape(4, 8, 2, 10, 10, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape REDSTONE_RAY_SOUTH = Stream.of(
            Block.createCuboidShape(2, 8, 4, 4, 10, 10),
            Block.createCuboidShape(5, 4, 12, 11, 10, 16),
            Block.createCuboidShape(4, 3, 12, 12, 11, 14),
            Block.createCuboidShape(12, 2, 10, 14, 11, 12),
            Block.createCuboidShape(2, 2, 10, 4, 11, 12),
            Block.createCuboidShape(2, 2, 10, 4, 11, 12),
            Block.createCuboidShape(12, 2, 2, 14, 11, 4),
            Block.createCuboidShape(2, 2, 2, 4, 11, 4),
            Block.createCuboidShape(4, 8, 2, 12, 10, 4),
            Block.createCuboidShape(4, 8, 10, 12, 10, 12),
            Block.createCuboidShape(12, 8, 4, 14, 10, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape REDSTONE_RAY_WEST = Stream.of(
            Block.createCuboidShape(6, 8, 2, 12, 10, 4),
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 4, 5, 4, 10, 11),
            Block.createCuboidShape(2, 3, 4, 4, 11, 12),
            Block.createCuboidShape(4, 2, 12, 6, 11, 14),
            Block.createCuboidShape(4, 2, 2, 6, 11, 4),
            Block.createCuboidShape(4, 2, 2, 6, 11, 4),
            Block.createCuboidShape(12, 2, 12, 14, 11, 14),
            Block.createCuboidShape(12, 2, 2, 14, 11, 4),
            Block.createCuboidShape(12, 8, 4, 14, 10, 12),
            Block.createCuboidShape(4, 8, 4, 6, 10, 12),
            Block.createCuboidShape(6, 8, 12, 12, 10, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    /** MIRROR STANDING **/

    public static final VoxelShape MIRROR_NORTH = Stream.of(
            Block.createCuboidShape(4, 12, 12, 12, 14, 14),
            Block.createCuboidShape(12, 0, 12, 14, 14, 14),
            Block.createCuboidShape(12, 12, 4, 14, 14, 12),
            Block.createCuboidShape(12, 0, 4, 14, 2, 12),
            Block.createCuboidShape(2, 0, 13, 4, 16, 15),
            Block.createCuboidShape(3, 0, 12, 5, 16, 14),
            Block.createCuboidShape(4, 0, 11, 6, 16, 13),
            Block.createCuboidShape(5, 0, 10, 7, 16, 12),
            Block.createCuboidShape(7, 0, 8, 9, 16, 10),
            Block.createCuboidShape(6, 0, 9, 8, 16, 11),
            Block.createCuboidShape(8, 0, 7, 10, 16, 9),
            Block.createCuboidShape(9, 0, 6, 11, 16, 8),
            Block.createCuboidShape(11, 0, 4, 13, 16, 6),
            Block.createCuboidShape(10, 0, 5, 12, 16, 7),
            Block.createCuboidShape(12, 0, 3, 14, 16, 5),
            Block.createCuboidShape(13, 0, 2, 15, 16, 4),
            Block.createCuboidShape(4, 0, 12, 12, 2, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_EAST = Stream.of(
            Block.createCuboidShape(2, 12, 4, 4, 14, 12),
            Block.createCuboidShape(2, 0, 12, 4, 14, 14),
            Block.createCuboidShape(4, 12, 12, 12, 14, 14),
            Block.createCuboidShape(4, 0, 12, 12, 2, 14),
            Block.createCuboidShape(1, 0, 2, 3, 16, 4),
            Block.createCuboidShape(2, 0, 3, 4, 16, 5),
            Block.createCuboidShape(3, 0, 4, 5, 16, 6),
            Block.createCuboidShape(4, 0, 5, 6, 16, 7),
            Block.createCuboidShape(6, 0, 7, 8, 16, 9),
            Block.createCuboidShape(5, 0, 6, 7, 16, 8),
            Block.createCuboidShape(7, 0, 8, 9, 16, 10),
            Block.createCuboidShape(8, 0, 9, 10, 16, 11),
            Block.createCuboidShape(10, 0, 11, 12, 16, 13),
            Block.createCuboidShape(9, 0, 10, 11, 16, 12),
            Block.createCuboidShape(11, 0, 12, 13, 16, 14),
            Block.createCuboidShape(12, 0, 13, 14, 16, 15),
            Block.createCuboidShape(2, 0, 4, 4, 2, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_SOUTH = Stream.of(
            Block.createCuboidShape(4, 12, 2, 12, 14, 4),
            Block.createCuboidShape(2, 0, 2, 4, 14, 4),
            Block.createCuboidShape(2, 12, 4, 4, 14, 12),
            Block.createCuboidShape(2, 0, 4, 4, 2, 12),
            Block.createCuboidShape(12, 0, 1, 14, 16, 3),
            Block.createCuboidShape(11, 0, 2, 13, 16, 4),
            Block.createCuboidShape(10, 0, 3, 12, 16, 5),
            Block.createCuboidShape(9, 0, 4, 11, 16, 6),
            Block.createCuboidShape(7, 0, 6, 9, 16, 8),
            Block.createCuboidShape(8, 0, 5, 10, 16, 7),
            Block.createCuboidShape(6, 0, 7, 8, 16, 9),
            Block.createCuboidShape(5, 0, 8, 7, 16, 10),
            Block.createCuboidShape(3, 0, 10, 5, 16, 12),
            Block.createCuboidShape(4, 0, 9, 6, 16, 11),
            Block.createCuboidShape(2, 0, 11, 4, 16, 13),
            Block.createCuboidShape(1, 0, 12, 3, 16, 14),
            Block.createCuboidShape(4, 0, 2, 12, 2, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_WEST = Stream.of(
            Block.createCuboidShape(12, 12, 4, 14, 14, 12),
            Block.createCuboidShape(12, 0, 2, 14, 14, 4),
            Block.createCuboidShape(4, 12, 2, 12, 14, 4),
            Block.createCuboidShape(4, 0, 2, 12, 2, 4),
            Block.createCuboidShape(13, 0, 12, 15, 16, 14),
            Block.createCuboidShape(12, 0, 11, 14, 16, 13),
            Block.createCuboidShape(11, 0, 10, 13, 16, 12),
            Block.createCuboidShape(10, 0, 9, 12, 16, 11),
            Block.createCuboidShape(8, 0, 7, 10, 16, 9),
            Block.createCuboidShape(9, 0, 8, 11, 16, 10),
            Block.createCuboidShape(7, 0, 6, 9, 16, 8),
            Block.createCuboidShape(6, 0, 5, 8, 16, 7),
            Block.createCuboidShape(4, 0, 3, 6, 16, 5),
            Block.createCuboidShape(5, 0, 4, 7, 16, 6),
            Block.createCuboidShape(3, 0, 2, 5, 16, 4),
            Block.createCuboidShape(2, 0, 1, 4, 16, 3),
            Block.createCuboidShape(12, 0, 4, 14, 2, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    /** MIRROR UP **/

    public static final VoxelShape MIRROR_UP_NORTH = Stream.of(
            Block.createCuboidShape(2, 0, 12, 14, 2, 14),
            Block.createCuboidShape(2, 2, 12, 4, 11.25, 14),
            Block.createCuboidShape(12, 2, 12, 14, 11.25, 14),
            Block.createCuboidShape(0, 6, 8, 16, 8, 10),
            Block.createCuboidShape(0, 7, 9, 16, 9, 11),
            Block.createCuboidShape(0, 10, 12, 16, 12, 14),
            Block.createCuboidShape(0, 9, 11, 16, 11, 13),
            Block.createCuboidShape(0, 8, 10, 16, 10, 12),
            Block.createCuboidShape(0, 3, 5, 16, 5, 7),
            Block.createCuboidShape(0, 4, 6, 16, 6, 8),
            Block.createCuboidShape(0, 5, 7, 16, 7, 9),
            Block.createCuboidShape(0, 2, 4, 16, 4, 6),
            Block.createCuboidShape(0, 1, 3, 16, 3, 5),
            Block.createCuboidShape(0, 0, 2, 16, 2, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_UP_EAST = Stream.of(
            Block.createCuboidShape(2, 0, 2, 4, 2, 14),
            Block.createCuboidShape(2, 2, 2, 4, 11.25, 4),
            Block.createCuboidShape(2, 2, 12, 4, 11.25, 14),
            Block.createCuboidShape(6, 6, 0, 8, 8, 16),
            Block.createCuboidShape(5, 7, 0, 7, 9, 16),
            Block.createCuboidShape(2, 10, 0, 4, 12, 16),
            Block.createCuboidShape(3, 9, 0, 5, 11, 16),
            Block.createCuboidShape(4, 8, 0, 6, 10, 16),
            Block.createCuboidShape(9, 3, 0, 11, 5, 16),
            Block.createCuboidShape(8, 4, 0, 10, 6, 16),
            Block.createCuboidShape(7, 5, 0, 9, 7, 16),
            Block.createCuboidShape(10, 2, 0, 12, 4, 16),
            Block.createCuboidShape(11, 1, 0, 13, 3, 16),
            Block.createCuboidShape(12, 0, 0, 14, 2, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_UP_SOUTH = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 2, 4),
            Block.createCuboidShape(2, 2, 2, 4, 11.25, 4),
            Block.createCuboidShape(12, 2, 2, 14, 11.25, 4),
            Block.createCuboidShape(0, 0, 12, 16, 2, 14),
            Block.createCuboidShape(0, 1, 11, 16, 3, 13),
            Block.createCuboidShape(0, 2, 10, 16, 4, 12),
            Block.createCuboidShape(0, 5, 7, 16, 7, 9),
            Block.createCuboidShape(0, 4, 8, 16, 6, 10),
            Block.createCuboidShape(0, 3, 9, 16, 5, 11),
            Block.createCuboidShape(0, 8, 4, 16, 10, 6),
            Block.createCuboidShape(0, 9, 3, 16, 11, 5),
            Block.createCuboidShape(0, 10, 2, 16, 12, 4),
            Block.createCuboidShape(0, 7, 5, 16, 9, 7),
            Block.createCuboidShape(0, 6, 6, 16, 8, 8)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_UP_WEST = Stream.of(
            Block.createCuboidShape(12, 0, 2, 14, 2, 14),
            Block.createCuboidShape(12, 2, 12, 14, 11.25, 14),
            Block.createCuboidShape(12, 2, 2, 14, 11.25, 4),
            Block.createCuboidShape(8, 6, 0, 10, 8, 16),
            Block.createCuboidShape(9, 7, 0, 11, 9, 16),
            Block.createCuboidShape(12, 10, 0, 14, 12, 16),
            Block.createCuboidShape(11, 9, 0, 13, 11, 16),
            Block.createCuboidShape(10, 8, 0, 12, 10, 16),
            Block.createCuboidShape(5, 3, 0, 7, 5, 16),
            Block.createCuboidShape(6, 4, 0, 8, 6, 16),
            Block.createCuboidShape(7, 5, 0, 9, 7, 16),
            Block.createCuboidShape(4, 2, 0, 6, 4, 16),
            Block.createCuboidShape(3, 1, 0, 5, 3, 16),
            Block.createCuboidShape(2, 0, 0, 4, 2, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    /** MIRROR DOWN **/

    public static final VoxelShape MIRROR_DOWN_NORTH = Stream.of(
            Block.createCuboidShape(2, 10, 14, 14, 12, 16),
            Block.createCuboidShape(12, 0, 14, 14, 10, 16),
            Block.createCuboidShape(2, 0, 14, 4, 10, 16),
            Block.createCuboidShape(12, 10, 3, 14, 12, 14),
            Block.createCuboidShape(2, 10, 3, 4, 12, 14),
            Block.createCuboidShape(12, 3, 10, 14, 5, 14),
            Block.createCuboidShape(2, 3, 10, 4, 5, 14),
            Block.createCuboidShape(0, 0.5, 12, 16, 2, 13.5),
            Block.createCuboidShape(0, 1, 11, 16, 3, 13),
            Block.createCuboidShape(0, 2, 10, 16, 4, 12),
            Block.createCuboidShape(0, 3, 9, 16, 5, 11),
            Block.createCuboidShape(0, 5, 7, 16, 7, 9),
            Block.createCuboidShape(0, 4, 8, 16, 6, 10),
            Block.createCuboidShape(0, 7, 5, 16, 9, 7),
            Block.createCuboidShape(0, 6, 6, 16, 8, 8),
            Block.createCuboidShape(0, 9, 3, 16, 11, 5),
            Block.createCuboidShape(0, 8, 4, 16, 10, 6),
            Block.createCuboidShape(0, 11, 1.5, 16, 12.5, 3),
            Block.createCuboidShape(0, 10, 2, 16, 12, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_DOWN_WEST = Stream.of(
            Block.createCuboidShape(14, 10, 2, 16, 12, 14),
            Block.createCuboidShape(14, 0, 2, 16, 10, 4),
            Block.createCuboidShape(14, 0, 12, 16, 10, 14),
            Block.createCuboidShape(3, 10, 2, 14, 12, 4),
            Block.createCuboidShape(3, 10, 12, 14, 12, 14),
            Block.createCuboidShape(10, 3, 2, 14, 5, 4),
            Block.createCuboidShape(10, 3, 12, 14, 5, 14),
            Block.createCuboidShape(12, 0.5, 0, 13.5, 2, 16),
            Block.createCuboidShape(11, 1, 0, 13, 3, 16),
            Block.createCuboidShape(10, 2, 0, 12, 4, 16),
            Block.createCuboidShape(9, 3, 0, 11, 5, 16),
            Block.createCuboidShape(7, 5, 0, 9, 7, 16),
            Block.createCuboidShape(8, 4, 0, 10, 6, 16),
            Block.createCuboidShape(5, 7, 0, 7, 9, 16),
            Block.createCuboidShape(6, 6, 0, 8, 8, 16),
            Block.createCuboidShape(3, 9, 0, 5, 11, 16),
            Block.createCuboidShape(4, 8, 0, 6, 10, 16),
            Block.createCuboidShape(1.5, 11, 0, 3, 12.5, 16),
            Block.createCuboidShape(2, 10, 0, 4, 12, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_DOWN_SOUTH = Stream.of(
            Block.createCuboidShape(2, 10, 0, 14, 12, 2),
            Block.createCuboidShape(2, 0, 0, 4, 10, 2),
            Block.createCuboidShape(12, 0, 0, 14, 10, 2),
            Block.createCuboidShape(2, 10, 2, 4, 12, 13),
            Block.createCuboidShape(12, 10, 2, 14, 12, 13),
            Block.createCuboidShape(2, 3, 2, 4, 5, 6),
            Block.createCuboidShape(12, 3, 2, 14, 5, 6),
            Block.createCuboidShape(0, 6, 8, 16, 8, 10),
            Block.createCuboidShape(0, 7, 9, 16, 9, 11),
            Block.createCuboidShape(0, 10, 12, 16, 12, 14),
            Block.createCuboidShape(0, 9, 11, 16, 11, 13),
            Block.createCuboidShape(0, 8, 10, 16, 10, 12),
            Block.createCuboidShape(0, 3, 5, 16, 5, 7),
            Block.createCuboidShape(0, 4, 6, 16, 6, 8),
            Block.createCuboidShape(0, 5, 7, 16, 7, 9),
            Block.createCuboidShape(0, 2, 4, 16, 4, 6),
            Block.createCuboidShape(0, 1, 3, 16, 3, 5),
            Block.createCuboidShape(0, 0, 2, 16, 2, 4)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static final VoxelShape MIRROR_DOWN_EAST = Stream.of(
            Block.createCuboidShape(0, 10, 2, 2, 12, 14),
            Block.createCuboidShape(0, 0, 12, 2, 10, 14),
            Block.createCuboidShape(0, 0, 2, 2, 10, 4),
            Block.createCuboidShape(2, 10, 12, 13, 12, 14),
            Block.createCuboidShape(2, 10, 2, 13, 12, 4),
            Block.createCuboidShape(2, 3, 12, 6, 5, 14),
            Block.createCuboidShape(2, 3, 2, 6, 5, 4),
            Block.createCuboidShape(2.5, 0.5, 0, 4, 2, 16),
            Block.createCuboidShape(3, 1, 0, 5, 3, 16),
            Block.createCuboidShape(4, 2, 0, 6, 4, 16),
            Block.createCuboidShape(5, 3, 0, 7, 5, 16),
            Block.createCuboidShape(7, 5, 0, 9, 7, 16),
            Block.createCuboidShape(6, 4, 0, 8, 6, 16),
            Block.createCuboidShape(9, 7, 0, 11, 9, 16),
            Block.createCuboidShape(8, 6, 0, 10, 8, 16),
            Block.createCuboidShape(11, 9, 0, 13, 11, 16),
            Block.createCuboidShape(10, 8, 0, 12, 10, 16),
            Block.createCuboidShape(13, 11, 0, 14.5, 12.5, 16),
            Block.createCuboidShape(12, 10, 0, 14, 12, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
}
