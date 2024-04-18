package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SUpdateBlockEntityPacket5 extends PacketBase<IClientPacketHandler> {

    //Essentially a block update on a block entity.
    /*
    Actions

    1: Set mob displayed inside a mob spawner
    2: Set command block text (command and last execution status)
    3: Set rotation and skin of mob head
    4: Set type of flower in flower pot
     */

    int x;
    short y;
    int z;

    int action;
    byte[] data;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(x);
        packetData.writeShort(y);
        packetData.writeInt(z);
        packetData.writeUByte(action);
        packetData.writeShort((short) data.length);
        packetData.writeBytesN(data);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readInt();
        y = packetData.readShort();
        z = packetData.readInt();
        action = packetData.readUByte();
        int length = packetData.readShort();
        data = packetData.readBytes(length);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
