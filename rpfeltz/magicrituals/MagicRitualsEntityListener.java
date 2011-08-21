package rpfeltz.magicrituals;

import rpfeltz.util.RandomValue;
import rpfeltz.util.Delay;
import org.bukkit.ChatColor;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.util.Vector;

/**
 * MagicRituals block listener
 * @author rpfeltz
 */
public class MagicRitualsEntityListener extends EntityListener {
    private final MagicRituals plugin;

    public MagicRitualsEntityListener(final MagicRituals plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        Entity rawentity = event.getEntity();
        if (!(rawentity instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) rawentity;
        CreatureType creatureType = CreatureType.fromName(rawentity.getClass().getSimpleName().replaceFirst("Craft", ""));
        if (entity instanceof Player)
            creatureType = CreatureType.MONSTER;
        // Spawn spirit.
        if (creatureType != null) {
            EntitySpirit entitySpirit = new EntitySpirit(entity.getWorld(),
                    entity.getLocation(),
                    creatureType,
                    100);
            entitySpirit.setVelocity(new Vector(0, 0.5, 0));
        }
        
        if (MagicRituals.owners.get(entity) != null)
                MagicRituals.owners.remove(entity);
    }
    
    @Override
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.isCancelled()) return;
        Entity entity = event.getEntity();
        Entity target = event.getTarget();
        Player owner = MagicRituals.owners.get(entity);
        if (owner != null)
            if (owner == target)
                event.setCancelled(true);
            else if (target != null)
                owner.sendMessage(ChatColor.GRAY + (String)RandomValue.getMessage("PET_TARGET"));
    }
    
    @Override
    public void onEntityDamage(EntityDamageEvent rawevent) {
        if (!(rawevent.getEntity() instanceof LivingEntity)) return;
        final LivingEntity entity = (LivingEntity)rawevent.getEntity();
        if (entity.isDead()) return;
        
        final Player owner = MagicRituals.owners.get(entity);
        if (owner == null) return;

        int health = entity.getHealth() - rawevent.getDamage();
        if (rawevent instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent)rawevent;
            Entity damager = event.getDamager();
            if (owner == damager) {
                if (health <= 0) {
                    owner.sendMessage(ChatColor.DARK_RED + (String)RandomValue.getMessage("PET_KILLED_BY_OWNER"));
                    MagicRituals.owners.remove(entity);
                    return;
                } else {
                    owner.sendMessage(ChatColor.DARK_GRAY + (String)RandomValue.getMessage("PET_HURT_BY_OWNER"));
                    return;
                }
            }
        }
        if (health <= 0) {
            owner.sendMessage(ChatColor.DARK_RED + (String)RandomValue.getMessage("PET_DIED"));
            MagicRituals.owners.remove(entity);
            return;
        }
        int fireTicks = entity.getFireTicks();
        if (fireTicks > 0
                && !MagicRituals.burning.containsKey(entity)) {
            MagicRituals.burning.put(entity, true);
            owner.sendMessage(ChatColor.RED + (String)RandomValue.getMessage("PET_BURN"));
            Delay.timer(new FireChecker(entity, owner), 100);
            return;
        }
    }
}