package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SCollectItemPacket5 extends PacketBase<IClientPacketHandler> {

    int collectedEntityID;
    int collectorEntityID;

    /*
    Sent by the server when someone picks up an item lying on the ground -
    its sole purpose appears to be the animation of the item flying towards you.
    It doesn't destroy the entity in the client memory, and it doesn't add it to your inventory.
    The server only checks for items to be picked up after each Player Position and [Player Position & Look packet sent by the client.
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(collectedEntityID);
        packetData.writeInt(collectorEntityID);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        collectedEntityID = packetData.readInt();
        collectorEntityID = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO implement
    }
}
