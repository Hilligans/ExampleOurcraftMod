package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SPlayerPositionAndLookPacket5 extends PacketBase<IClientPacketHandler> {

    double x;
    double y;
    double z;
    float yaw;
    float pitch;
    boolean onGround;


    /*
    Updates the players position on the server.
    If the distance between the last known position of the player on the server and the new
    position set by this packet is greater than 100 units will result in the client being
    kicked for "You moved too quickly :( (Hacking?)" Also if the fixed-point number of X or Z
    is set greater than 3.2E7D the client will be kicked for "Illegal position"

    Yaw is measured in degrees, and does not follow classical trigonometry rules.
    The unit circle of yaw on the XZ-plane starts at (0, 1) and turns counterclockwise,
    with 90 at (-1, 0), 180 at (0,-1) and 270 at (1, 0). Additionally, yaw is not clamped to between 0 and 360 degrees;
    any number is valid, including negative numbers and numbers greater than 360.

    Pitch is measured in degrees, where 0 is looking straight ahead,
    -90 is looking straight up, and 90 is looking straight down.

    The yaw of player (in degrees), standing at point (x0,z0) and looking towards point (x,z) one can be calculated with:

    l = x-x0
    w = z-z0
    c = sqrt( l*l + w*w )
    alpha1 = -arcsin(l/c)/PI*180
    alpha2 =  arccos(w/c)/PI*180
    if alpha2 > 90 then
        yaw = 180 - alpha1
    else
        yaw = alpha1

    You can get a unit vector from a given yaw/pitch via:

    x = -cos(pitch) * sin(yaw)
    y = -sin(pitch)
    z =  cos(pitch) * cos(yaw)
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeDouble(x);
        packetData.writeDouble(y);
        packetData.writeDouble(z);
        packetData.writeFloat(yaw);
        packetData.writeFloat(pitch);
        packetData.writeBoolean(onGround);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readDouble();
        y = packetData.readDouble();
        z = packetData.readDouble();
        yaw = packetData.readFloat();
        pitch = packetData.readFloat();
        onGround = packetData.readBoolean();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
