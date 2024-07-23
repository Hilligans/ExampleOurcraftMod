package mcop.server;

import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.entity.IPlayerEntity;
import dev.hilligans.ourcraft.entity.living.entities.PlayerEntity;
import dev.hilligans.ourcraft.network.PacketBase;
import dev.hilligans.ourcraft.network.ServerNetwork;
import dev.hilligans.ourcraft.network.ServerNetworkHandler;
import dev.hilligans.ourcraft.server.IServer;
import dev.hilligans.ourcraft.world.newworldsystem.ClassicChunk;
import dev.hilligans.ourcraft.world.newworldsystem.IServerWorld;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import mcop.MessageUtil;
import mcop.network.PacketInterface;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class MCOPServer implements IServer {

    public GameInstance gameInstance;
    public int versionNumber = 5;
    public boolean isOnline = false;

    public int tickCount;

    ServerNetwork serverNetwork;
    PacketInterface packetInterface;

    public ConcurrentLinkedQueue<IPlayerEntity> players = new ConcurrentLinkedQueue<>();
    public Int2ObjectOpenHashMap<IServerWorld> worlds = new Int2ObjectOpenHashMap<>();

    public int worldID = 0;

    public MCOPServer(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }


    public MCOPServer setServerNetworkHandler(ServerNetwork serverNetwork) {
        this.serverNetwork = serverNetwork;
        return this;
    }

    public MCOPServer setPacketInterface(PacketInterface packetInterface) {
        this.packetInterface = packetInterface;
        return this;
    }

    public int getVersion() {
        return versionNumber;
    }


    @Override
    public void addWorld(IServerWorld world) {
        worlds.put(worldID++, world);
        ClassicChunk chunk = new ClassicChunk(world, 16, 0, 0);
        Random random = new Random();
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                if(random.nextBoolean()) {
                    chunk.setBlockState(x, 0, z, gameInstance.BLOCKS.getExcept("mcop:red_sand").getDefaultState());
                } else {
                    chunk.setBlockState(x, 0, z, gameInstance.BLOCKS.getExcept("mcop:stone").getDefaultState());
                }
                chunk.setBlockState(x, 15, z, gameInstance.BLOCKS.getExcept("mcop:dirt").getDefaultState());
                chunk.setBlockState(x, 17, z, gameInstance.BLOCKS.getExcept("mcop:dirt").getDefaultState());
            }
        }
        System.out.println(chunk.get(0, 0, 0).isEmpty());


        world.setChunk(0, 0, 0, chunk);
    }



    public void addPlayer(IPlayerEntity player) {
        players.add(player);
    }

    @Override
    public IServerWorld getWorld(ServerPlayerData serverPlayerData) {
        return null;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public void setTime(long time) {

    }

    @Override
    public void tick() {
        try {
            while(players.peek() != null) {
                IPlayerEntity playerEntity = players.poll();

                IServerWorld world = worlds.get(0);
                playerEntity.setWorld(world);
                packetInterface.sendWorldInfo(this, world, ((mcop.entities.PlayerEntity)playerEntity).data);
                for(int x = -10; x < 10; x++) {
                    for(int z = -10; z < 10; z++) {
                        packetInterface.sendChunk(world.getChunkNonNull(x * 16, 0, z * 16), playerEntity);
                    }
                }
                System.err.println(world.getChunk(0, 0, 0).getSubChunkWidthIndex(0).isEmpty());
                packetInterface.sendPositionAndLook(playerEntity);
            }


            tickCount++;
            if (tickCount >= 20) {
                tickCount = 0;
                doKeepaliveChecks();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doKeepaliveChecks() {
        //System.out.println("keepalive");
        int time = (int) (System.currentTimeMillis()/1000);
        foreachPlayer(serverPlayerData -> {
            Integer time1 = (Integer) serverPlayerData.arbDataMap.put("timeoutTime", time);

            if(time1 == null) {
                serverPlayerData.disconnect(MessageUtil.getServerDisconnectMessage("timeout"));
                return;
            }
            if((time - time1) > 30) {
                serverPlayerData.disconnect(MessageUtil.getServerDisconnectMessage("timeout"));
            }
        });

        packetInterface.sendKeepalivePacket(time);
    }

    public void foreachPlayer(Consumer<ServerPlayerData> playerDataConsumer) {
        getServerNetworkHandler().mappedPlayerData.values().forEach(playerDataConsumer);
    }

    @Override
    public Object executeCommand(String command) {
        return null;
    }

    @Override
    public ServerNetworkHandler getServerNetworkHandler() {
        return (ServerNetworkHandler) serverNetwork.networkHandler;
    }

    @Override
    public void sendPacket(PacketBase<?> packetBase, ServerPlayerData playerData) {
        getServerNetworkHandler().sendPacket(packetBase, playerData.getChannelId());
    }

    @Override
    public void sendPacket(PacketBase packetBase) {}

    @Override
    public void sendPacket(PacketBase packetBase, PlayerEntity playerEntity) {}

    @Override
    public GameInstance getGameInstance() {
        return gameInstance;
    }

    @Override
    public void stop() {

    }
}
