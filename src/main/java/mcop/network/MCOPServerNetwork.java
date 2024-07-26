package mcop.network;

import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.Ourcraft;
import dev.hilligans.ourcraft.network.*;
import dev.hilligans.ourcraft.server.IServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class MCOPServerNetwork extends ServerNetwork {

    public MCOPServerNetwork(GameInstance gameInstance, Protocol protocol, IServer server) {
        super(gameInstance, protocol, server);
        System.out.println("instance = " + gameInstance);
    }

    @Override
    public void startServer(String port) throws Exception {
        System.out.println("inst = " + this.gameInstance);
        networkHandler = new MCOPServerNetworkHandler(this, server, gameInstance.PROTOCOLS.getExcept("mcop:5-handshake"), gameInstance.PROTOCOLS.getExcept("mcop:5-handshake"));
        ServerNetworkHandler.debug = Ourcraft.getArgumentContainer().getBoolean("--tracePacket", false);

        final int PORT = Integer.parseInt(System.getProperty("port", port));

        SelfSignedCertificate ssc = new SelfSignedCertificate();
        sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(this);

            channelFuture = b.bind(PORT).sync();
            channelFuture.channel().closeFuture().sync();
            System.err.println("Closing server");
           // channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    @Override
    protected void initChannel(SocketChannel ch) {
        System.err.println("Innit channel");

        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast(new ChannelHandler[]{this.sslCtx.newHandler(ch.alloc())});

        pipeline.addLast(new MCOPPacketEncoder());
        pipeline.addLast(new MCOPPacketDecoder());

        //  System.out.println(this.networkHandler);
        ((ServerNetworkHandler) networkHandler).ssl = false;
        pipeline.addLast(new ChannelHandler[]{this.networkHandler});
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }
}
