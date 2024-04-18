package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSpawnPositionPacket5 extends PacketBase<IClientPacketHandler> {

    int x;
    int y;
    int z;

    /*
    Sent by the server after login to specify the coordinates of the spawn point
    (the point at which players spawn at, and which the compass points to).
    It can be sent at any time to update the point compasses point at.
     */

    public SSpawnPositionPacket5(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SSpawnPositionPacket5() {}

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
        //TODO handle
    }
}
