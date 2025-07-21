package mcop.server;

import dev.hilligans.ourcraft.block.blockstate.IBlockState;
import dev.hilligans.ourcraft.world.newworldsystem.SimpleServerWorld;

public class MCOPServerWorld extends SimpleServerWorld {
    public MCOPServerWorld(int id, String name) {
        super(id, name);
    }

    @Override
    public void setBlockState(long x, long y, long z, IBlockState newState) {
        super.setBlockState(x, y, z, newState);

    }
}
