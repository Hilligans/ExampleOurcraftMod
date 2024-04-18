package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityVelocityPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    short velX;
    short velY;
    short velZ;

    /*
    Velocity is believed to be in units of 1/8000 of a block per server tick (50ms);
    for example, -1343 would move (-1343 / 8000) = -0.167875 blocks per tick (or -3,3575 blocks per second).
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeShort(velX);
        packetData.writeShort(velY);
        packetData.writeShort(velZ);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        velX = packetData.readShort();
        velY = packetData.readShort();
        velZ = packetData.readShort();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
