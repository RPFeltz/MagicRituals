/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Sheep;

/**
 *
 * @author rpfeltz
 */
public class SpawnSheep extends SpawnMob {
    private DyeColor color = DyeColor.WHITE;
    private boolean sheared = false;
    
    public SpawnSheep(Location location) {
        super(location, CreatureType.SHEEP);
    }
    
    public SpawnSheep(Location location, DyeColor color) {
        super(location, CreatureType.SHEEP);
        this.color = color;
    }
    
    public SpawnSheep(Location location, boolean sheared) {
        super(location, CreatureType.SHEEP);
        this.sheared = sheared;
    }
    
    public SpawnSheep(Location location, DyeColor color, boolean sheared) {
        super(location, CreatureType.SHEEP);
        this.color = color;
        this.sheared = sheared;
    }
    
    public SpawnSheep(Location location, boolean sheared, DyeColor color) {
        super(location, CreatureType.SHEEP);
        this.color = color;
        this.sheared = sheared;
    }
    
    @Override
    public Sheep execute() {
        Sheep sheep = (Sheep)super.execute();
        sheep.setColor(color);
        sheep.setSheared(sheared);
        return sheep;
    }
}
