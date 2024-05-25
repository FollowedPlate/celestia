package celestia.followedplate.celestia.menusystem.menus;

import celestia.followedplate.celestia.Celestia;
import celestia.followedplate.celestia.items.Keys;
import celestia.followedplate.celestia.menusystem.Menu;
import celestia.followedplate.celestia.menusystem.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EnchantingTableMenu extends Menu {
    public EnchantingTableMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.AQUA + "Enchanting Table";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        ItemStack cur = e.getCurrentItem();
        if (!(cur == null) && cur.getItemMeta().getPersistentDataContainer().has(Keys.CELESTIA_MENU_ITEM))
            e.setCancelled(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Celestia.getInstance(), () -> {
            ItemStack enchantee = inv.getItem(13);
            if (!(enchantee==null)) {
                inventory.setItem(38, enchantee);
                inventory.setItem(40, enchantee);
                inventory.setItem(42, enchantee);

                inventory.setItem(47, super.makeItem(Material.GREEN_DYE, null));
                inventory.setItem(49, super.makeItem(Material.GREEN_DYE, null));
                inventory.setItem(51, super.makeItem(Material.GREEN_DYE, null));
            } else {
                inventory.setItem(38, super.makeItem(Material.BARRIER, null));
                inventory.setItem(40, super.makeItem(Material.BARRIER, null));
                inventory.setItem(42, super.makeItem(Material.BARRIER, null));

                inventory.setItem(47, super.makeItem(Material.RED_DYE, null));
                inventory.setItem(49, super.makeItem(Material.RED_DYE, null));
                inventory.setItem(51, super.makeItem(Material.RED_DYE, null));
            }
        }, 0);
    }

    public ItemStack rng(ItemStack root) {



        return root;
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(38, super.makeItem(Material.BARRIER, null));
        inventory.setItem(40, super.makeItem(Material.BARRIER, null));
        inventory.setItem(42, super.makeItem(Material.BARRIER, null));

        inventory.setItem(47, super.makeItem(Material.RED_DYE, null));
        inventory.setItem(49, super.makeItem(Material.RED_DYE, null));
        inventory.setItem(51, super.makeItem(Material.RED_DYE, null));
        setFillerGlass();
        inventory.setItem(13, new ItemStack(Material.AIR));
    }
}
