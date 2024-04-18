package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityTeleportPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    int x; // X position as a Fixed-Point number
    int y;
    int z;
    byte yaw; // The X Axis rotation as a fraction of 360
    byte pitch;

    //This packet is sent by the server when an entity moves more than 4 blocks.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
        packetData.writeByte(yaw);
        packetData.writeByte(pitch);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
        yaw = packetData.readByte();
        pitch = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
