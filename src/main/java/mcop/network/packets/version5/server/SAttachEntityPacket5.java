package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SAttachEntityPacket5 extends PacketBase<IClientPacketHandler> {

    //This packet is sent when a player has been attached to an entity (e.g. Minecart)

    int entityID;
    int vehicleID;
    boolean leashed;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeInt(vehicleID);
        packetData.writeBoolean(leashed);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        vehicleID = packetData.readInt();
        leashed = packetData.readBoolean();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
