package mcop.network.packets.version5.login;

import dev.hilligans.ourcraft.network.IPacketByteArray;
import dev.hilligans.ourcraft.network.IServerPacketHandler;
import dev.hilligans.ourcraft.network.PacketBase;
import mcop.MCOP;
import mcop.MCOPServerPlayerData;
import mcop.entities.PlayerEntity;
import mcop.server.MCOPServer;

import java.util.UUID;

public class CLoginStartPacket5 extends PacketBase<IServerPacketHandler> {

    String name;

    @Override
    public void encode(IPacketByteArray packetData) {
        packetData.writeUTF8(name);
    }

    @Override
    public void decode(IPacketByteArray packetData) {
        name = packetData.readUTF8();
    }

    @Override
    public void handle(IServerPacketHandler iServerPacketHandler) {
        if(((MCOPServer)iServerPacketHandler.getServer()).isOnline) {
            //TODO add encryption shit
        } else {
            System.out.println("Login success:" + name);
            //iServerPacketHandler.sendPacket(new SDisconnectPacket5("u suck idiot"), ctx);

            MCOPServerPlayerData playerData = (MCOPServerPlayerData) iServerPacketHandler.getServerPlayerData();
            iServerPacketHandler.sendPacket(new SLogicSuccessPacket5(UUID.randomUUID().toString(), name), ctx);

            //MCOPServerNetworkHandler packetHandler = (MCOPServerNetworkHandler) iServerPacketHandler;
            //MCOPServerPlayerData playerData = (MCOPServerPlayerData) packetHandler.mappedPlayerData.get(ctx.channel().id());
            playerData.playerName = name;
            PlayerEntity playerEntity = new PlayerEntity(name);
            playerEntity.setPosition(0, 10, 0);
            playerData.entity = playerEntity;
            playerData.table = MCOP.table;
            playerEntity.data = playerData;

            playerData.setServer(iServerPacketHandler.getServer());
            //packetHandler.mappedPlayerData.put(ctx.channel().id(), playerData);
            ((MCOPServer)iServerPacketHandler.getServer()).addPlayer(playerEntity);
            //
        }
    }
}
