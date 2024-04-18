package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SBlockBreakAnimationPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    int x;
    int y;
    int z;

    byte stage; // 	0 - 9

    /*
    0-9 are the displayable destroy stages and each other number means that there is no animation on this coordinate.

    You can also set an animation to air! The animation will still be visible.

    If you need to display several break animations at the same time you have to give each of them a unique Entity ID.

    Also if you set the coordinates to a special block like water, it won't show the actual break animation, but some other interesting effects. (Water will lose its transparency)
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(entityID);
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
        packetData.writeByte(stage);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readVarInt();
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
        stage =  packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
