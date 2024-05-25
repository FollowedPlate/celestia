package celestia.followedplate.celestia.listeners;

import celestia.followedplate.celestia.items.AstronEdge;
import celestia.followedplate.celestia.items.DragonTear;
import celestia.followedplate.celestia.items.Keys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ClickListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (e.getAction().isRightClick() && e.hasItem()) {
            ItemStack item = e.getItem();
            if (!(item==null)) {
                PersistentDataContainer persistentDataContainer = item.getItemMeta().getPersistentDataContainer();
                if (persistentDataContainer.has(Keys.CELESTIA_ITEM)) {
                    if (persistentDataContainer.has(Keys.ASTRON_EDGE) && Boolean.TRUE.equals(persistentDataContainer.get(Keys.ASTRON_EDGE, PersistentDataType.BOOLEAN)))
                        AstronEdge.ability(e);
                    if (persistentDataContainer.has(Keys.DRAGON_TEAR)) DragonTear.ability(e);
                }
            }
        }
    }
}
