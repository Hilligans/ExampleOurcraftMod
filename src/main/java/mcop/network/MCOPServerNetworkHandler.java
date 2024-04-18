package mcop.network;

import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.network.*;
import dev.hilligans.ourcraft.network.packet.client.CHandshakePacket;
import dev.hilligans.ourcraft.server.IServer;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
public class MCOPServerNetworkHandler extends ServerNetworkHandler {
    public MCOPServerNetworkHandler(ServerNetwork network, IServer server) {
        super(network, server);
    }

    public ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.err.println("Channel Active");
        service.schedule(new Runnable() {
            @Override
            public void run() {
               // ctx.close();
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IPacketByteArray msg) throws Exception {
        PacketBase<?> packetBase = msg.createPacket(network.receiveProtocol);
        try {
            ServerPlayerData serverPlayerData = mappedPlayerData.get(ctx.channel().id());
            if(serverPlayerData == null) {
                packetBase.handle(this);
            } else  {
                packetBase.handle(serverPlayerData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.err.println("Server channel innactive");
    }

    public ChannelFuture sendPacket(PacketBase<?> packetBase, ChannelHandlerContext ctx) {
        packetBase.packetId = getNetwork().sendProtocol.packetMap.get(packetBase.getClass());
        return ctx.channel().writeAndFlush(new PacketByteArray(packetBase));
    }
}
