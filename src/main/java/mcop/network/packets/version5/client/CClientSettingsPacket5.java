package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CClientSettingsPacket5 extends PacketBase<IServerPacketHandler> {

    //Sent when the player connects, or when settings are changed.


    String locale; //en_GB
    byte viewDistance; //Client-side render distance(chunks)
    byte chatFlags; //Chat settings. See notes below.
    boolean chatColours; //"Colours" multiplayer setting
    byte difficulty; //Client-side difficulty from options.txt
    boolean showCape; //Show Cape multiplayer setting

    //Chat flags has several values packed into one byte.
    //
    //Chat Enabled: Bits 0-1. 00: Enabled. 01: Commands only. 10: Hidden.


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(locale);
        packetData.writeByte(viewDistance);
        packetData.writeByte(chatFlags);
        packetData.writeBoolean(chatColours);
        packetData.writeByte(difficulty);
        packetData.writeBoolean(showCape);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        locale = packetData.readUTF8();
        viewDistance = packetData.readByte();
        chatFlags = packetData.readByte();
        chatColours = packetData.readBoolean();
        difficulty = packetData.readByte();
        showCape = packetData.readBoolean();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
