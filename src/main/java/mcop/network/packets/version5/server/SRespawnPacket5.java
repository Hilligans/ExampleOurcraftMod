package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SRespawnPacket5 extends PacketBase<IClientPacketHandler> {

    public int dimension;
    public int difficulty;
    public int gamemode;
    public String level;



    /*
    To change the player's dimension (overworld/nether/end),
    send them a respawn packet with the appropriate dimension,
    followed by prechunks/chunks for the new dimension, and finally a position and look packet.
    You do not need to unload chunks, the client will do it automatically.
     */

    /*
     Warning.png If the Dimension isn't valid then the client will crash

    Warning.png Avoid changing player's dimension to same dimension they were already in,
    weird bugs can occur i.e. such player will be unable to attack other players in new world (fixes after their death and respawn)
     */
    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(dimension);
        packetData.writeUByte(difficulty);
        packetData.writeUByte(gamemode);
        packetData.writeUTF8(level);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        dimension = packetData.readInt();
        difficulty = packetData.readUByte();
        gamemode = packetData.readUByte();
        level = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
