package mcop;

import dev.hilligans.ourcraft.ClientMain;
import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.client.Client;
import dev.hilligans.ourcraft.mod.handler.ModClass;
import dev.hilligans.ourcraft.mod.handler.content.ModContainer;
import dev.hilligans.ourcraft.mod.handler.content.RegistryView;
import dev.hilligans.ourcraft.mod.handler.pipeline.InstanceLoaderPipeline;
import dev.hilligans.ourcraft.server.IServer;
import dev.hilligans.ourcraft.util.registry.Registry;
import dev.hilligans.ourcraft.world.newworldsystem.SimpleServerWorld;
import mcop.blocks.IBlockConversionTable;
import mcop.blocks.Version5BlockTable;
import mcop.effects.Effect;
import mcop.effects.Effects;
import mcop.net.NetEngine;
import mcop.net.Protocols;
import mcop.network.packets.version5.Version5;
import mcop.network.packets.version5.Version5PacketInterface;
import mcop.server.MCOPServer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MCOP extends ModClass {

    ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    public static IBlockConversionTable table;

    @Override
    public void registerRegistries(RegistryView view) {
        view.registerRegistry(() -> new Registry<Effect>(view.gameInstance, Effect.class, "effects"));
    }

    @Override
    public void registerHooks(InstanceLoaderPipeline<?> pipeline) {
        pipeline.POST_HOOKS.clear();
        pipeline.POST_CORE_HOOKS.clear();

        pipeline.POST_HOOKS.add(new Consumer<GameInstance>() {
            @Override
            public void accept(GameInstance gameInstance) {
                Version5BlockTable table = (Version5BlockTable) MCOP.table;
                table.buildMappings();
            }
        });

        //final MCOPClientNetwork[] network = new MCOPClientNetwork[1];

        pipeline.addPostHook(new Consumer<GameInstance>() {
            @Override
            public void accept(GameInstance gameInstance) {

                if(true) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                System.err.println("Starting server");
                                MCOPServer mcopServer = new MCOPServer(gameInstance);
                                SimpleServerWorld world = new SimpleServerWorld(0, "overworld");
                                mcopServer.addWorld(world);
                                System.out.println(gameInstance);

                                System.out.println("Starting");
                                IServer.Server server = new IServer.Server(mcopServer);
                                service.scheduleAtFixedRate(server, 0, 1000/20, TimeUnit.MILLISECONDS);
                                mcopServer.startServer("25565").connectSocket();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();

                }
            }
        });
    }

    @Override
    public void registerContent(ModContainer container) {
        Effects.registerEffects((Registry<Effect>) container.registries.get("mcop:effects"));

        Version5.register(container);

        container.registerNetworkEngine(new NetEngine());
        Protocols.register(container);

        super.registerContent(container);
    }

    @Override
    public String getModID() {
        return "mcop";
    }
}
