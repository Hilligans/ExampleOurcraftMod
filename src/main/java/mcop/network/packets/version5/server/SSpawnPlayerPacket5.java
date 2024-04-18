package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSpawnPlayerPacket5 extends PacketBase<IClientPacketHandler> {

    /*
    This packet is sent by the server when a player comes into visible range, not when a player joins.

    Servers can, however, safely spawn player entities for players not in visible range. The client appears to handle it correctly.

    When in online-mode the uuids must be valid and have valid skin blobs, in offline-mode uuid v3 is used.

    For NPCs UUID v2 should be used. Note:

  <+Grum> i will never confirm this as a feature you know that :)
     */

    int entityID;
    String uuid;
    String name;
    int dataCount;

    int x; //these are supposed to be fixed point numbers
    int y;
    int z;

    byte yaw;
    byte pitch;
    short currentItem;



    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeVarInt(entityID);
        packetData.writeUTF8(uuid);
        packetData.writeUTF8(name);
        packetData.writeVarInt(dataCount);
        //TODO data
        packetData.writeInt(x);
        packetData.writeInt(y);
        packetData.writeInt(z);
        packetData.writeByte(yaw);
        packetData.writeByte(pitch);
        packetData.writeShort(currentItem);
        //TODO metadata
    }

    /*
     Warning.png The client will crash if no metadata is sent Warning.png
     The client will disconnect if both UUID and Name are empty
     */

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readVarInt();
        uuid = packetData.readUTF8();
        name = packetData.readUTF8();
        dataCount = packetData.readVarInt();
        for(int x = 0; x < dataCount; x++) {
            String name = packetData.readUTF8();
            String value = packetData.readUTF8();
            String signature = packetData.readUTF8();
        }
        x = packetData.readInt();
        y = packetData.readInt();
        z = packetData.readInt();
        yaw = packetData.readByte();
        pitch = packetData.readByte();
        currentItem = packetData.readShort();

        //todo read metadata
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo implement
    }
}
