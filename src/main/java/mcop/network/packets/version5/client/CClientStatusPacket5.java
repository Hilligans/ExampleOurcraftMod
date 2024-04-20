package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CClientStatusPacket5 extends PacketBase<IServerPacketHandler> {

    //Sent when the client is ready to complete login and when the client is ready to respawn after death.

    /*
    Action ID values:
    Action ID 	Name
    0 	Perform respawn
    1 	Request stats
    2 	Open inventory achievement
     */

    byte actionID;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeByte(actionID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        actionID = packetData.readByte();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
