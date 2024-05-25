package celestia.followedplate.celestia.menusystem.menus;

import celestia.followedplate.celestia.items.*;
import celestia.followedplate.celestia.menusystem.Menu;
import celestia.followedplate.celestia.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponsMenu extends Menu {

    public WeaponsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.AQUA + "Celestia Weapons Menu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        try {
            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);
            if (!e.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)) {
                if (player.hasPermission("celestia.giveitems")) {
                    player.getInventory().addItem(inventory.getItem(e.getSlot()));
                } else {
                    player.sendMessage("Nuh uh");
                }
            }
        } catch (java.lang.NullPointerException ignored) {
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack etherealRapier = EtherealRapier.etherealRapier;
        ItemStack astronEdge = AstronEdge.astronEdge;
        ItemStack starlightSaber = StarlightSaber.starlightSaber;
        ItemStack carminiteReaper = CarminiteReaper.carminiteReaper;
        inventory.setItem(10, etherealRapier);
        inventory.setItem(12, astronEdge);
        inventory.setItem(14, starlightSaber);
        inventory.setItem(16, carminiteReaper);
        for (int i = 27; i < 54; i++) {
            inventory.setItem(i, RNJesus.next(10));
        }
        setFillerGlass();
    }

}
