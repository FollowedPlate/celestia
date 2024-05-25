package celestia.followedplate.celestia.items;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StarlightSaber {
    public static final ItemStack starlightSaber = createStarlightSaber();
    public static HashMap<Player, Long> playerTimerMap = new HashMap<>();

    public static ItemStack createStarlightSaber() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.BLUE + "Starlight " + ChatColor.GOLD + "Saber");
        im.setCustomModelData(13013);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        im.getPersistentDataContainer().set(Keys.CELESTIA_ITEM, PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(Keys.STARLIGHT_SABER, PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(Keys.STARLIGHT_SABER_CHARGED, PersistentDataType.BOOLEAN, false);

        List<String> lore = new ArrayList();
        //lore.add(ChatColor.ITALIC + "" + ChatColor.DARK_PURPLE + "Gambler's delight");
        double attackDamage = Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.attack-damage");
        double attackSpeed = Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.attack-speed");
        double armorToughness = Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.armor-toughness");
        lore.add(ChatColor.GRAY + "When in Main Hand:");
        lore.add(ChatColor.DARK_GREEN + " " + attackDamage + " Attack Damage");
        lore.add(ChatColor.DARK_GREEN + " " + attackSpeed + " Attack Speed");
        lore.add(ChatColor.BLUE + "+" + armorToughness + " Armor Toughness");
        im.setLore(lore);
        AttributeModifier speed = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -4.0D + Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.attack-speed"), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.attack-damage"), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier armor_toughness = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.armor-toughness"), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier armor_toughness2 = new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.armor-toughness"), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speed);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
        im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armor_toughness);
        im.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, armor_toughness2);
        item.setItemMeta(im);

        return item;
    }

    public static void ability(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player player) {
            PersistentDataContainer persistentDataContainer = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
            if (persistentDataContainer.get(Keys.STARLIGHT_SABER_CHARGED, PersistentDataType.BOOLEAN)) {
                if (e.getEntity() instanceof LivingEntity livingEntity) {
                    double percent = Celestia.getConfiguration().getDouble("celestiaWeapons.starlightSaber.ferocity-chance");
                    if (Math.random() <= percent) {
                        player.attack(e.getEntity());
                        player.playSound(player, Sound.ENTITY_ARROW_HIT_PLAYER,  1.0f, 1.0f);
                        //add particle effects
                        player.getLocation().getWorld().spawnParticle(Particle.SWEEP_ATTACK,e.getEntity().getLocation().add(Math.random() - Math.random(),1 + Math.random() - Math.random(),Math.random() - Math.random()), 1);
                    }
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Celestia.getInstance(), () -> {
                        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
                        im.getPersistentDataContainer().set(Keys.STARLIGHT_SABER_CHARGED, PersistentDataType.BOOLEAN, false);
                        im.setCustomModelData(13013);
                        player.getInventory().getItemInMainHand().setItemMeta(im);
                    }, 1);
                }
            } else {
                if (!playerTimerMap.containsKey(player)) {
                    playerTimerMap.put(player, System.currentTimeMillis() - 3001);
                }
                long time = System.currentTimeMillis() - playerTimerMap.get(player);

                if (time > 3000) {
                    ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
                    im.getPersistentDataContainer().set(Keys.STARLIGHT_SABER_CHARGED, PersistentDataType.BOOLEAN, true);
                    im.setCustomModelData(13014);
                    player.getInventory().getItemInMainHand().setItemMeta(im);
                }
            }
        }
    }
}
