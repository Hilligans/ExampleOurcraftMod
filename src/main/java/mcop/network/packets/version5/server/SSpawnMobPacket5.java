package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSpawnMobPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    int type;
    int x;  //fixed point
    int y;
    int z;
    byte yaw;
    byte pitch;
    byte head;
    short velX;
    short velY;
    short velZ;


    //Sent by the server when a Mob Entity is Spawned

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(entityID);
        packetData.writeUByte(type);
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
        packetData.writeByte(yaw);
        packetData.writeByte(pitch);
        packetData.writeByte(head);
        packetData.writeShort(velX);
        packetData.writeShort(velY);
        packetData.writeShort(velZ);
        //metadata
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readVarInt();
        type = packetData.readUByte();
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
        yaw = packetData.readByte();
        pitch = packetData.readByte();
        head = packetData.readByte();
        velX = packetData.readShort();
        velY = packetData.readShort();
        velZ = packetData.readShort();
        //metadata
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo implement
    }
}
