package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SPlayerListItemPacket5 extends PacketBase<IClientPacketHandler> {

    //Sent by the notchian server to update the user list (<tab> in the client).

    String playerName;
    boolean online;
    short ping;



    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(playerName);
        packetData.writeBoolean(online);
        packetData.writeShort(ping);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        playerName = packetData.readUTF8();
        online = packetData.readBoolean();
        ping = packetData.readShort();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
