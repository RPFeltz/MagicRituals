package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Ghast;

/**
 *
 * @author rpfeltz
 */
public class SpawnGhast extends SpawnMob {
    
    public SpawnGhast(Location location) {
        super(location, CreatureType.GHAST);
    }
    
    @Override
    public Ghast execute() {
        Ghast ghast = (Ghast) super.execute();
        return ghast;
    }
}
