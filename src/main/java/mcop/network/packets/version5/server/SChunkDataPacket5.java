package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;
import dev.hilligans.ourcraft.util.IByteArray;
import dev.hilligans.ourcraft.world.newworldsystem.IChunk;
import dev.hilligans.ourcraft.world.newworldsystem.ISubChunk;
import mcop.blocks.IBlockConversionTable;
import mcop.blocks.Version5BlockTable;
import mcop.network.packets.version5.Version5PacketInterface;

import java.util.zip.Deflater;

public class SChunkDataPacket5 extends PacketBase<IClientPacketHandler> {

    int chunkX;
    int chunkZ;
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


    /*
    Data

    The data is compressed using the deflate() function in zlib.
    In 0x21, it describes of a single chunk column in the format below.
    In 0x26, the format below is repeated for each chunk column.

    The format is thus:

        Block type array - whole byte per block
        Block metadata array - half byte per block
        Block light array - half byte per block
        Sky light array - half byte per block - only if 'skylight' is true
        Add array - half byte per block - uses secondary bitmask
        Biome array - whole byte per XZ coordinate, 256 bytes total, only sent if 'ground up continuous' is true

    Each section (except for biome) contains packed chunk data for zero or more 16x16x16 chunks.
    You need to refer to the primary bitmask for the chunk column to see which chunks are sent, or the add bitmask for 'add' data, which is Mojang's means of provided Block IDs past 256. In vanilla minecraft, you can expect this to always be zero.

    Chunks are sent bottom-to-top, i.e. the first chunk, if sent, extends from Y=0 to Y=15.
    Blocks are ordered Y, Z, X, i.e. the 'X' coordinate changes fastest.

    In half-byte arrays, two values are packed into each byte.
    Even-indexed items are packed into the high bits, odd-indexed into the low bits.

    The 'biome' array is always 256 bytes when sent. Values above 22 will cause the client to crash.
     */

    public SChunkDataPacket5() {}

    public SChunkDataPacket5(IChunk chunk, IBlockConversionTable table) {
        this.chunkX = (int) chunk.getX();
        this.chunkZ = (int) chunk.getZ();
        this.groundUpContinuous = true;

        int count = 0;
        for(int x = 0; x < chunk.getSubChunkCount(); x++) {
            ISubChunk subChunk = chunk.getSubChunkWidthIndex(x);
            if(subChunk != null && !subChunk.isEmpty()) {
                count++;
                primaryBitMap |= (1 << x);
            }
        }
        addBitMap = 0;

        byte[] vals = new byte[count * 10240 + 256];


        int add = 0;
        for(int i = 0; i < chunk.getSubChunkCount(); i++) {
            ISubChunk subChunk = chunk.getSubChunkWidthIndex(i);
            if(subChunk != null && !subChunk.isEmpty()) {
                System.out.println("yes");
                for(int y = 0; y < 16; y++) {
                    for(int z = 0; z < 16; z++) {
                        for(int x = 0; x < 16; x++) {
                            int id = table.getBlockID(subChunk.getBlockState(chunk.getWorld(), x, y, z));

                            vals[add] = (byte) ((id >> 4));
                            vals[count * 4096 + add/2] |= (byte) ((id & 0xF) << ((add & 1) * 4));

                            vals[count * (4096 + 2048) + add/2] = ~((byte)0);
                          //  vals[count * (4096 + 2048) + add/1] = ~((byte)0);
                            add+=1;
                        }
                    }
                }
            }
        }


        Deflater compress = new Deflater();
        compress.setInput(vals);
        compress.finish();
        data = new byte[vals.length];
        compressedSize = compress.deflate(data);
    }


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(chunkX);
        packetData.writeInt(chunkZ);
      //  System.err.println("X:" + chunkX + " Z:" + chunkZ);
        packetData.writeBoolean(groundUpContinuous);
        packetData.writeUShort(primaryBitMap);
        packetData.writeUShort(addBitMap);
        packetData.writeInt(compressedSize);
        for(int x = 0; x < compressedSize; x++) {
            packetData.writeByte(data[x]);
        }
        //packetData.writeBytesN(data);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        chunkX = packetData.readInt();
        chunkZ = packetData.readInt();
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
