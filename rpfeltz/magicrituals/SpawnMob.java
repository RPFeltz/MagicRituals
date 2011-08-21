package rpfeltz.magicrituals;

import rpfeltz.util.Action;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;

/**
 *
 * @author rpfeltz
 */
public class SpawnMob extends Action {
    private final Location location;
    private final CreatureType type;
    
    public SpawnMob(Location location, CreatureType type) {
        this.location = location;
        this.type = type;
    }
    
    @Override
    public LivingEntity execute() {
        return location.getWorld().spawnCreature(location, type);
    }
}
