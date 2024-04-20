package mcop.network.packets.version5.client;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CSteerVehiclePacket5 extends PacketBase<IServerPacketHandler> {

    float sideways; // 	Positive to the left of the player
    float forward; //Positive forward
    boolean jump;
    boolean unmount; //True when leaving the vehicle


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeFloat(sideways);
        packetData.writeFloat(forward);
        packetData.writeBoolean(jump);
        packetData.writeBoolean(unmount);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        sideways = packetData.readFloat();
        forward = packetData.readFloat();
        jump = packetData.readBoolean();
        unmount = packetData.readBoolean();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {

    }
}
