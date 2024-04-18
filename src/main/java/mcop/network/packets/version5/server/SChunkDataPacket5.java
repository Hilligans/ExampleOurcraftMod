package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SChunkDataPacket5 extends PacketBase<IClientPacketHandler> {

    int chunkX;
    int chunkY;
    boolean groundUpContinuous;
    int primaryBitMap;
    int addBitMap;
    int compressedSize;
    byte[] data; //The chunk data is compressed using Zlib Deflate

    /*
    Chunks are not unloaded by the client automatically.
    To unload chunks, send this packet with ground-up continuous=true and no 16^3 chunks (eg. primary bit mask=0).
    The server does not send skylight information for nether-chunks,
    it's up to the client to know if the player is currently in the nether.
    You can also infer this information from the primary bitmask and the amount of uncompressed bytes sent.

    https://wiki.vg/Chunk_Format
     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(chunkX);
        packetData.writeInt(chunkY);
        packetData.writeBoolean(groundUpContinuous);
        packetData.writeUShort(primaryBitMap);
        packetData.writeUShort(addBitMap);
        packetData.writeInt(compressedSize);
        packetData.writeBytesN(data);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        chunkX = packetData.readInt();
        chunkY = packetData.readInt();
        groundUpContinuous = packetData.readBoolean();
        primaryBitMap = packetData.readUShort();
        addBitMap = packetData.readUShort();
        compressedSize = packetData.readInt();
        data = packetData.readBytes(compressedSize);
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo implement
    }
}
