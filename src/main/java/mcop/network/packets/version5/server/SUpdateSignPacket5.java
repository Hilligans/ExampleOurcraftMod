package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SUpdateSignPacket5 extends PacketBase<IClientPacketHandler> {

    //This message is sent from the server to the client whenever a sign is discovered or created.
    //This message is NOT sent when a sign is destroyed or unloaded.

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
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
