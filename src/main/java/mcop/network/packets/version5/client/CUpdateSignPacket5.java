package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CUpdateSignPacket5 extends PacketBase<IServerPacketHandler> {

    //This message is sent from the client to the server when the "Done" button is pushed after placing a sign.

    int x;
    short y;
    int z;
    String line1;
    String line2;
    String line3;
    String line4;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(x);
        packetData.writeShort(y);
        packetData.writeInt(z);
        packetData.writeUTF8(line1);
        packetData.writeUTF8(line2);
        packetData.writeUTF8(line3);
        packetData.writeUTF8(line4);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readInt();
        y = packetData.readShort();
        z = packetData.readInt();
        line1 = packetData.readUTF8();
        line2 = packetData.readUTF8();
        line3 = packetData.readUTF8();
        line4 = packetData.readUTF8();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
