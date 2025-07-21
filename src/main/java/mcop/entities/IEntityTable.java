package mcop.entities;

import dev.hilligans.ourcraft.entity.EntityType;
import dev.hilligans.ourcraft.entity.IEntity;
import dev.hilligans.ourcraft.mod.handler.content.ModContainer;

public interface IEntityTable {

    IEntity createEntityWithType(EntityType type);

    void loadEntities(ModContainer modContainer);

    EntityType getEntityType(Types type);

    public static enum Types {
        Player("player");

        final String name;
        Types(String name) {
            this.name = name;
        }
    }
}
