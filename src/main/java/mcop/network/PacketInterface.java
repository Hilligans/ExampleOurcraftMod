package mcop.network;

import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.entity.IPlayerEntity;
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
}
