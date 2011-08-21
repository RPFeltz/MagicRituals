/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author rpfeltz
 */
public class Delay {
    public static void timer(Action action, int delay) {
        Timer timer = new Timer();
        timer.schedule(action, delay);
    }

    public static void timer(TimerTask action, int delay) {
        Timer timer = new Timer();
        timer.schedule(action, delay);
    }
}
