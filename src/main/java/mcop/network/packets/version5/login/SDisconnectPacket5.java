package mcop.network.packets.version5.login;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SDisconnectPacket5 extends PacketBase<IClientPacketHandler> {

    public String message;

    public SDisconnectPacket5() {}

    public SDisconnectPacket5(String message) {
        this.message = message;
    }

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(message);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        message = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //TODO open disconnect screen
    }
}
