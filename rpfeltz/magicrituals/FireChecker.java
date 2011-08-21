package rpfeltz.magicrituals;

import rpfeltz.util.RandomValue;
import rpfeltz.util.Action;
import rpfeltz.util.Delay;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author rpfeltz
 */

public class FireChecker extends Action {
    private final LivingEntity entity;
    private final Player owner;
    
    public FireChecker(LivingEntity entity, Player owner) {
        this.entity = entity;
        this.owner = owner;
    }
    
    @Override
    public Object execute() {
        if ((entity.getFireTicks() > 0)
                && (!entity.isDead())) {
            Delay.timer(new FireChecker(entity, owner), 100);
        } else if (!entity.isDead()) {
            MagicRituals.burning.remove(entity);
            owner.sendMessage(ChatColor.DARK_GRAY + (String)RandomValue.getMessage("PET_STOP_FIRE"));
        }
        return null;
    }
}
