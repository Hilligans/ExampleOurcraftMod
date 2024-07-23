package mcop.network.packets.version5;

import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.entity.IPlayerEntity;
import dev.hilligans.ourcraft.network.ServerNetwork;
import dev.hilligans.ourcraft.network.ServerNetworkHandler;
import dev.hilligans.ourcraft.server.IServer;
import dev.hilligans.ourcraft.world.newworldsystem.IChunk;
import dev.hilligans.ourcraft.world.newworldsystem.IWorld;
import mcop.MCOP;
import mcop.MCOPServerPlayerData;
import mcop.blocks.Version5BlockTable;
import mcop.entities.PlayerEntity;
import mcop.network.PacketInterface;
import mcop.network.packets.version5.server.*;

public class Version5PacketInterface implements PacketInterface {

    public IServer server;
    public Version5BlockTable table;

    public Version5PacketInterface(IServer server) {
        this.server = server;
    }

    @Override
    public void sendKeepalivePacket(int id) {
        //System.err.println("start");
        server.getServerNetworkHandler().sendPacketInternal(new SKeepAlivePacket5(id));
       // System.err.println("done");
    }

    @Override
    public void sendWorldInfo(IServer server, IWorld world, MCOPServerPlayerData data) {
        server.sendPacket(new SJoinGamePacket5((int) data.entity.getID(), 1, (byte)0 ,0, 200, "default"), data);
        server.sendPacket(new SSpawnPositionPacket5(10, 10, 10), data);
        server.sendPacket(new SPlayerAbilitiesPacket5(true, true, true, true, 0.1f, 1), data);
       // server.sendPacket(new SPlayerPositionAndLookPacket5(10, 100, 10, 0, 0), data);

        //server.sendPacket(new SMapChunkBulkPacket5(), data);
    }


    @Override
    public void sendChunk(IChunk chunk, IPlayerEntity playerEntity) {
        if(chunk == null) {
            return;
        }
        if(chunk.getWidth() != 16) {
            throw new RuntimeException("Cannot send chunk width that isnt 16");
        }

        if(chunk.getHeight() != 256) {
            System.err.println(chunk.getHeight());
            throw new RuntimeException("Cannot send chunk height that isnt 256");
        }
        server.sendPacket(new SChunkDataPacket5(chunk, MCOP.table), ((PlayerEntity) playerEntity).data);
        for(int x = 0; x < chunk.getWidth(); x++) {
            for(int y =0; y < chunk.getHeight(); y++) {
                for(int z = 0; z < chunk.getWidth(); z++) {
                 //   server.sendPacket(new SBlockChangePacket5((int) (chunk.getBlockX() + x), (int) (chunk.getBlockY() + y), (int) (chunk.getBlockZ() + z), MCOP.table.getBlockID(chunk.getBlockState1(x, y, z)), 0));
                }
            }
        }
    }

    @Override
    public void sendPositionAndLook(IPlayerEntity playerEntity) {
        server.sendPacket(new SPlayerPositionAndLookPacket5(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), playerEntity.getYaw(), playerEntity.getPitch()), ((PlayerEntity) playerEntity).data);
    }
}
