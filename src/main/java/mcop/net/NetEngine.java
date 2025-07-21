package mcop.net;

import dev.hilligans.ourcraft.network.*;
import dev.hilligans.ourcraft.network.engine.NettyEngine;
import dev.hilligans.ourcraft.network.packet.PacketType;
import dev.hilligans.ourcraft.network.packet.packet.SServerExceptionPacket;
import dev.hilligans.ourcraft.server.IServer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import mcop.network.MCOPPacketDecoder;
import mcop.network.MCOPPacketEncoder;

public class NetEngine extends NettyEngine {

    public String getResourceName() {
        return "netEngine";
    }

    public String getResourceOwner() {
        return "mcop";
    }

    @Override
    public NettySocket openServer(Protocol protocol, IServer server, String port) {
        NetSocket socket = new NetSocket(this, protocol, server, Integer.parseInt(port));
        this.addSocket(socket);
        return socket;
    }

    public static class NetSocket extends NettySocket {

        public NetSocket(NettyEngine engine, Protocol protocol, IServer server, int port) {
            super(engine, protocol, server, port);
        }

        @Override
        protected void initChannel(SocketChannel ch) {
            System.out.println("Init Channel");
            ch.pipeline().addLast(new MCOPPacketEncoder());
            ch.pipeline().addLast(new MCOPPacketDecoder());
            ch.pipeline().addLast((new NetEntity(this.protocol, this)).setNetworkEngine(this.engine));
        }
    }

    public static class NetEntity extends NettyNetworkEntity {

        public NetEntity(Protocol initialProtocol, NettySocket socket) {
            super(initialProtocol, socket);
        }

        protected void channelRead0(ChannelHandlerContext ctx, IPacketByteArray msg) throws Exception {
            try {
                PacketType packet = this.getReceiveProtocol().fromIdToPacketType(msg.readVarInt());
                System.err.println("Reading packet:" + packet.getClass());
                packet.decode(this, msg);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
