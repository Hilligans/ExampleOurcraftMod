package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSpawnGlobalEntityPacket5 extends PacketBase<IClientPacketHandler> {

    //With this packet, the server notifies the client of thunderbolts striking within a 512 block radius around the player.
    //The coordinates specify where exactly the thunderbolt strikes.


    int entityID; //The entity ID of the thunderbolt
    byte type; //The global entity type, currently always 1 for thunderbolt.
    int x; //Thunderbolt X a fixed-point number, https://wiki.vg/Data_types#Fixed-point_numbers
    int y;
    int z;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(entityID);
        packetData.writeByte(type);
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        type = packetData.readByte();
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
