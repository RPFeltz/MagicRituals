package rpfeltz.magicrituals;

import rpfeltz.util.Task;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.util.Vector;
import rpfeltz.util.Delay;

/**
 *
 * @author rpfeltz
 */
public class EntitySpirit {
    private RenderSpirit renderer;
    private Location location;
    private Vector velocity = new Vector(0, 0, 0);
    private CreatureType type;
    private Location target;
    private int lifetime;
    private boolean dead;
     
    public EntitySpirit(World world, Location location, CreatureType type, int lifetime) {
        this.renderer = new RenderSpirit(world, location);
        this.location = location;
        this.type = type;
        this.lifetime = lifetime;
        this.target = getNearbySoulCollector();
        if (target == null) {
            setDead();
        }
        Delay.timer(new Task(this), 100);
    }
    
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
    
    public void addVelocity(Vector velocity, double intensity) {
        this.velocity.multiply(1 - intensity).add(velocity.multiply(intensity));
    }
    
    public void setLocation(Location location) {
        this.location = location;
        renderer.setLocation(location);
    }

    private void setDead() {
        setDead(true);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
    
    private Location getNearbySoulCollector() {
        Set<Location> points = MagicRituals.soulCollectionPoints.keySet();

        Location nearestPoint = null;
        double minDistance = 16;
        if (type == CreatureType.GHAST)
            minDistance = 48;
        
        for (Location point : points) {
            double distance = location.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPoint = point;
            }
        }
        
        return nearestPoint;
    }
    
    public void mainLoop() {
        if (target != null) {
            if (target.distance(location) <= 1.5) {
                setDead(true);
                HashMap<CreatureType,Integer> creatureCount = MagicRituals.soulCollectionPoints.get(target);
                if (creatureCount.containsKey(type))
                    creatureCount.put(type, creatureCount.get(type) + 1);
                else
                    creatureCount.put(type, 1);
                renderer.blink(target.getBlock());
                target.getBlock().setTypeId(76);
                
            } else {
                Location tempTarget = target.clone();
                Vector tempVelocity = (tempTarget.subtract(location)).toVector().normalize();
                this.addVelocity(tempVelocity, 0.1);
            }
        }
        location.add(velocity.getX(), velocity.getY(), velocity.getZ());
        renderer.render();
        lifetime--;
        if (lifetime < 1)
                setDead();
    }
}
