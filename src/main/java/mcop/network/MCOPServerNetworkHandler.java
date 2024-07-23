package mcop.network;

import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.network.*;
import dev.hilligans.ourcraft.network.packet.client.CHandshakePacket;
import dev.hilligans.ourcraft.network.packet.server.SChatMessage;
import dev.hilligans.ourcraft.network.packet.server.SSendPlayerList;
import dev.hilligans.ourcraft.server.IServer;
import io.netty.channel.*;

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
        channels.add(ctx.channel());
        channelIds.add(ctx.channel().id());
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
        //TODO handle unloading
        ServerPlayerData serverPlayerData = mappedPlayerData.remove(ctx.channel().id());
        if(serverPlayerData != null) {
            serverPlayerData.handleDisconnect();
            serverPlayerData.close();
            //sendPacketInternal(new SSendPlayerList(serverPlayerData.getPlayerName(), (int) serverPlayerData.getPlayerID().l1,false));
            //sendPacketInternal(new SChatMessage(serverPlayerData.getPlayerName() + " has left the game"));
        }
        // mappedChannels.remove(id);
        channelIds.remove(ctx.channel().id());
        super.channelInactive(ctx);
        System.err.println("Server channel innactive");
    }

    public ChannelFuture sendPacket(PacketBase<?> packetBase, ChannelHandlerContext ctx) {
        packetBase.packetId = getNetwork().sendProtocol.packetMap.get(packetBase.getClass());
        return ctx.channel().writeAndFlush(new PacketByteArray(packetBase));
    }

    public void sendPacket(PacketBase<?> packetBase, ChannelId channelId) {
        packetBase.packetId = getNetwork().sendProtocol.packetMap.get(packetBase.getClass());
        sendPacket(packetBase, channels.find(channelId));
    }

    @Override
    public void sendPacketInternal(PacketBase<?> packetBase) {
        for(int x = 0; x < channelIds.size(); x++) {
            packetBase.packetId = getNetwork().sendProtocol.packetMap.get(packetBase.getClass());
            Channel channel = channels.find(channelIds.get(x));
            if(channel == null) {
                channelIds.remove(x);
                x--;
                continue;
            }
            sendPacket(packetBase, channel);
        }
    }

}
