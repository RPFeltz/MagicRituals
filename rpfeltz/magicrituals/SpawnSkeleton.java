package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Skeleton;

/**
 *
 * @author rpfeltz
 */
public class SpawnSkeleton extends SpawnMob {
    
    public SpawnSkeleton(Location location) {
        super(location, CreatureType.SKELETON);
    }
    
    @Override
    public Skeleton execute() {
        Skeleton skeleton = (Skeleton) super.execute();
        return skeleton;
    }
}
