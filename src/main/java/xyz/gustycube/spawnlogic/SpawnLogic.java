package xyz.gustycube.spawnlogic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import redempt.redlib.config.ConfigManager;
import redempt.redlib.config.annotations.ConfigMappable;
import redempt.redlib.config.annotations.ConfigName;

import org.bukkit.configuration.file.FileConfiguration;
import java.util.logging.Level;

public final class SpawnLogic extends JavaPlugin implements Listener {
    public static SpawnLogic instance;

    FileConfiguration config = getConfig();

    @ConfigMappable
    public class cls {
        @ConfigName("CSME (Custom Spawn Message Enabler) Configuration Settings")
        public boolean CSME = false;
        public String CSM = "This is an Default CSME Message on Player Join. Please set it to your own.";

        public boolean VPS = true;
    }

    @Override
    public void onEnable() {
        ConfigManager config = ConfigManager.create(this).target(SpawnLogic.class).saveDefaults().load();

        /** PLUGIN BASIC STARTUP */
        instance = this;
        this.getLogger().log(Level.INFO, "SpawnLogic Enabled");
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new onClickGUI(), this);

    }

    @Override
    public void onDisable() {
        /** PLUGIN BASIC SHUTDOWN */
        this.getLogger().log(Level.INFO, "SpawnLogic Disabled");

    }

    @EventHandler
    public void onplayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskTimer(SpawnLogic.instance, () -> { } , 0, 60L);
        int playerPing = e.getPlayer().getPing();
        Player player = e.getPlayer();
        boolean CSME = config.getBoolean("CSME");
        String CSM = config.getString("CSM");
        boolean VPS = config.getBoolean("VPS");
        
        if (CSME == true) {
            player.sendMessage(CSM);
        }

        if (VPS == true) {
            if (playerPing > 200) {
                player.sendMessage("Unstable Connection to RBL Network. For more Information, please visit rebornedlife.github.io/docs");
    
                /**
                 * PATCHES HIGH PING ISSUE
                 * 
                 * This code basicially creates an GUI and runs
                 * it at the start of the player join. Which causes
                 * alot of lag, so for In testing we will disable
                 * it to see if anything improves. If so it will
                 * be permantly disabled.
                 * 
                 * Bukkit.getScheduler().runTask(this, () -> {
                    Inventory inv = Bukkit.createInventory(player, 27, ChatColor.RED.toString() + ChatColor.BOLD + "You have high ping!");
                    // LEAVE BUTTON
                    ItemStack leave = new ItemStack(Material.RED_DYE);
                    ItemMeta meta = leave.getItemMeta();
                    meta.setDisplayName(ChatColor.RED + "Leave the game");
                    meta.setLore(Arrays.asList("You should rejoin with a VPN!"));
                    leave.setItemMeta(meta);
    
                    inv.setItem(12, leave);
    
                    // CONTINUE
                    ItemStack continueButton = new ItemStack(Material.GREEN_DYE);
                    ItemMeta continueMeta = leave.getItemMeta();
                    continueMeta.setDisplayName(ChatColor.RED + "Continue");
                    continueMeta.setLore(Arrays.asList("You may experience lag, world generation glitches, and more!"));
                    continueButton.setItemMeta(continueMeta);
    
                    inv.setItem(14, continueButton);
    
                    // BACKGROUND
                    ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemMeta backgroundMeta = background.getItemMeta();
                    backgroundMeta.setDisplayName("\n");
                    background.setItemMeta(backgroundMeta);
    
                    for (int i : new int[]{0,1,2,3,4,5,6,7,8,9,10,11,13,15,16,17,18,19,20,21,22,23,24,25,26}) {
                        inv.setItem(i, background);
                    }
                    player.openInventory(inv);
                });
                 * 
                 */
                
            }
        }
    }



}
