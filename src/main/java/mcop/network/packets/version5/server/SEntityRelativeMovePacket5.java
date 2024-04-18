package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityRelativeMovePacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    byte dx; //change as a fixed point number
    byte dy;
    byte dz;


    /*
    This packet is sent by the server when an entity moves less then 4 blocks; if an entity moves more than 4 blocks Entity Teleport should be sent instead.

    This packet allows at most four blocks movement in any direction, because byte range is from -128 to 127.
    */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(dx);
        packetData.writeByte(dy);
        packetData.writeByte(dx);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        dx = packetData.readByte();
        dy = packetData.readByte();
        dz = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
