package mcop.entities;

import dev.hilligans.ourcraft.entity.IPlayerEntity;
import dev.hilligans.ourcraft.world.newworldsystem.IWorld;
import mcop.MCOPServerPlayerData;

public class PlayerEntity extends EntityBase implements IPlayerEntity {

    public String name;
    public MCOPServerPlayerData data;

    public PlayerEntity(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
