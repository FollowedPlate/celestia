package celestia.followedplate.celestia.items;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CarminiteReaper {
    public static final ItemStack carminiteReaper = createCarminiteReaper();
    public static HashMap<Player, Long> playerTimerMap = new HashMap<>();

    public static ItemStack createCarminiteReaper() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.DARK_RED + "Carminite Reaper");
        im.setCustomModelData(13015);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        im.getPersistentDataContainer().set(Keys.SCARLET_DEVIL, PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(Keys.CELESTIA_ITEM, PersistentDataType.BOOLEAN, true);

        List<String> lore = new ArrayList();
//        lore.add(ChatColor.ITALIC + "" + ChatColor.DARK_PURPLE + "Feeds on the blood");
//        lore.add(ChatColor.ITALIC + "" + ChatColor.DARK_PURPLE + "of your foes");
        double attackDamage = Celestia.getConfiguration().getDouble("celestiaWeapons.carminiteReaper.attack-damage");
        double attackSpeed = Celestia.getConfiguration().getDouble("celestiaWeapons.carminiteReaper.attack-speed");
        lore.add(ChatColor.GRAY + "When in Main Hand:");
        lore.add(ChatColor.DARK_GREEN + " " + attackDamage + " Attack Damage");
        lore.add(ChatColor.DARK_GREEN + " " + attackSpeed + " Attack Speed");
        im.setLore(lore);
        AttributeModifier speed = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -4.0D + attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speed);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
        item.setItemMeta(im);
//        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ethereal_rapier"), item);
//        sr.shape("  X", " Y ", "Z  ");
//        sr.setIngredient('X', Material.DIAMOND);
//        sr.setIngredient('Y', Material.DIAMOND_BLOCK);
//        sr.setIngredient('Z', Material.STICK);
//        //SmithingTransformRecipe smtr = new SmithingTransformRecipe(NamespacedKey.minecraft("netherite_katana"), item, new RecipeChoice.MaterialChoice(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE), new RecipeChoice.ExactChoice(etherealRapier), new RecipeChoice.MaterialChoice(Material.NETHERITE_INGOT));
//        try {
//            Bukkit.addRecipe(sr);
//            //Bukkit.addRecipe(smtr);
//            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Recipe works");
//        } catch (IllegalStateException localIllegalStateException) {
//        }
        return item;
    }

    public static void ability(EntityDamageByEntityEvent e) {
        try {
            if (e.getDamager() instanceof Player player) {
                if (!playerTimerMap.containsKey(player)) {
                    playerTimerMap.put(player, System.currentTimeMillis() - 5001);
                }
                long time = System.currentTimeMillis() - playerTimerMap.get(player);

                if (time > 5000) {
                    if (e.getEntity() instanceof LivingEntity livingEntity) {
                        double health = livingEntity.getHealth();
                        double healthAfter = health - Celestia.getConfiguration().getDouble("celestiaWeapons.carminiteReaper.health-steal");
                        double playerHealth = player.getHealth();
                        if (healthAfter >= 0) {
                            livingEntity.setHealth(healthAfter);
                        } else {
                            livingEntity.setHealth(0.0);
                        }
                        playerHealth += health - livingEntity.getHealth();
                        if (playerHealth <= player.getHealthScale()) {
                            player.setHealth(playerHealth);
                        } else {
                            player.setHealth(player.getHealthScale());
                        }
                    }
                }

            }
        } catch (NullPointerException ex) {
        }
    }
}