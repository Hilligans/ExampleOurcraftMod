package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPlayerPositionPacket5 extends PacketBase<IServerPacketHandler> {

    /*
    Updates the players XYZ position on the server.
    If HeadY - FeetY is less than 0.1 or greater than 1.65,
    the stance is illegal and the client will be kicked with the message “Illegal Stance”.
    If the distance between the last known position of the player on the server and the new
    position set by this packet is greater than 100 units will result in the client being
    kicked for "You moved too quickly :( (Hacking?)" Also if the fixed-point number of X or Z
    is set greater than 3.2E7D the client will be kicked for "Illegal position"
     */

    double x; //Absolute position
    double feetY; //Absolute feet position, normally HeadY - 1.62. Used to modify the players bounding box when going up stairs, crouching, etc…
    double headY;
    double z;
    boolean onGround;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeDouble(x);
        packetData.writeDouble(feetY);
        packetData.writeDouble(headY);
        packetData.writeDouble(z);
        packetData.writeBoolean(onGround);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readDouble();
        feetY = packetData.readDouble();
        headY = packetData.readDouble();
        z = packetData.readDouble();
        onGround = packetData.readBoolean();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        //todo handle
    }
}
