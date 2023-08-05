package net.ramgames.leapers.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;
import net.ramgames.leapers.LeaperVariables;
import net.ramgames.leapers.block.entity.LeapPadBlockEntity;
import net.ramgames.leapers.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class LeapPadBlock extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty Memoria = BooleanProperty.of("memoria");
    public LeapPadBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(2, 4, 2, 14, 5, 14), Block.createCuboidShape(0, 0, 0, 16, 4, 16), BooleanBiFunction.OR);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if(ctx.getStack().hasNbt()) {
            if(ctx.getStack().getNbt().getUuid("crystalUUID") != null) {
                return this.getDefaultState().with(Memoria, true);
            }
        }

        return this.getDefaultState().with(Memoria, false);
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient()) {
            if (player.getStackInHand(hand).getCount() == 1 && player.getStackInHand(hand).getItem() == ModItems.MEMORIA_CRYSTAL && !state.get(Memoria)) {
                player.setStackInHand(hand, ItemStack.EMPTY);
                LeaperVariables.KnownLeapPads.add(pos);
                UUID uuid = UUID.randomUUID();
                LeaperVariables.UuidLeapPads.put(pos,uuid);
                ((LeapPadBlockEntity) world.getBlockEntity(pos)).setCrystalID(uuid);
                world.setBlockState(pos,state.with(Memoria,true));
                return ActionResult.SUCCESS;
            }
            if (player.getStackInHand(hand) == ItemStack.EMPTY && state.get(Memoria)) {
                ((LeapPadBlockEntity) world.getBlockEntity(pos)).setCrystalID(null);
                LeaperVariables.KnownLeapPads.remove(pos);
                LeaperVariables.UuidLeapPads.remove(pos);
                player.setStackInHand(hand, new ItemStack(ModItems.MEMORIA_CRYSTAL));
                world.setBlockState(pos,state.with(Memoria,false));
                return ActionResult.SUCCESS;
            }
            /*if (player.getStackInHand(hand).isIn(RegisterTags.MEMORIA_TYPE) && state.get(Memoria)) {
                world.getPlayers().get(0).sendMessage(Text.of("LEAPER"));
                ItemStack itemStack = player.getStackInHand(hand);
                NbtCompound nbt = itemStack.getNbt();
                if(nbt == null) {
                    nbt = new NbtCompound();
                }
                world.getPlayers().get(0).sendMessage(Text.of(((LeapPadBlockEntity) world.getBlockEntity(pos)).getCrystalID().toString()));
                nbt.putUuid("memoriaID",((LeapPadBlockEntity) world.getBlockEntity(pos)).getCrystalID());
                itemStack.setNbt(nbt);
                player.setStackInHand(hand, itemStack);
                return ActionResult.SUCCESS;
            }*/

        }
        return ActionResult.SUCCESS;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Memoria,FACING);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LeapPadBlockEntity(pos,state);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        onDestroy((World) world,pos);
        super.onBroken(world, pos, state);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        onDestroy(world,pos);
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        LeaperVariables.KnownLeapPads.remove(pos);
        super.onDestroyedByExplosion(world, pos, explosion);
    }

    private void onDestroy(World world, BlockPos pos) {
        if(LeaperVariables.KnownLeapPads.contains(pos)) {
            world.spawnEntity(new ItemEntity(world, pos.getX(),pos.getY(),pos.getZ(),new ItemStack(ModItems.MEMORIA_CRYSTAL)));
        }
        LeaperVariables.KnownLeapPads.remove(pos);
        LeaperVariables.UuidLeapPads.remove(pos);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BlockEntityProvider.super.getTicker(world, state, type);
    }

}
