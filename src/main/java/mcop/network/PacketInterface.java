package mcop.network;

import dev.hilligans.ourcraft.block.blockstate.IBlockState;
import dev.hilligans.ourcraft.data.other.blockstates.BlockState;
import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.entity.IPlayerEntity;
import dev.hilligans.ourcraft.network.PacketBase;
import dev.hilligans.ourcraft.server.IServer;
import dev.hilligans.ourcraft.world.newworldsystem.IChunk;
import dev.hilligans.ourcraft.world.newworldsystem.IWorld;
import mcop.MCOPServerPlayerData;
import mcop.entities.PlayerEntity;

public interface PacketInterface {


    void sendKeepalivePacket(int id);

    void sendWorldInfo(IServer server, IWorld world, MCOPServerPlayerData data);

    void sendChunk(IChunk chunk, IPlayerEntity playerEntity);

    void sendPositionAndLook(IPlayerEntity playerEntity);

    PacketBase<?> getBlockChangePacket(int x, int y, int z, IWorld world, IBlockState blockState);
}
