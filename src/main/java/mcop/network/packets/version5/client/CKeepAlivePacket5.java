package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.server.MCOPServer;

public class CKeepAlivePacket5 extends PacketBase<IServerPacketHandler> {

    //The server will frequently send out a keep-alive, each containing a random ID.
    //The client must respond with the same packet.

    int keepAliveID;

    public CKeepAlivePacket5() {}

    public CKeepAlivePacket5(int keepAliveID) {
        this.keepAliveID = keepAliveID;
    }

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(keepAliveID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        keepAliveID = packetData.readInt();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        iServerPacketHandler.getServerPlayerData().arbDataMap.put("timeoutTime", keepAliveID);
    }
}
