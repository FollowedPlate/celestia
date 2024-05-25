package celestia.followedplate.celestia.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class AmuletOfUndying {
    public static final ItemStack amuletOfUndying = createAmuletOfUndying();
    public static void init(){}
    public static ItemStack createAmuletOfUndying() {
        ItemStack item = new ItemStack(Material.IRON_NUGGET, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "Amulet of Undying");
        im.setCustomModelData(13600);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        im.getPersistentDataContainer().set(Keys.AMULET_OF_UNDYING, PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(Keys.CELESTIA_ITEM, PersistentDataType.BOOLEAN, true);

        item.setItemMeta(im);
        ShapedRecipe sr = new ShapedRecipe(Keys.AMULET_OF_UNDYING, item);
        sr.shape("YXY", "XYX", "YXY");
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
}
