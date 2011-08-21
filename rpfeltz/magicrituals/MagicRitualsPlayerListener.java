package rpfeltz.magicrituals;

import rpfeltz.util.RandomValue;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

/**
 * Handle events for all Player related events
 * @author rpfeltz
 */
public class MagicRitualsPlayerListener extends PlayerListener {
    private final MagicRituals plugin;

    public MagicRitualsPlayerListener(MagicRituals instance) {
        plugin = instance;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.isCancelled()) return;
        Block block = event.getClickedBlock();
        ItemStack itemstack = event.getItem();
        
        // Rune of Slime
        if ((block.getTypeId() == 83)
                && (itemstack.getTypeId() == 341)) {
            while (block.getRelative(BlockFace.UP).getTypeId() == 83) {
                block = block.getRelative(BlockFace.UP);
            }
            Block topblock = block;
            int size = 0;
            while (block.getTypeId() == 83) {
                size++;
                block = block.getRelative(BlockFace.DOWN);
            }
            if (((block.getRelative(BlockFace.NORTH).getTypeId() == 8)
                    || (block.getRelative(BlockFace.NORTH).getTypeId() == 9))
                    && ((block.getRelative(BlockFace.EAST).getTypeId() == 8)
                    || (block.getRelative(BlockFace.EAST).getTypeId() == 9))
                    && ((block.getRelative(BlockFace.SOUTH).getTypeId() == 8)
                    || (block.getRelative(BlockFace.SOUTH).getTypeId() == 9))
                    && ((block.getRelative(BlockFace.WEST).getTypeId() == 8)
                    || (block.getRelative(BlockFace.WEST).getTypeId() == 9))
                    && ((block.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getTypeId() == 8)
                    || (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getTypeId() == 9))
                    && ((block.getRelative(BlockFace.EAST).getRelative(BlockFace.EAST).getTypeId() == 8)
                    || (block.getRelative(BlockFace.EAST).getRelative(BlockFace.EAST).getTypeId() == 9))
                    && ((block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH).getTypeId() == 8)
                    || (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH).getTypeId() == 9))
                    && ((block.getRelative(BlockFace.WEST).getRelative(BlockFace.WEST).getTypeId() == 8)
                    || (block.getRelative(BlockFace.WEST).getRelative(BlockFace.WEST).getTypeId() == 9))
                    && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).getTypeId() == 82)
                    && (block.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST).getTypeId() == 82)
                    && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST).getTypeId() == 82)
                    && (block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST).getTypeId() == 82)) {
                size = Math.min(size, 3);
                if (size == 3)
                    size = 4;
                if (itemstack.getAmount() < size)
                    size = itemstack.getAmount();
                if (itemstack.getAmount() == 3)
                    size = 2;
                if (itemstack.getAmount() <= size)
                    event.getPlayer().getInventory().remove(itemstack);
                else
                    itemstack.setAmount(itemstack.getAmount() - size);
                while (topblock.getTypeId() == 83) {
                    topblock.setTypeId(0);
                    topblock = topblock.getRelative(BlockFace.DOWN);
                }
                block.getRelative(BlockFace.NORTH).setTypeId(0);
                block.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).setTypeId(0);
                block.getRelative(BlockFace.EAST).setTypeId(0);
                block.getRelative(BlockFace.EAST).getRelative(BlockFace.EAST).setTypeId(0);
                block.getRelative(BlockFace.SOUTH).setTypeId(0);
                block.getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH).setTypeId(0);
                block.getRelative(BlockFace.WEST).setTypeId(0);
                block.getRelative(BlockFace.WEST).getRelative(BlockFace.WEST).setTypeId(0);
                new SpawnSlime(block.getLocation().add(0, 1, 0), size).execute();
                event.setCancelled(true);
            }
        }
    }
    
    @Override
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        Entity passenger = entity.getPassenger();
        if (entity.getPassenger() == player) {
            player.sendMessage(ChatColor.DARK_GRAY + (String)RandomValue.getMessage("PET_DISMOUNTED"));
            entity.eject();
        } else if ((passenger == null)
                && (MagicRituals.owners.get(entity) == player)) {
            player.sendMessage(ChatColor.DARK_GRAY + (String)RandomValue.getMessage("PET_MOUNTED"));
            entity.setPassenger(player);
        }
    }
}