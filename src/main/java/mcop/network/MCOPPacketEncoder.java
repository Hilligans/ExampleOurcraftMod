package mcop.network;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketByteArray;
import dev.hilligans.ourcraft.util.IByteArray;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MCOPPacketEncoder extends MessageToByteEncoder<IPacketByteArray> {

    protected void encode(ChannelHandlerContext ctx, IPacketByteArray msg, ByteBuf out) throws Exception {
        PacketByteArray arr = new PacketByteArray(out);

        arr.writeVarInt((int) msg.getSize() + IByteArray.varIntLength(msg.getPacketID()));
        arr.writeVarInt(msg.getPacketID());
        System.out.println("PacketIDDD:" + msg.getPacketID());
        out.writeBytes(msg.getByteBuf());
    }
}
