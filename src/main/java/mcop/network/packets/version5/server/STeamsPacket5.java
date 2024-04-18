package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class STeamsPacket5 extends PacketBase<IClientPacketHandler> {

    String teamName; //A unique name for the team. (Shared with scoreboard).
    byte mode; /*
    f 0 then the team is created.

If 1 then the team is removed.

If 2 the team team information is updated.

If 3 then new players are added to the team.

If 4 then players are removed from the team. */
    String teamDisplayName; //Only if Mode = 0 or 2.
    String teamPrefix; //Only if Mode = 0 or 2. Displayed before the players' name that are part of this team.
    String teamSuffix; //Only if Mode = 0 or 2. Displayed after the players' name that are part of this team.
    byte friendlyFire; //Only if Mode = 0 or 2; 0 for off, 1 for on, 3 for seeing friendly invisibles
    short playerCount; //Only if Mode = 0 or 3 or 4. Number of players in the arra
    String[] players; //Only if Mode = 0 or 3 or 4. Players to be added/remove from the team.


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(teamDisplayName);
        packetData.writeByte(mode);
        packetData.writeUTF8(teamDisplayName);
        packetData.writeUTF8(teamPrefix);
        packetData.writeUTF8(teamSuffix);
        packetData.writeByte(friendlyFire);
        packetData.writeShort(playerCount);
        for(int x = 0; x < playerCount; x++) {
            packetData.writeUTF8(players[x]);
        }
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        teamDisplayName = packetData.readUTF8();
        mode = packetData.readByte();
        teamDisplayName = packetData.readUTF8();
        teamPrefix = packetData.readUTF8();
        teamSuffix = packetData.readUTF8();
        friendlyFire = packetData.readByte();
        playerCount = packetData.readShort();
        players = new String[playerCount];
        for(int x = 0; x < playerCount; x++) {
            players[x] = packetData.readUTF8();
        }
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
