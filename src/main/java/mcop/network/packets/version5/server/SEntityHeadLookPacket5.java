package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEntityHeadLookPacket5 extends PacketBase<IClientPacketHandler> {

    int entityID;
    byte headYaw; //Head yaw in steps of 2p/256



    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(entityID);
        packetData.writeByte(headYaw);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        entityID = packetData.readInt();
        headYaw = packetData.readByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
