package rpfeltz.magicrituals;

import rpfeltz.util.RandomValue;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * Magic Rituals plugin for Bukkit
 *
 * @author rpfeltz
 */
public class MagicRituals extends JavaPlugin {
    public static final Random random = new Random();
    private static final Logger log = Logger.getLogger("Minecraft");
    private final MagicRitualsPlayerListener playerListener = new MagicRitualsPlayerListener(this);
    private final MagicRitualsBlockListener blockListener = new MagicRitualsBlockListener(this);
    private final MagicRitualsEntityListener entityListener = new MagicRitualsEntityListener(this);
    public static HashMap<Entity, Player> owners = new HashMap<Entity, Player>();
    public static HashMap<Entity, Boolean> burning = new HashMap<Entity, Boolean>();
    public static HashMap<Location, HashMap<CreatureType, Integer>> soulCollectionPoints = new HashMap<Location, HashMap<CreatureType, Integer>>();
    private PluginDescriptionFile pdfFile;
    private String PluginName = "";
    
    @Override
    public void onDisable() {
        log.info("Disabling " + PluginName + "!");
        /*HashMap<String, Object> allObjects = new HashMap<String, Object>();
        allObjects.put("owners", owners);
        allObjects.put("burning", burning);
        allObjects.put("soulCollectionPoints", soulCollectionPoints);
        try {
            SLAPI.save(allObjects, "data.txt");
        } catch (Exception exception) {
            if (Prompt.get(MagicRituals.class.getName() + " " + exception.getClass().getName() + ":\nLog stack trace [Y/N]?", Prompt.YesNo).equals("y")) {
                Logger.getLogger(MagicRituals.class.getName()).log(Level.SEVERE, null, exception);
            }
        }*/
    }

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Priority.Lowest, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.Lowest, this);
        pm.registerEvent(Event.Type.BLOCK_PHYSICS, blockListener, Priority.Lowest, this);
        pm.registerEvent(Event.Type.REDSTONE_CHANGE, blockListener, Priority.Lowest, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Low, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, playerListener, Priority.Low, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Priority.Lowest, this);
        pm.registerEvent(Event.Type.ENTITY_TARGET, entityListener, Priority.Lowest, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Lowest, this);
        pdfFile = this.getDescription();
        PluginName = pdfFile.getName();
        
        // Credit to MegaBlox for the phrases.
        String[] PetHurtByOwnerMessages =  {"Ow...", "That hurts!", "Hey!",
            "Owch!", "Please stop!", "You know it hurts?",
            "Did I do anything bad to you? Please forgive me if so!",
            "Don't hit me again!", "What did I do?", "Please don't hit me?",
            "I hope that was an accident...?", "Why are you doing this?",
            "No, stop!", "Don't hit me!", "Ouch!", "Ow!", "Ouch...",
            "Owch..."};
        String[] PetKilledByOwnerMessages = {"You... killed me...",
            "AAAAUGH!!!", "Why, my master... Ugh...", "Why me...",
            "Goodbye world...", "Humans are such cruel creatures...",
            "GYAHHHH!!!", "Why did you kill me...?",
            "No way... Why did you do... AAARRGH!!!"};
        String[] PetDiedMessages = {"AAAAUGH!!!", "Live on...",
            "Farewell... My master.", "Goodbye world...", "GYAHHHH!!!",
            "It's a cruel world...", "Why... me... AAGH!!!", "Goodbye...",
            "AAARGH!!!"};
        String[] PetTargetMessages = {"Come at me bro!", "Bring it on!",
            "I fight for my friends.", "Take this!", "You coward!",
            "THIS is what pain feels like!", "I have no sympathy for you."};
        String[] PetBurnMessages = {"No, it burns!", "AAAGH FIRE!!!",
            "I'm on fire!", "Water! Bring some water, quick!",
            "Help! I'm on fire!", "I need water!", "Master, get water, quick!",
            "FIRE!!!"};
        String[] PetStopFireMessages = {
            "The burning stopped, finally. That hurt!",
            "I hope I never catch fire again.", "That was terrible."};
        String[] PetMountedMessages = {"Wanna have a ride?",
            "Wanna get on my back?", "How may I serve you?", "Hello, master.",
            "You're heavy...", "Where would you like to go?",
            "Let's walk around for a bit.", "Isn't this like... spider abuse?",
            "Have a nice ride, master."};
        String[] PetDismountedMessages = {"Tired of sitting?",
            "I am glad to have served you.", "You're welcome, master.",
            "Come again.", "You're welcome.", "So, what would you like to do?"};
        
        RandomValue.addList("PET_HURT_BY_OWNER", PetHurtByOwnerMessages);
        RandomValue.addList("PET_KILLED_BY_OWNER", PetKilledByOwnerMessages);
        RandomValue.addList("PET_DIED", PetDiedMessages);
        RandomValue.addList("PET_TARGET", PetTargetMessages);
        RandomValue.addList("PET_BURN", PetBurnMessages);
        RandomValue.addList("PET_STOP_FIRE", PetStopFireMessages);
        RandomValue.addList("PET_MOUNTED", PetMountedMessages);
        RandomValue.addList("PET_DISMOUNTED", PetDismountedMessages);
        
        log.info(PluginName + " version " + pdfFile.getVersion() + " is enabled!");
    }
}

