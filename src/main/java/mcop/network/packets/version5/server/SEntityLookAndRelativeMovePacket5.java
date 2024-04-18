package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityLookAndRelativeMovePacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    byte dx; //Change in X position as a Fixed-Point number
    byte dy;
    byte dz;
    byte yaw; //The X Axis rotation as a fraction of 360
    byte pitch;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(dx);
        packetData.writeByte(dy);
        packetData.writeByte(dz);
        packetData.writeByte(yaw);
        packetData.writeByte(pitch);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        dx = packetData.readByte();
        dy = packetData.readByte();
        dz = packetData.readByte();
        yaw = packetData.readByte();
        pitch = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
