package mcop.entities;

import dev.hilligans.ourcraft.data.other.server.ServerPlayerData;
import dev.hilligans.ourcraft.entity.EntityType;
import dev.hilligans.ourcraft.entity.IPlayerEntity;
import dev.hilligans.ourcraft.world.newworldsystem.IWorld;
import mcop.MCOPServerPlayerData;

public class PlayerEntity extends LivingEntityBase implements IPlayerEntity {

    public String name;
    public MCOPServerPlayerData data;

    public PlayerEntity(String name) {
        super(null);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ServerPlayerData getPlayerData() {
        return null;
    }

}
