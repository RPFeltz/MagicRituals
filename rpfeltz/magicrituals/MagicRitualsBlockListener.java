package rpfeltz.magicrituals;

import java.util.HashMap;
import java.util.HashSet;
import rpfeltz.util.Delay;
import java.util.Random;
import java.util.Set;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * MagicRituals block listener
 * @author rpfeltz
 */
public class MagicRitualsBlockListener extends BlockListener {
    private final MagicRituals plugin;
    private final Random random;

    public MagicRitualsBlockListener(final MagicRituals plugin) {
        this.plugin = plugin;
        this.random = MagicRituals.random;
    }
    
    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        Block block = event.getBlock();
        World world = block.getWorld();
        Player player = event.getPlayer();
        Location centerLocation = block.getLocation();
        // Rune of Wool
        if (((block.getTypeId() == 37)
                || (block.getTypeId() == 38))
                && (block.getRelative(BlockFace.NORTH).getTypeId() == 35)
                && (block.getRelative(BlockFace.EAST).getTypeId() == 35)
                && (block.getRelative(BlockFace.SOUTH).getTypeId() == 35)
                && (block.getRelative(BlockFace.WEST).getTypeId() == 35)
                && (block.getRelative(BlockFace.NORTH).getData() == block.getRelative(BlockFace.EAST).getData())
                && (block.getRelative(BlockFace.EAST).getData() == block.getRelative(BlockFace.SOUTH).getData())
                && (block.getRelative(BlockFace.SOUTH).getData() == block.getRelative(BlockFace.WEST).getData())
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 2)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 2)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 2)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 2)) {
            // Check wool color.
            DyeColor color = DyeColor.getByData(block.getRelative(BlockFace.NORTH).getData());
            
            // Check flower type.
            int FlowerType = block.getTypeId();
            
            // Modify the rune structure.
            block.setTypeId(2);
            block.getRelative(BlockFace.NORTH).setTypeId(2);
            block.getRelative(BlockFace.EAST).setTypeId(2);
            block.getRelative(BlockFace.SOUTH).setTypeId(2);
            block.getRelative(BlockFace.WEST).setTypeId(2);
            
            // Put flower on top.
            block.getRelative(BlockFace.UP).setTypeId(FlowerType);
            
            // Spawn sheep.
            new SpawnSheep(centerLocation.add(0, 1, 0), color).execute();
        }
        
        // Rune of Spiders
        if ((block.getTypeId() == 35)
                && (block.getRelative(BlockFace.NORTH).getTypeId() == 0)
                && (block.getRelative(BlockFace.EAST).getTypeId() == 0)
                && (block.getRelative(BlockFace.SOUTH).getTypeId() == 0)
                && (block.getRelative(BlockFace.WEST).getTypeId() == 0)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 17)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 17)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 17)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 17)) {
            // Check wool color.
            int color = block.getData();
            
            // Modify the rune structure.
            block.setTypeId(0);
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).setTypeId(0);
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).setTypeId(0);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).setTypeId(0);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).setTypeId(0);
            
            // Spawn spider.
            Spider spider = new SpawnSpider(centerLocation, (color == 6) ? player : null).execute();
        }
        
        // Rune of Souls
        if (block.getTypeId() == 76) {
            block = block.getRelative(BlockFace.DOWN);
            if ((block.getTypeId() == 48)
                    && (block.getRelative(BlockFace.NORTH).getTypeId() == 88)
                    && (block.getRelative(BlockFace.EAST).getTypeId() == 88)
                    && (block.getRelative(BlockFace.SOUTH).getTypeId() == 88)
                    && (block.getRelative(BlockFace.WEST).getTypeId() == 88)
                    && ((block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 2)
                    || (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 3))
                    && ((block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 2)
                    || (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 3))
                    && ((block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 2)
                    || (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 3))
                    && ((block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 2)
                    || (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 3))
                    && (block.getRelative(BlockFace.NORTH, 2).getRelative(BlockFace.UP).getTypeId()  == 89)
                    && (block.getRelative(BlockFace.EAST, 2).getRelative(BlockFace.UP).getTypeId()  == 89)
                    && (block.getRelative(BlockFace.SOUTH, 2).getRelative(BlockFace.UP).getTypeId()  == 89)
                    && (block.getRelative(BlockFace.WEST, 2).getRelative(BlockFace.UP).getTypeId()  == 89)) {
                // Create soul collection point.
                MagicRituals.soulCollectionPoints.put(centerLocation, new HashMap<CreatureType, Integer>());
                
                block = block.getRelative(BlockFace.UP);
                
                block.setTypeId(75);
            }
        }
        
        // Rune of Creepers
        if ((block.getTypeId() == 46)
                && (block.getRelative(BlockFace.NORTH).getTypeId() == 18)
                && (block.getRelative(BlockFace.EAST).getTypeId() == 18)
                && (block.getRelative(BlockFace.SOUTH).getTypeId() == 18)
                && (block.getRelative(BlockFace.WEST).getTypeId() == 18)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getTypeId() == 4)
                && (block.getRelative(BlockFace.EAST).getRelative(BlockFace.EAST).getTypeId() == 4)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH).getTypeId() == 4)
                && (block.getRelative(BlockFace.WEST).getRelative(BlockFace.WEST).getTypeId() == 4)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.UP).getTypeId() == 4)
                && (block.getRelative(BlockFace.EAST).getRelative(BlockFace.EAST).getRelative(BlockFace.UP).getTypeId() == 4)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH).getRelative(BlockFace.UP).getTypeId() == 4)
                && (block.getRelative(BlockFace.WEST).getRelative(BlockFace.WEST).getRelative(BlockFace.UP).getTypeId() == 4)
                && (((block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 18)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 88)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 88)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 18))
                || ((block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 88)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 18)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 18)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 88)))) {
            // Modify the rune structure.
            block.setTypeId(0);
            block.getRelative(BlockFace.NORTH).setTypeId(0);
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).setTypeId(0);
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).setTypeId(0);
            block.getRelative(BlockFace.EAST).setTypeId(0);
            block.getRelative(BlockFace.SOUTH).setTypeId(0);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).setTypeId(0);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).setTypeId(0);
            block.getRelative(BlockFace.WEST).setTypeId(0);
            
            boolean powered = false;
            // Spawn lightning.
            if (block.getWorld().hasStorm()) {
                new SpawnLightningStrikeEffect(centerLocation).execute();
                powered = true;
            }
            
            // Spawn a creeper after 1 second.
            Delay.timer(new SpawnCreeper(centerLocation, powered), 1000);
        }
        
        // Rune of Nature
        if ((block.getTypeId() == 6)
                && ((block.getRelative(BlockFace.NORTH).getTypeId() == 8)
                || (block.getRelative(BlockFace.NORTH).getTypeId() == 9))
                && ((block.getRelative(BlockFace.EAST).getTypeId() == 8)
                || (block.getRelative(BlockFace.EAST).getTypeId() == 9))
                && ((block.getRelative(BlockFace.SOUTH).getTypeId() == 8)
                || (block.getRelative(BlockFace.SOUTH).getTypeId() == 9))
                && ((block.getRelative(BlockFace.WEST).getTypeId() == 8)
                || (block.getRelative(BlockFace.WEST).getTypeId() == 9))
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 18)
                && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 18)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 18)
                && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 18)) {
            // Check sapling type.
            int treeData = block.getData();
            TreeType treeType = TreeType.BIG_TREE;
            if (treeData == 0) {
                if (random.nextInt(5) == 0)
                    treeType = TreeType.TREE;
            }
            else if (treeData == 1) {
                if (random.nextInt(2) == 0)
                    treeType = TreeType.TALL_REDWOOD;
                else
                    treeType = TreeType.REDWOOD;
            }
            else
                treeType = TreeType.BIRCH;
                    
            // Modify the rune structure.
            block.setTypeId(2);
            block.getRelative(BlockFace.NORTH).setTypeId(9);
            block.getRelative(BlockFace.EAST).setTypeId(9);
            block.getRelative(BlockFace.SOUTH).setTypeId(9);
            block.getRelative(BlockFace.WEST).setTypeId(9);
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).setTypeId(2);
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).setTypeId(2);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).setTypeId(2);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).setTypeId(2);
            
            // Create tall grass.
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getRelative(BlockFace.UP).setTypeIdAndData(31, (byte)(1 + random.nextInt(2)), true);
            block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getRelative(BlockFace.UP).setTypeIdAndData(31, (byte)(1 + random.nextInt(2)), true);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getRelative(BlockFace.UP).setTypeIdAndData(31, (byte)(1 + random.nextInt(2)), true);
            block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getRelative(BlockFace.UP).setTypeIdAndData(31, (byte)(1 + random.nextInt(2)), true);
            
            // Create tree.
            boolean tree = false;
            if (random.nextInt(6) != 0)
                tree = centerLocation.getWorld().generateTree(centerLocation.add(0, 1, 0), treeType);
            
            // Create sugar canes if no tree was created.
            if (!tree) {
                for (int i = 0; i < 7; i++) {
                    block = block.getRelative(BlockFace.UP);
                    if (block.getTypeId() == 0)
                        block.setTypeId(83);
                    else
                        break;
                }
            }
        }
    }
    
    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        Block block = event.getBlock();
        World world = block.getWorld();
        Location centerLocation = block.getLocation();
        HashMap<Location, HashMap<CreatureType, Integer>> soulRunes = MagicRituals.soulCollectionPoints;
        
        if ((block.getTypeId() == 76) && soulRunes.containsKey(centerLocation)) {
            HashMap<CreatureType, Integer> souls = soulRunes.get(centerLocation);
            Object rawCreatureType = maxKeyByValue(souls);
            if (rawCreatureType instanceof CreatureType) {
                CreatureType creatureType = (CreatureType) rawCreatureType;
                LivingEntity creature = null;
                boolean noMount = false;
                int health = 2;

                if (souls.containsKey(CreatureType.GHAST)) {
                    health = 100;
                    creature = new SpawnCreeper(centerLocation).execute();
                    for (int index = 0; index < 9; index++)
                        new SpawnCreeper(centerLocation).execute().setHealth(health);
                } else if (creatureType == CreatureType.MONSTER) {
                    if (souls.get(CreatureType.MONSTER) > 4) {
                        creature = new SpawnGiant(centerLocation).execute();
                        health = -1;
                        noMount = true;
                    } else
                        creature = new SpawnSkeleton(centerLocation).execute();
                } else if ((souls.containsKey(CreatureType.CHICKEN))
                        && (souls.containsKey(CreatureType.COW))
                        && (souls.containsKey(CreatureType.PIG))
                        && (souls.containsKey(CreatureType.SHEEP))) {
                    creature = new SpawnMonster(centerLocation).execute();
                    health = -1;
                }
                else if ((creatureType == CreatureType.PIG) || (creatureType == CreatureType.PIG_ZOMBIE)) {
                    creature = new SpawnPigZombie(centerLocation).execute();
                    health = 6;
                }
                else if ((creatureType == CreatureType.CHICKEN) || (creatureType == CreatureType.ZOMBIE))
                    creature = new SpawnZombie(centerLocation).execute();
                else if ((creatureType == CreatureType.COW) || (creatureType == CreatureType.SHEEP) || (creatureType == CreatureType.SKELETON))
                    creature = new SpawnSkeleton(centerLocation).execute();
                else if ((souls.containsKey(CreatureType.GIANT)) && (souls.containsKey(CreatureType.CREEPER))) {
                    creature = new SpawnGhast(centerLocation).execute();
                    health = -1;
                    noMount = true;
                } else if (souls.containsKey(CreatureType.GIANT)) {
                    health = -1;
                    creature = new SpawnMonster(centerLocation).execute();
                    for (int index = 0; index < 4; index++)
                        new SpawnMonster(centerLocation).execute().setHealth(health);
                } else if ((creatureType == CreatureType.WOLF) || (creatureType == CreatureType.CREEPER))
                    creature = new SpawnCreeper(centerLocation).execute();
                else if ((creatureType == CreatureType.SQUID) || (creatureType == CreatureType.SLIME)) {
                    int size = 0;
                    if (souls.containsKey(CreatureType.SQUID))
                            size += souls.get(CreatureType.SQUID);
                    if (souls.containsKey(CreatureType.SLIME))
                            size += souls.get(CreatureType.SLIME);
                    if (size == 3)
                        size = 4;
                    if (size > 4)
                        size = 4;
                    health = -1;
                    creature = new SpawnSlime(centerLocation, size).execute();
                    noMount = true;
                }
                if ((creature != null) && (health > 0))
                    creature.setHealth(health);
                if (souls.containsKey(CreatureType.SPIDER) && !noMount) {
                        Spider spider = new SpawnSpider(centerLocation).execute();
                        spider.setHealth(3);
                        spider.setPassenger(creature);
                }
                smokeEffect(block.getRelative(BlockFace.UP), world, 5);
            }
            souls.clear();
            soulRunes.remove(centerLocation);
        }
    }
    
    public void smokeEffect(Block centerBlock, World world, int thickness) {
        world.playEffect(centerBlock.getLocation(), Effect.EXTINGUISH, 32);
        for (int index = 0; index < thickness; index++) {
            world.playEffect(centerBlock.getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.NORTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.EAST).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.SOUTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.WEST).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.UP).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.DOWN).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.EAST).getRelative(BlockFace.NORTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.UP).getRelative(BlockFace.EAST).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.WEST).getRelative(BlockFace.SOUTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.UP).getRelative(BlockFace.WEST).getLocation(), Effect.SMOKE, 31);
            world.playEffect(centerBlock.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getLocation(), Effect.SMOKE, 31);
        }
    }
    
    @Override
    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
        if (MagicRituals.soulCollectionPoints.containsKey(event.getBlock().getLocation())) {
            event.setNewCurrent(event.getOldCurrent());
        }
    }
    
    @Override
    public void onBlockPhysics(BlockPhysicsEvent event) {

    }
    
    public Object maxKeyByValue(HashMap map) {
        double maxValue = 0;
        Object maxKey = null;
        
        for (Object key : map.keySet()) {
            Object rawValue = map.get(key);
            if (rawValue instanceof Number) {
                Double value = Double.parseDouble(rawValue.toString());
                if (value > maxValue) {
                    maxValue = value;
                    maxKey = key;
                }
            }
        }
        return maxKey;
    }
}