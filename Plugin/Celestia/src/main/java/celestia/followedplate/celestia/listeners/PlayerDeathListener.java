package celestia.followedplate.celestia.listeners;

import celestia.followedplate.celestia.commands.DuelCommand;
import celestia.followedplate.celestia.items.AmuletOfUndying;
import celestia.followedplate.celestia.items.Keys;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        for (ItemStack item : player.getInventory()) {
            if (item == null) {
                continue;
            }
            PersistentDataContainer persistentDataContainer = item.getItemMeta().getPersistentDataContainer();
            if (persistentDataContainer.has(Keys.CELESTIA_ITEM)) {
                if (persistentDataContainer.has(Keys.AMULET_OF_UNDYING)) {
                    e.setCancelled(true);
                    player.setHealth(1.0);
                    item.subtract(1);
                    player.playEffect(EntityEffect.TOTEM_RESURRECT);
                    break;
                }
            }
        }
        if (DuelCommand.playerLocationHashMap.containsKey(player)) {
            e.setCancelled(true);
            e.setReviveHealth(10);
        }


    }
}
