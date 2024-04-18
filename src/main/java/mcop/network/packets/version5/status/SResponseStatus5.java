package mcop.network.packets.version5.status;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SResponseStatus5 extends PacketBase<IClientPacketHandler> {

    public String response;

    public SResponseStatus5(String response) {
        this.response = response;
    }

    public SResponseStatus5() {}

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(response);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        response = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        System.err.println("Server response: " + response);
        iClientPacketHandler.sendPacket(new CPingStatus5(100001), ctx);
    }
}
