package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPlayerLookPacket5 extends PacketBase<IServerPacketHandler> {

    /*
    Updates the direction the player is looking in.

Yaw is measured in degrees, and does not follow classical trigonometry rules. The unit circle of yaw on the XZ-plane starts at (0, 1) and turns counterclockwise, with 90 at (-1, 0), 180 at (0,-1) and 270 at (1, 0). Additionally, yaw is not clamped to between 0 and 360 degrees; any number is valid, including negative numbers and numbers greater than 360.

Pitch is measured in degrees, where 0 is looking straight ahead, -90 is looking straight up, and 90 is looking straight down.

The yaw of player (in degrees), standing at point (x0,z0) and looking towards point (x,z) one can be calculated with:

     */

    /*
    l = x-x0
    w = z-z0
    c = sqrt( l*l + w*w )
    alpha1 = -arcsin(l/c)/PI*180
    alpha2 =  arccos(w/c)/PI*180
    if alpha2 > 90 then
        yaw = 180 - alpha1
    else
        yaw = alpha1
     */

    /*
    Pitch is measured in degrees, where 0 is looking straight ahead, -90 is looking straight up, and 90 is looking straight down.

    You can get a unit vector from a given yaw/pitch via:

    x = -cos(pitch) * sin(yaw)
    y = -sin(pitch)
    z =  cos(pitch) * cos(yaw)
     */

    float yaw;
    float pitch;
    boolean onGround;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeFloat(yaw);
        packetData.writeFloat(pitch);
        packetData.writeBoolean(onGround);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        yaw = packetData.readFloat();
        pitch = packetData.readFloat();
        onGround = packetData.readBoolean();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
