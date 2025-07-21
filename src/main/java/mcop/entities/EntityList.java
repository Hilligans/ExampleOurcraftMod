package mcop.entities;

import dev.hilligans.ourcraft.entity.EntityType;
import dev.hilligans.ourcraft.entity.IEntity;
import dev.hilligans.ourcraft.mod.handler.content.ModContainer;

import java.util.ArrayList;
import java.util.HashMap;

import static mcop.entities.IEntityTable.Types.Player;

public class EntityList implements IEntityTable {

    public ArrayList<MCOPEntityType> entityList = new ArrayList<>();
    public HashMap<String, MCOPEntityType> entityTypes = new HashMap<>();

    @Override
    public IEntity createEntityWithType(EntityType type) {
        return null;
    }

    @Override
    public void loadEntities(ModContainer modContainer) {

    }

    @Override
    public EntityType getEntityType(Types type) {
        return switch (type) {
            default -> entityTypes.get(type.name());
        };
    }
}
