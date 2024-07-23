package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SJoinGamePacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    int gamemode; //0: survival, 1: creative, 2: adventure. Bit 3 (0x8) is the hardcore flag
    byte dimension; //-1: nether, 0: overworld, 1: end
    int difficulty; //0 thru 3 for Peaceful, Easy, Normal, Hard
    int maxPlayers;
    String worldType; //default, flat, largeBiomes, amplified, default_1_1

    public SJoinGamePacket5() {}

    public SJoinGamePacket5(int entityID, int gamemode, byte dimension, int difficulty, int maxPlayers, String worldType) {
        this.entityID = entityID;
        this.gamemode = gamemode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.worldType = worldType;
    }

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
