package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SMultiBlockChangePacket5 extends PacketBase<IClientPacketHandler> {

    int chunkX;
    int chunkY;

    short recordCount; //The number of blocks affected
    int dataSize;   //The total size of the data, in bytes.
                    // Should always be 4*record count
    int[] records;

    /*
    Record
    Bit mask 	    Width 	    Meaning
    00 00 00 0F 	4 bits 	    Block metadata
    00 00 FF F0 	12 bits 	Block ID
    00 FF 00 00 	8 bits 	    Y co-ordinate
    0F 00 00 00 	4 bits 	    Z co-ordinate, relative to chunk
    F0 00 00 00 	4 bits 	    X co-ordinate, relative to chunk

     */

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeInt(chunkX);
        packetData.writeInt(chunkY);
        packetData.writeShort(recordCount);
        packetData.writeInts(records);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        chunkX = packetData.readInt();
        chunkY = packetData.readInt();
        recordCount = packetData.readShort();
        records = packetData.readInts();
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {
        //todo handle
    }
}
