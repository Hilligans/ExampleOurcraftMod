package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SMapsPacket5 extends PacketBase<IClientPacketHandler> {

    int itemDamage;
    short length;
    byte[] data;

    //If the first byte of the array is 0, the next two bytes are X start and Y start and the rest of the bytes are the colors in that column.
    //
    //If the first byte of the array is 1, the rest of the bytes are in groups of three: (data, x, y). The lower half of the data is the type (always 0 under vanilla) and the upper half is the direction.
    //
    //If the first byte of the array is 2, the second byte is the map scale.


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(itemDamage);
        packetData.writeShort(length);
        packetData.writeBytesN(data);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        itemDamage = packetData.readVarInt();
        length = packetData.readShort();
        data = packetData.readBytes(length);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
