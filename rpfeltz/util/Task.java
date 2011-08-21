/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.util;

import java.util.TimerTask;
import rpfeltz.magicrituals.EntitySpirit;
/**
 *
 * @author Roderick
 */
public class Task extends TimerTask {
    private EntitySpirit spirit;

    public Task(EntitySpirit spirit) {
        this.spirit = spirit;
    }

    @Override
    public void run() {
        if (!spirit.isDead()) {
            spirit.mainLoop();
            Delay.timer(new Task(spirit), 100);
        }
    }
}
