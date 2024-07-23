package mcop.network.packets.version5.server;

import dev.hilligans.ourcraft.network.IClientPacketHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;

public class SMapChunkBulkPacket5 extends PacketBase<IClientPacketHandler> {

    short chunkColumnCount; //The number of chunk in this packet
    int dataLength;
    boolean skyLightSent; //Whether or not the chunk data contains a light nibble array. This is true in the main world, false in the end + nether

    byte[] data = new byte[0];    //Compressed chunk data

    /*

    See also: https://wiki.vg/SMP_Map_Format

    To reduce the number of bytes this packet is used to send
    chunks together for better compression results.


    Meta
    Field Name 	Field Type 	Notes
    Chunk X 	Int 	The X Coordinate of the chunk
    Chunk Z 	Int 	The Z Coordinate of the chunk
    Primary bitmap 	Unsigned Short 	A bitmap which specifies which sections are not empty in this chunk
    Add bitmap 	Unsigned Short 	A bitmap which specifies which sections need add information because of very high block ids. not yet used

     */


    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeShort(chunkColumnCount);
        packetData.writeInt(dataLength);
        packetData.writeBoolean(skyLightSent);
        packetData.writeBytesN(data);

    }

    @Override
    public void decode(IPacketByteArray packetData) {
        //TODO finish
    }

    @Override
    public void handle(IClientPacketHandler iClientPacketHandler) {

    }
}
