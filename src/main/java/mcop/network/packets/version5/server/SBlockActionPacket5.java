package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SBlockActionPacket5 extends PacketBase<IClientPacketHandler> {

    int x;
    short y;
    int z;

    int byte1; //Varies depending on block - see Block_Actions
    int byte2;
    int blockType;


    /*
    This packet is used for a number of things:

    Chests opening and closing
    Pistons pushing and pulling
    Note blocks playing

    https://wiki.vg/Block_Actions
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(x);
        packetData.writeShort(y);
        packetData.writeInt(z);
        packetData.writeUByte(byte1);
        packetData.writeUByte(byte2);
        packetData.writeVarInt(blockType);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readInt();
        y = packetData.readShort();
        z = packetData.readInt();
        byte1 = packetData.readUByte();
        byte2 = packetData.readUByte();
        blockType = packetData.readVarInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
