package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPlayerPositionAndLookPacket5 extends PacketBase<IServerPacketHandler> {

    //A combination of Player Look and Player position.

    double x;
    double feetY;
    double headY;
    double z;
    float yaw;
    float pitch;
    boolean onGround;



    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeDouble(x);
        packetData.writeDouble(feetY);
        packetData.writeDouble(headY);
        packetData.writeDouble(z);
        packetData.writeFloat(yaw);
        packetData.writeFloat(pitch);
        packetData.writeBoolean(onGround);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readDouble();
        feetY = packetData.readDouble();
        headY = packetData.readDouble();
        z = packetData.readDouble();
        yaw = packetData.readFloat();
        pitch = packetData.readFloat();
        onGround = packetData.readBoolean();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
