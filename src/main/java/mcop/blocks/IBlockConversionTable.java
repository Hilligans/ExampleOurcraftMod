package mcop.blocks;

import dev.hilligans.ourcraft.block.blockstate.IBlockState;

public interface IBlockConversionTable {

    IBlockState getBlockState(int id);

    int getBlockID(IBlockState blockState);

}
