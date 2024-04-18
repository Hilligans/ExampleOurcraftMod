package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SDisconnectPacket5 extends PacketBase<IClientPacketHandler> {

    /*
    Sent by the server before it disconnects a client. The server assumes that the sender has already closed the connection by the time the packet arrives.
     */

    String reason; //Displayed to the client when the connection terminates. Must be valid JSON.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(reason);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        reason = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
