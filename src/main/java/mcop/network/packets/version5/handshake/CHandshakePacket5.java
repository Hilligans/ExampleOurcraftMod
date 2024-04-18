package mcop.network.packets.version5.handshake;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.server.MCOPServer;

public class CHandshakePacket5 extends PacketBase<IServerPacketHandler> {

    public int version = 5;
    public String address;
    public int port;
    public int nextState;

    public CHandshakePacket5(String address, int port, int nexState) {
        this.address = address;
        this.port = port;
        this.nextState = nexState;
    }

    public CHandshakePacket5() {}

    @Override
    public void encode(IPacketByteArray iPacketByteArray) {
        iPacketByteArray.writeVarInt(5);
        iPacketByteArray.writeUTF8(address);//server address
        iPacketByteArray.writeUShort(port);//port
        iPacketByteArray.writeVarInt(nextState);//1 for status, 2 for login
    }

    @Override
    public void decode(IPacketByteArray iPacketByteArray) {
        version = iPacketByteArray.readVarInt();
        address = iPacketByteArray.readUTF8();
        port = iPacketByteArray.readUShort();
        nextState = iPacketByteArray.readVarInt();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        System.err.println("address:"+address);
        System.err.println("port:"+port);
        System.err.println("version:"+version);
        System.err.println("state:"+nextState);
        MCOPServer server = (MCOPServer) iServerPacketHandler.getServer();
        if(server.getVersion() != version) {
            System.err.println("WRONG VERSION");
            iServerPacketHandler.handleDisconnect();
        }

        if(nextState == 1) {
            iServerPacketHandler.getServerNetworkHandler().network.sendProtocol = iServerPacketHandler.getGameInstance().PROTOCOLS.getExcept("mcop:5-status-server");
            iServerPacketHandler.getServerNetworkHandler().network.receiveProtocol = iServerPacketHandler.getGameInstance().PROTOCOLS.getExcept("mcop:5-status-client");
        } else if(nextState == 2) {
            iServerPacketHandler.getNetwork().setSendProtocol("mcop:5-login-server");
            iServerPacketHandler.getNetwork().setReceiveProtocol("mcop:5-login-client");
        } else {
            ctx.close();
            throw new RuntimeException();
        }
    }
}
