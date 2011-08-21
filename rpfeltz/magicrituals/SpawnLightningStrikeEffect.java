/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import rpfeltz.util.Action;
import org.bukkit.Location;
import org.bukkit.entity.LightningStrike;

/**
 *
 * @author rpfeltz
 */
public class SpawnLightningStrikeEffect extends Action {
    private Location location;
    
    public SpawnLightningStrikeEffect(Location location) {
        this.location = location;
    }

    @Override
    public LightningStrike execute() {
        LightningStrike strikeLightningEffect = location.getWorld().strikeLightningEffect(location);
        return strikeLightningEffect;
    }
}
