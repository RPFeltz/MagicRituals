package rpfeltz.util;

import java.util.HashMap;
import net.minecraft.server.EnumSkyBlock;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.util.Vector;

/**
 *
 * @author rpfeltz
 */
public class LightAPI {
    private static HashMap<Vector, Integer> lightLevels = new HashMap<Vector, Integer>();
    
    public static void setLightLevel(World world, Location location, int intensity) {
        Block centerBlock = world.getBlockAt(location);
        int centerLightLevel = centerBlock.getLightLevel();
        CraftWorld craftWorld = (CraftWorld) world;
        Vector blockVector = new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        
        if (lightLevels.containsKey(blockVector)) {
            restoreLightLevels(craftWorld, location, intensity);
            lightLevels.remove(blockVector);
        }
        
        setBlockLightLevel(craftWorld, world, location, centerLightLevel + intensity, centerLightLevel);
        
        for (int loopX = -intensity; loopX <= intensity; loopX++) {
            for (int loopY = -intensity; loopY <= intensity; loopY++) {
                for (int loopZ = -intensity; loopY <= intensity; loopZ++) {
                    int newLightLevel = world.getBlockAt(location).getLightLevel();
                }
            }
        }
    }
    
    public static void setBlockLightLevel(CraftWorld craftWorld, World world, Location location, int lightLevel, int oldLightLevel) {
        craftWorld.getHandle().b(EnumSkyBlock.BLOCK, location.getBlockX(),
                location.getBlockY(), location.getBlockZ(), Math.max(0, Math.min(15, lightLevel)));
        
        lightLevels.put(new Vector(location.getBlockX(), location.getBlockY(),
                location.getBlockZ()), oldLightLevel);
    }
    
    public static void setBlockLightLevelNoRegister(CraftWorld craftWorld, int BlockX, int BlockY, int BlockZ, int lightLevel) {
        craftWorld.getHandle().b(EnumSkyBlock.BLOCK, BlockX, BlockY, BlockZ, Math.max(0, Math.min(15, lightLevel)));
    }
    
    public static void restoreLightLevels(CraftWorld craftWorld, Location location, int intensity) {
        
    }
}
