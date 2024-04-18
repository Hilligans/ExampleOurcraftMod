package mcop.network.packets.version5.login;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SLogicSuccessPacket5 extends PacketBase<IClientPacketHandler> {

    String uuid;
    String username;

    public SLogicSuccessPacket5(String uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }

    public SLogicSuccessPacket5() {}


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(uuid);
        packetData.writeUTF8(username);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        uuid = packetData.readUTF8();
        username = packetData.readUTF8();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
