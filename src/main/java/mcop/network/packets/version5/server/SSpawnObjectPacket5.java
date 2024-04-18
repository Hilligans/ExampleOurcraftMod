package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSpawnObjectPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    byte type;
    int x; //more fixed point numbers
    int y;
    int z;
    byte pitch;
    byte yaw;

    int data;


    //Sent by the server when an Object/Vehicle is created.

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(entityID);
        packetData.writeByte(type);
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
        packetData.writeByte(pitch);
        packetData.writeByte(yaw);
        packetData.writeInt(data);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readVarInt();
        type = packetData.readByte();
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
        pitch = packetData.readByte();
        yaw = packetData.readByte();
        data = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO implement
    }
}
