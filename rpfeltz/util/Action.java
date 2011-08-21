package rpfeltz.util;

import java.util.TimerTask;

/**
 *
 * @author rpfeltz
 */
public abstract class Action extends TimerTask {
    @Override
    public void run() { this.execute(); }
    public Object execute() { return null; }
}
