package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSignEditorOpenPacket5 extends PacketBase<IClientPacketHandler> {

    //Sent on placement of sign.

    int x;
    int y;
    int z;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
