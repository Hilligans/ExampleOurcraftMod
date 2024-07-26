package mcop.network;

import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.network.*;
import dev.hilligans.ourcraft.network.packet.client.CHandshakePacket;
import dev.hilligans.ourcraft.network.packet.server.SChatMessage;
import dev.hilligans.ourcraft.network.packet.server.SSendPlayerList;
import dev.hilligans.ourcraft.server.IServer;
import io.netty.channel.*;
import mcop.MCOPServerPlayerData;
import mcop.network.packets.version5.handshake.CHandshakePacket5;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ChannelHandler.Sharable
public class MCOPServerNetworkHandler extends ServerNetworkHandler {
    public MCOPServerNetworkHandler(ServerNetwork network, IServer server, Protocol sendProtocol, Protocol receiveProtocol) {
        super(network, server, sendProtocol, receiveProtocol);
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
        ServerPlayerData serverPlayerData = mappedPlayerData.get(ctx.channel().id());
        PacketBase<?> packetBase;
        if(serverPlayerData == null || serverPlayerData.receriveProtocol == null) {
            packetBase = msg.createPacket(defaultReceiveProtocol);
            if(packetBase instanceof CHandshakePacket5) {
                serverPlayerData = new MCOPServerPlayerData(network.gameInstance, "");
                serverPlayerData.serverNetworkHandler = this;
                serverPlayerData.setServer(this.getServer());
                mappedPlayerData.put(ctx.channel().id(), serverPlayerData);
            }
            packetBase.handle(serverPlayerData);
        } else {
            packetBase = msg.createPacket(serverPlayerData.getReceiveProtocol());
            packetBase.handle(serverPlayerData);
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
        System.out.println("Server channel innactive");
    }

    public ChannelFuture sendPacket(PacketBase<?> packetBase, ChannelHandlerContext ctx) {
        packetBase.packetId = getSendProtocol(ctx).packetMap.get(packetBase.getClass());
        return ctx.channel().writeAndFlush(new PacketByteArray(packetBase));
    }

    @Override
    public void sendPacket(PacketBase<?> packetBase, ChannelId channelId) {
        try {
            packetBase.packetId = getSendProtocol(channelId).packetMap.get(packetBase.getClass());
            sendPacket(packetBase, channels.find(channelId));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPacketInternal(PacketBase<?> packetBase) {
        for(int x = 0; x < channelIds.size(); x++) {
            packetBase.packetId = getSendProtocol(channelIds.get(x)).packetMap.get(packetBase.getClass());
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
