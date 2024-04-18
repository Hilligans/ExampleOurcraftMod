package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;

    /*
    Most entity-related packets are subclasses of this packet.
    When sent from the server to the client, it may initialize the entry.

    For player entities, either this packet or any move/look packet is sent every game tick.
    So the meaning of this packet is basically that the entity did not move/look since the last such packet.
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO handle
    }
}
