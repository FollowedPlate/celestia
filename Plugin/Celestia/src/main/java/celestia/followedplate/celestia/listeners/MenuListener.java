package celestia.followedplate.celestia.listeners;

import celestia.followedplate.celestia.Celestia;
import celestia.followedplate.celestia.menusystem.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {
    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof Menu menu) menu.handleMenu(e);
    }
}
