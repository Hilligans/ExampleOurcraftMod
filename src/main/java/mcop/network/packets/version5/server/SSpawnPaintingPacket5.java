package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSpawnPaintingPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    String title;
    int x;  //center coordinates
    int y;
    int z;
    int direction;

    /*
    This packet shows location, name, and type of painting.

    Calculating the center of an image: given a (width x height) grid of cells,
    with (0, 0) being the top left corner, the center is (max(0, width / 2 - 1),
    height / 2). E.g.

    2x1 (1, 0) 4x4 (1, 2)
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(entityID);
        packetData.writeUTF8(title);
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
        packetData.writeInt(direction);
    }

    @Override
    public void decode(IPacketByteArray packetData) {

    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
