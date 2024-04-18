package mcop.network.packets.version5.status;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;

public class CRequestStatus5 extends PacketBase<IServerPacketHandler> {
    @Override
    public void encode(IPacketByteArray packetData) {

    }

    @Override
    public void decode(IPacketByteArray packetData) {

    }

    public static String message = "{\"description\":\"A Pee pee poo poo\",\"players\":{\"max\":20,\"online\":0},\"version\":{\"name\":\"1.7.10\",\"protocol\":5}}";

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        System.out.println("Requesting response");
        iServerPacketHandler.sendPacket(new SResponseStatus5(message), ctx);

    }
}
