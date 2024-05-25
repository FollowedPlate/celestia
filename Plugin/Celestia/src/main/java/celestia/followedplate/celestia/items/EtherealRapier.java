package celestia.followedplate.celestia.items;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EtherealRapier {
    public static final ItemStack etherealRapier = createEtherealRapier();
    public static ItemStack createEtherealRapier() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.AQUA + "Ethereal " + ChatColor.DARK_RED + "Rapier");
        im.setCustomModelData(13010);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        im.getPersistentDataContainer().set(Keys.ETHEREAL_RAPIER, PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(Keys.ETHEREAL_RAPIER_CHARGED, PersistentDataType.BOOLEAN, false);
        im.getPersistentDataContainer().set(Keys.CELESTIA_ITEM, PersistentDataType.BOOLEAN, true);

        List<String> lore = new ArrayList();
//        lore.add(ChatColor.ITALIC +""+ ChatColor.DARK_PURPLE + "Harnesses the cosmos,");
//        lore.add(ChatColor.ITALIC +""+ ChatColor.DARK_PURPLE + "tears space and time");
        double attackDamage = Celestia.getConfiguration().getDouble("celestiaWeapons.etherealRapier.attack-damage");
        double attackSpeed = Celestia.getConfiguration().getDouble("celestiaWeapons.etherealRapier.attack-speed");
        double armorToughness = Celestia.getConfiguration().getDouble("celestiaWeapons.etherealRapier.armor-toughness");
        lore.add(ChatColor.GRAY + "When in Main Hand:");
        lore.add(ChatColor.DARK_GREEN + " " + attackDamage + " Attack Damage");
        lore.add(ChatColor.DARK_GREEN + " " + attackSpeed + " Attack Speed");
        lore.add(ChatColor.BLUE + "+" + armorToughness + " Armor Toughness");
        im.setLore(lore);
        AttributeModifier speed = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -4.0D + attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier armor_toughness = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", Celestia.getConfiguration().getDouble("celestiaWeapons.etherealRapier.armor-toughness"), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier armor_toughness2 = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", Celestia.getConfiguration().getDouble("celestiaWeapons.etherealRapier.armor-toughness"), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);

        im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speed);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);

        im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armor_toughness);
        im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armor_toughness2);
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
    public static HashMap<Player, Long> playerTimerMap = new HashMap<>();
    public static void ability(EntityDamageByEntityEvent e) {
        try {
            if (e.getDamager() instanceof Player player) {
                PersistentDataContainer persistentDataContainer = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
                if (persistentDataContainer.get(Keys.ETHEREAL_RAPIER_CHARGED, PersistentDataType.BOOLEAN)) {
                    if (e.getEntity() instanceof LivingEntity livingEntity) {
                        double health = livingEntity.getHealth();
                        double healthAfter = health - Celestia.getConfiguration().getDouble("celestiaWeapons.etherealRapier.special-attack-damage");
                        if (healthAfter >= 0) {
                            livingEntity.setHealth(healthAfter);
                        } else {
                            livingEntity.setHealth(0.0);
                        }
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Celestia.getInstance(), () -> {
                            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
                            im.getPersistentDataContainer().set(Keys.ETHEREAL_RAPIER_CHARGED, PersistentDataType.BOOLEAN, false);
                            im.setCustomModelData(13010);
                            player.getInventory().getItemInMainHand().setItemMeta(im);
                        }, 1);
                    }
                } else {
                    if (!playerTimerMap.containsKey(player)) {
                        playerTimerMap.put(player, System.currentTimeMillis() - 5001);
                    }
                    long time = System.currentTimeMillis() - playerTimerMap.get(player);

                    if (time > 5000) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Celestia.getInstance(), () -> {
                            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
                            im.getPersistentDataContainer().set(Keys.ETHEREAL_RAPIER_CHARGED, PersistentDataType.BOOLEAN, true);
                            im.setCustomModelData(13011);
                            player.getInventory().getItemInMainHand().setItemMeta(im);
                        }, 1);
                    }
                }
            }
        } catch (NullPointerException ex) {}
    }
}