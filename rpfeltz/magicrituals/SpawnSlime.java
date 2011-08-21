/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Slime;

/**
 *
 * @author rpfeltz
 */
public class SpawnSlime extends SpawnMob {
    private int size;
    
    public SpawnSlime(Location location, int size) {
        super(location, CreatureType.SLIME);
        this.size = size;
    }
    
    @Override
    public Slime execute() {
        Slime slime = (Slime)super.execute();
        slime.setSize(size);
        return slime;
    }
}
