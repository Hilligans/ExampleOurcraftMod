package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SUseBedPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    int x;
    int y;
    int z;


    /*
    This packet tells that a player goes to bed.

    The client with the matching Entity ID will go into bed mode.

    This Packet is sent to all nearby players including the one sent to bed.
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeInt(x);
        packetData.writeUByte(y);
        packetData.writeInt(z);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        x = packetData.readInt();
        y = packetData.readUByte();
        z = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO implement
    }
}
