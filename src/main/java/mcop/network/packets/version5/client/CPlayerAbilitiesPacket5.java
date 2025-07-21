package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CPlayerAbilitiesPacket5 extends PacketBase<IServerPacketHandler> {

    /*
    The latter 2 bytes are used to indicate the walking and flying speeds respectively,
    while the first byte is used to determine the value of 4 booleans.

    The flags are whether damage is disabled (god mode, 8, bit 3),
    whether the player can fly (4, bit 2), whether the player is flying (2, bit 1), and whether the player is in creative mode (1, bit 0).

    To get the values of these booleans, simply AND (&) the byte with 1,2,4 and 8 respectively,
    to get the 0 or 1 bitwise value. To set them OR (|) them with their repspective masks.
    The vanilla client sends this packet when the player starts/stops flying with the second parameter changed accordingly.
    All other parameters are ignored by the vanilla server.
     */

    byte flags;
    float flyingSpeed; // 	previous integer value divided by 250
    float walkingSpeed;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(flags);
        packetData.writeFloat(flyingSpeed);
        packetData.writeFloat(walkingSpeed);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        flags = packetData.readByte();
        flyingSpeed = packetData.readFloat();
        walkingSpeed = packetData.readFloat();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        //System.out.println(flags);
    }
}
