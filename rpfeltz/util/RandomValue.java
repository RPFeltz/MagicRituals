/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpfeltz.util;

import java.util.HashMap;
import java.util.Random;

/**
 * This is a library for getting random values from a list.
 * @author rpfeltz
 */
public class RandomValue {
    public static HashMap<Object, Object[]> lists = new HashMap<Object, Object[]>();
    public static final Random random = new Random();
    /** 
     * Possible results for mehtods.
    */
    public enum Result {NO_OVERRIDE_EXCEPTION, SUCCESSFUL};
      
    /** 
     * Add a list of values which will be stored with a key.
     * @param key The key of the list.
     * @param list The list to be stored.
     * @return Result of method.
    */
    public static Result addList(Object key, Object[] list) {
        if (lists.containsKey(key))
            return Result.NO_OVERRIDE_EXCEPTION;
        lists.put(key, list);
        return Result.SUCCESSFUL;
    }
    
    /** 
     * Add a list of values which will be stored with a key.
     * @param key The key of the list.
     * @param list The list to be stored.
     * @param override Allow overriding.
     * @return Result of method.
    */
    public static Result addList(Object key, Object[] list, boolean override) {
        lists.put(key, list);
        return Result.SUCCESSFUL;
    }
    
    /** 
     * Get a random value from a stored list.
     * @param key The key of the list.
     * @return Random value of the list.
    */
    public static Object getMessage(Object key) {
        Object[] list = lists.get(key);
        if (list == null) {
            // Add exception handling stuff here.
            return null;
        }
        return list[random.nextInt(list.length)];
    }
}