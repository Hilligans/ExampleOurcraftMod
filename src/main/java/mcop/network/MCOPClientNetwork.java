package mcop.network;

import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.client.Client;
import dev.hilligans.ourcraft.network.*;
import dev.hilligans.ourcraft.network.debug.PacketTrace;
import dev.hilligans.ourcraft.network.debug.PacketTracePacketDecoder;
import dev.hilligans.ourcraft.network.debug.PacketTracePacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class MCOPClientNetwork extends ClientNetwork {


    public MCOPClientNetwork(GameInstance gameInstance, Protocol protocol) {
        super(gameInstance, protocol);
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast(new ChannelHandler[]{this.sslCtx.newHandler(ch.alloc())});

        pipeline.addLast(new MCOPPacketEncoder());
        pipeline.addLast(new MCOPPacketDecoder());

        //  System.out.println(this.networkHandler);
       // ((ServerNetworkHandler)networkHandler).ssl = false;
        pipeline.addLast(new ChannelHandler[]{this.networkHandler});
    }

    public void joinServer(String ip, String port, Client client) throws Exception {
        this.client = client;
        this.gameInstance = client.gameInstance;

        networkHandler = new MCOPClientNetworkHandler(this);

        final String HOST = System.getProperty("host", ip);
        final int PORT = Integer.parseInt(System.getProperty("port", port));
       // sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(this);
            ((NetworkHandler) networkHandler).setData(b.connect(HOST, PORT).sync().channel(), group, ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        flush();
    }
}
