package xyz.gustycube.spawnlogic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Level;

public final class SpawnLogic extends JavaPlugin implements Listener {
    public static SpawnLogic instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.getLogger().log(Level.INFO, "SpawnLogic Enabled");
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new onClickGUI(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().log(Level.INFO, "SpawnLogic Disabled");

    }

    @EventHandler
    public void onplayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskTimer(SpawnLogic.instance, () -> { } , 0, 60L);
        int playerPing = e.getPlayer().getPing();
        Player player = e.getPlayer();
        if (playerPing > 200) {
            Bukkit.getScheduler().runTask(this, () -> {
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
        }
    }



}
