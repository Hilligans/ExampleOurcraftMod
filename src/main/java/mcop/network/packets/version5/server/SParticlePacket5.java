package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SParticlePacket5 extends PacketBase<IClientPacketHandler> {

    ///Displays the named particle

    String name; //The name of the particle to create. A list can be found here https://gist.github.com/thinkofdeath/5110835
    float x; //X position of the particle
    float y; //Y position of the particle
    float z; //Z position of the particle
    float offsetX; //This is added to the X position after being multiplied by random.nextGaussian()
    float offsetY; //This is added to the Y position after being multiplied by random.nextGaussian()
    float offsetZ; //This is added to the Z position after being multiplied by random.nextGaussian()
    float particleData; //The data of each particle
    int numParticles; //The number of particles to create


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(name);
        packetData.writeFloat(x);
        packetData.writeFloat(y);
        packetData.writeFloat(z);
        packetData.writeFloat(offsetX);
        packetData.writeFloat(offsetY);
        packetData.writeFloat(offsetZ);
        packetData.writeFloat(particleData);
        packetData.writeInt(numParticles);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        name = packetData.readUTF8();
        x = packetData.readFloat();
        y = packetData.readFloat();
        z = packetData.readFloat();
        offsetX = packetData.readFloat();
        offsetY = packetData.readFloat();
        offsetZ = packetData.readFloat();
        particleData = packetData.readFloat();
        numParticles = packetData.readInt();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
