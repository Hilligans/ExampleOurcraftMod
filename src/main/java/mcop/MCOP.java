package mcop;

import dev.hilligans.ourcraft.ClientMain;
import dev.hilligans.ourcraft.GameInstance;
import dev.hilligans.ourcraft.client.Client;
import dev.hilligans.ourcraft.mod.handler.ModClass;
import dev.hilligans.ourcraft.mod.handler.content.ModContainer;
import dev.hilligans.ourcraft.mod.handler.content.RegistryView;
import dev.hilligans.ourcraft.mod.handler.pipeline.InstanceLoaderPipeline;
import dev.hilligans.ourcraft.util.registry.Registry;
import mcop.effects.Effect;
import mcop.effects.Effects;
import mcop.network.MCOPClientNetwork;
import mcop.network.MCOPServerNetwork;
import mcop.network.packets.version5.Version5;
import mcop.server.MCOPServer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MCOP extends ModClass {

    @Override
    public void registerRegistries(RegistryView view) {
        view.registerRegistry(() -> new Registry<Effect>(view.gameInstance, Effect.class, "effects"));
    }

    @Override
    public void registerHooks(InstanceLoaderPipeline<?> pipeline) {
        pipeline.POST_HOOKS.clear();
        pipeline.POST_CORE_HOOKS.clear();

        final MCOPClientNetwork[] network = new MCOPClientNetwork[1];

        pipeline.addPostHook(new Consumer<GameInstance>() {
            @Override
            public void accept(GameInstance gameInstance) {

                if(true) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                System.err.println("Starting server");
                                MCOPServerNetwork net = new MCOPServerNetwork(gameInstance, gameInstance.PROTOCOLS.getExcept("mcop:5-handshake"), new MCOPServer(gameInstance));
                                net.startServer("25565");
                            } catch (Exception e) {
                                e.printStackTrace();
                                ;
                            }
                        }
                    };
                    thread.start();

                } else {
                    try {
                        network[0] = new MCOPClientNetwork(gameInstance, gameInstance.PROTOCOLS.getExcept("mcop:5-handshake"));
                        Client client = new Client(gameInstance, ClientMain.argumentContainer);
                        client.network = network[0];
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    network[0].joinServer("localhost", "25565", client);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        };
                        thread.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void registerContent(ModContainer container) {
        Effects.registerEffects((Registry<Effect>) container.registries.get("mcop:effects"));

        Version5.register(container);
        super.registerContent(container);
    }

    @Override
    public String getModID() {
        return "mcop";
    }
}
