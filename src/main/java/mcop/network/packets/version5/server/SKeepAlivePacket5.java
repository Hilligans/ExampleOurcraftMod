package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SKeepAlivePacket5 extends PacketBase<IClientPacketHandler> {

    /*
     The server will frequently send out a keep-alive, each containing a random ID. The client must respond with the same packet.
     If the client does not respond to them for over 30 seconds, the server kicks the client.
     Vice versa, if the server does not send any keep-alives for 20 seconds, the client will disconnect and yields a "Timed out" exception.
     */

    int id;

    public SKeepAlivePacket5(int id) {
        this.id = id;
    }

    public SKeepAlivePacket5() {}


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(id);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        id=packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO send keepalive to server
    }
}
