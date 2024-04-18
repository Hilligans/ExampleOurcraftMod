package mcop.network.packets.version5.login;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SEncryptionRequestPacket5 extends PacketBase<IClientPacketHandler> {

    String serverID;
    short keyLength;
    byte[] key;
    short verifyLength;
    byte[] verifyToken;


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(serverID);
        packetData.writeShort(keyLength);
        for(int x = 0; x < key.length; x++) {
            packetData.writeByte(key[x]);
        }
        packetData.writeShort(verifyLength);
        for(int x = 0; x < verifyToken.length; x++) {
            packetData.writeByte(verifyToken[x]);
        }
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        serverID = packetData.readUTF8();
        keyLength = packetData.readShort();
        key = packetData.readBytes(keyLength);
        verifyLength = packetData.readShort();
        verifyToken = packetData.readBytes(verifyLength);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
