package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSpawnExperienceOrbPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    int x; //fixed point
    int y;
    int z;
    short count;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(entityID);
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
        packetData.writeShort(count);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readVarInt();
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
        count = packetData.readShort();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo implement
    }
}
