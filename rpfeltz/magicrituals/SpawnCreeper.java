/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;

/**
 *
 * @author rpfeltz
 */
public class SpawnCreeper extends SpawnMob {
    private boolean powered = false;
    
    public SpawnCreeper(Location location) {
        super(location, CreatureType.CREEPER);
    }
    
    public SpawnCreeper(Location location, boolean powered) {
        super(location, CreatureType.CREEPER);
        this.powered = powered;
    }
    
    @Override
    public Creeper execute() {
        Creeper creeper = (Creeper)super.execute();
        creeper.setPowered(powered);
        return creeper;
    }
}
