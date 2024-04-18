package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SJoinGamePacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    int gamemode;
    byte dimension;
    int difficulty;
    int maxPlayers;
    String worldType;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeUByte(gamemode);
        packetData.writeByte(dimension);
        packetData.writeUByte(difficulty);
        packetData.writeUByte(maxPlayers);
        packetData.writeUTF8(worldType);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        gamemode = packetData.readUByte();
        dimension = packetData.readByte();
        difficulty = packetData.readUByte();
        maxPlayers = packetData.readUByte();
        worldType = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
