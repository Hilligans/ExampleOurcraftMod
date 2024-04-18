package mcop.network;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketByteArray;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MCOPPacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        int packetLength = in.readableBytes();

        System.out.println(packetLength);

        int numRead = 0;
        int result = 0;

        byte read;
        do {
            if(packetLength == 0) {
                in.resetReaderIndex();
                return;
            }
            packetLength--;
            read = in.readByte();
            System.err.println(Integer.toBinaryString(read & 0xFF));
            int value = read & 127;
            result |= value << (7 * numRead);
            ++numRead;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while((read & 128) != 0);

        if(packetLength >= result) {
            System.out.println("Result:" + result);
            byte[] bytes = new byte[result];
            in.readBytes(bytes);

            IPacketByteArray packetData = new PacketByteArray(bytes);
            System.err.println("PacketID:" + packetData.getPacketID());
            packetData.setOwner(ctx);
            list.add(packetData);
            in.markReaderIndex();
            System.err.println("Bytes:" + in.readableBytes());
        } else {
            System.err.println("Expected:" + result + " got " + packetLength);
            in.resetReaderIndex();
        }
    }
}
