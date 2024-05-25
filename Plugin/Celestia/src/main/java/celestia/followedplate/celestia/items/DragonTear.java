package celestia.followedplate.celestia.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class DragonTear {
    public static final ItemStack dragonTear = createDragonTear();

    public static void init() {
    }

    public static ItemStack createDragonTear() {
        ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "Dragon Tear");
        im.setCustomModelData(13601);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        im.getPersistentDataContainer().set(Keys.DRAGON_TEAR, PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(Keys.CELESTIA_ITEM, PersistentDataType.BOOLEAN, true);

        item.setItemMeta(im);
        ShapedRecipe sr = new ShapedRecipe(Keys.DRAGON_TEAR, item);
        sr.shape("   ", "   ", "   ");
        sr.setIngredient('X', Material.GOLD_BLOCK);
        sr.setIngredient('Y', Material.TOTEM_OF_UNDYING);
        //SmithingTransformRecipe smtr = new SmithingTransformRecipe(NamespacedKey.minecraft("netherite_katana"), item, new RecipeChoice.MaterialChoice(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE), new RecipeChoice.ExactChoice(etherealRapier), new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT));
        try {
            Bukkit.addRecipe(sr);
            //Bukkit.addRecipe(smtr);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Recipe works");
        } catch (IllegalStateException localIllegalStateException) {
        }
        return item;
    }

    public static void ability(PlayerInteractEvent e) {
        e.getItem().subtract(1);
        e.getPlayer().getWorld().setStorm(true);
    }
}
