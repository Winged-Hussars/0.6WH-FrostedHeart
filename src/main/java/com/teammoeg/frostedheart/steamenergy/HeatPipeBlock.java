package com.teammoeg.frostedheart.steamenergy;

import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.teammoeg.frostedheart.block.FHBaseBlock;
import com.teammoeg.frostedheart.content.FHTileTypes;

import blusunrize.immersiveengineering.common.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class HeatPipeBlock extends FHBaseBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public HeatPipeBlock(String name, Properties blockProps,
			BiFunction<Block, net.minecraft.item.Item.Properties, Item> createItemBlock) {
		super(name, blockProps, createItemBlock);
		this.lightOpacity = 0;
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.FALSE));
	}


    @Nullable
    @Override
    public TileEntity createTileEntity(@Nonnull BlockState state, @Nonnull IBlockReader world) {
        return FHTileTypes.HEATPIPE.get().create();
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(LIT);
    }


	/*@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
		TileEntity tec=Utils.getExistingTileEntity(worldIn,pos);
		for(Direction d:Direction.values()) {
			TileEntity te=Utils.getExistingTileEntity(worldIn,pos.offset(d));
			if(te instanceof HeatPipeTileEntity)
				((HeatPipeTileEntity) te).connectAt(d.getOpposite());
			else
				((HeatPipeTileEntity) tec).connectAt(d);
		}
	}*/


	/*@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		super.onReplaced(state, worldIn, pos, newState, isMoving);
		for(Direction d:Direction.values()) {
			TileEntity te=Utils.getExistingTileEntity(worldIn,pos.offset(d));
			if(te instanceof HeatPipeTileEntity)
				((HeatPipeTileEntity) te).disconnectAt(d.getOpposite());
		}
	}*/


    @Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		//System.out.println(pos);
		//System.out.println(fromPos);
    	TileEntity te=Utils.getExistingTileEntity(worldIn,pos);
		if(te instanceof HeatPipeTileEntity) {
			Vector3i vec=fromPos.subtract(pos);
			Direction dir=Direction.getFacingFromVector(vec.getX(),vec.getY(),vec.getZ());
			((HeatPipeTileEntity) te).connectAt(dir);
		}
	}



	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}