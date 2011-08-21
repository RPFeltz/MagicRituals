/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import rpfeltz.util.Delay;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;

/**
 *
 * @author rpfeltz
 */
public class SpawnSpider extends SpawnMob {
    private Player owner;
    private Entity mounting;
    
    public SpawnSpider(Location location) {
        super(location, CreatureType.SPIDER);
    }
    
    public SpawnSpider(Location location, Player owner) {
        super(location, CreatureType.SPIDER);
        this.owner = owner;
    }
    
    public SpawnSpider(Location location, Entity mounting) {
        super(location, CreatureType.SPIDER);
        this.mounting = mounting;
    }
    
    @Override
    public Spider execute() {
        Delay delay = new Delay();
        Spider spider = (Spider)super.execute();
        if (mounting != null) {
            spider.setPassenger(mounting);
        } else if (owner != null) {
            MagicRituals.owners.put(spider, owner);
            spider.setPassenger(owner);
        }
        return spider;
    }
}
