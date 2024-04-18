package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityLookPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    byte yaw; //The X Axis rotation as a fraction of 360
    byte pitch;

    /*
    This packet is sent by the server when an entity rotates.
    Example: "Yaw" field 64 means a 90 degree turn.
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(yaw);
        packetData.writeByte(pitch);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        yaw = packetData.readByte();
        pitch = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
