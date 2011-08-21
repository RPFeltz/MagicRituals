/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Zombie;

/**
 *
 * @author rpfeltz
 */
public class SpawnZombie extends SpawnMob {
    
    public SpawnZombie(Location location) {
        super(location, CreatureType.ZOMBIE);
    }
    
    @Override
    public Zombie execute() {
        Zombie zombie = (Zombie)super.execute();
        return zombie;
    }
}
