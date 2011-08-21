/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.PigZombie;

/**
 *
 * @author rpfeltz
 */
public class SpawnPigZombie extends SpawnMob {
    
    public SpawnPigZombie(Location location) {
        super(location, CreatureType.PIG_ZOMBIE);
    }
    
    @Override
    public PigZombie execute() {
        PigZombie pigZombie = (PigZombie)super.execute();
        return pigZombie;
    }
}
