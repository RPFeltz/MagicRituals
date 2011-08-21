package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Giant;

/**
 *
 * @author rpfeltz
 */
public class SpawnGiant extends SpawnMob {
    
    public SpawnGiant(Location location) {
        super(location, CreatureType.GIANT);
    }
    
    @Override
    public Giant execute() {
        Giant giant = (Giant) super.execute();
        return giant;
    }
}
