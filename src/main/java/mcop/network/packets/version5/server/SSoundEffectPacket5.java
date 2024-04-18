package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SSoundEffectPacket5 extends PacketBase<IClientPacketHandler> {

    String soundName;
    int effectX; //Effect X multiplied by 8
    int effectY; //Effect Y multiplied by 8
    int effectZ; //Effect Z multiplied by 8
    float volume; //1 is 100%, can be more
    int pitch; //63 is 100%, can be more



    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(soundName);
        packetData.writeInt(effectX);
        packetData.writeInt(effectY);
        packetData.writeInt(effectZ);
        packetData.writeFloat(volume);
        packetData.writeUByte(pitch);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        soundName = packetData.readUTF8();
        effectX = packetData.readInt();
        effectY = packetData.readInt();
        effectZ = packetData.readInt();
        volume = packetData.readFloat();
        pitch = packetData.readUByte();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
