package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SExplosionPacket5 extends PacketBase<IClientPacketHandler> {

    float x;
    float y;
    float z;
    float radius; //Currently unused in the client
    int recordCount; //This is the count, not the size. The size is 3 times this value.
    byte[] records; //Each record is 3 signed bytes long, each bytes are the XYZ (respectively) offsets of affected blocks.

    float motionX; //X velocity of the player being pushed by the explosion
    float motionY; //Y velocity of the player being pushed by the explosion
    float motionZ; //Z velocity of the player being pushed by the explosion


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeFloat(x);
        packetData.writeFloat(y);
        packetData.writeFloat(z);
        packetData.writeFloat(radius);
        packetData.writeInt(recordCount);
        packetData.writeBytesN(records);
        packetData.writeFloat(motionX);
        packetData.writeFloat(motionY);
        packetData.writeFloat(motionZ);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        x = packetData.readFloat();
        y = packetData.readFloat();
        z = packetData.readFloat();
        radius = packetData.readFloat();
        recordCount = packetData.readInt();
        records = packetData.readBytes(recordCount);
        motionX = packetData.readFloat();
        motionY = packetData.readFloat();
        motionZ = packetData.readFloat();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
