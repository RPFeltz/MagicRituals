/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.magicrituals;

import net.minecraft.server.EnumSkyBlock;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftWorld;

/**
 *
 * @author rpfeltz
 */
public class RenderSpirit {
    private World world;
    private Location location;
    
    public RenderSpirit(World world, Location location) {
        this.world = world;
        this.location = location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public void render() {
        world.playEffect(location, Effect.SMOKE, 31); // location, effect, smokeDirection
    }
    
    public void blink(Block centerBlock) {
        world.playEffect(centerBlock.getLocation(), Effect.CLICK1, 16);
        world.playEffect(centerBlock.getLocation(), Effect.SMOKE, 31);
        world.playEffect(centerBlock.getRelative(BlockFace.NORTH).getLocation(), Effect.SMOKE, 31);
        world.playEffect(centerBlock.getRelative(BlockFace.EAST).getLocation(), Effect.SMOKE, 31);
        world.playEffect(centerBlock.getRelative(BlockFace.SOUTH).getLocation(), Effect.SMOKE, 31);
        world.playEffect(centerBlock.getRelative(BlockFace.WEST).getLocation(), Effect.SMOKE, 31);
        world.playEffect(centerBlock.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 31);
        world.playEffect(centerBlock.getRelative(BlockFace.DOWN).getLocation(), Effect.SMOKE, 31);
    }
}
