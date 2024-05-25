package celestia.followedplate.celestia.listeners;

import celestia.followedplate.celestia.items.EtherealRapier;
import celestia.followedplate.celestia.items.Keys;
import celestia.followedplate.celestia.items.CarminiteReaper;
import celestia.followedplate.celestia.items.StarlightSaber;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerAttackListener implements Listener {
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player player ) {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
                PersistentDataContainer persistentDataContainer = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
                if (persistentDataContainer.has(Keys.CELESTIA_ITEM)) {
                    if (persistentDataContainer.has(Keys.ETHEREAL_RAPIER)) EtherealRapier.ability(e);
                    if (persistentDataContainer.has(Keys.STARLIGHT_SABER)) StarlightSaber.ability(e);
                    if (persistentDataContainer.has(Keys.SCARLET_DEVIL)) CarminiteReaper.ability(e);

                }
            }
        }
    }
}
