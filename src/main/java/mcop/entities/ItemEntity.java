package mcop.entities;

import dev.hilligans.ourcraft.entity.IEntity;
import dev.hilligans.ourcraft.entity.IPlayerEntity;

import java.util.function.Consumer;

public class ItemEntity extends EntityBase {
    public ItemEntity(MCOPEntityType entityType) {
        super(entityType);
    }

    @Override
    public void tick() {
        IEntity self = this;
        getWorld().forEachPlayerInRange((long) getX(), (long) getY(), (long) getZ(), 2, new Consumer<IPlayerEntity>() {
            @Override
            public void accept(IPlayerEntity iPlayerEntity) {
                if(iPlayerEntity.intersects(self)) {

                }
            }
        });
    }
}
