package xyz.gustycube.spawnlogic;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onClickGUI implements Listener {

    @EventHandler
    public  void onClick(InventoryClickEvent e) {

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "You have high ping!")
           && e.getCurrentItem() != null) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            switch (e.getRawSlot()) {
                case 12: // Leave
                    player.kickPlayer("Chose to leave. Please rejoin with a VPN or fixed internet!");
                    return;
                case 14: // Continue
                    player.sendMessage("You have chosen to stay, beware of the glitches that may occur!");
                    break;
                default:
                    return;
            }
            player.closeInventory();





        }


    }

}
