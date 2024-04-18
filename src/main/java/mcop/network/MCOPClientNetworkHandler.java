package mcop.network;

import dev.hilligans.ourcraft.network.ClientNetwork;
import dev.hilligans.ourcraft.network.ClientNetworkHandler;
import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.PacketBase;
import io.netty.channel.ChannelHandlerContext;
import mcop.network.packets.version5.handshake.CHandshakePacket5;
import mcop.network.packets.version5.status.CRequestStatus5;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MCOPClientNetworkHandler extends ClientNetworkHandler {
    public MCOPClientNetworkHandler(ClientNetwork network) {
        super(network);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        sendPacket(new CHandshakePacket5("localhost", 25565, 1));
        network.setSendProtocol("mcop:5-status-client");
        network.setReceiveProtocol("mcop:5-status-server");
        Thread.sleep(500);
        sendPacket(new CRequestStatus5());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IPacketByteArray msg) throws Exception {
        System.out.println("Packet");
        PacketBase<?> packetBase = msg.createPacket(network.receiveProtocol);
        packets.add(packetBase);
        System.err.println(packetBase);
        processPackets();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //super.channelInactive(ctx);
        System.out.println("Channel Innactive");
    }
}
