package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Monster;

/**
 *
 * @author rpfeltz
 */
public class SpawnMonster extends SpawnMob {
    
    public SpawnMonster(Location location) {
        super(location, CreatureType.MONSTER);
    }
    
    @Override
    public Monster execute() {
        Monster monster = (Monster) super.execute();
        return monster;
    }
}
