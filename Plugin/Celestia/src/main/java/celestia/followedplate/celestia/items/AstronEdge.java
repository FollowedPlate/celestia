package celestia.followedplate.celestia.items;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AstronEdge {
    public static ItemStack astronEdge = createAstronEdge();
    public static HashMap<Player, Long> playerTimerMap = new HashMap<>();

    public static ItemStack createAstronEdge() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta im = item.getItemMeta();
        im.setCustomModelData(13012);
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        im.getPersistentDataContainer().set(Keys.ASTRON_EDGE, PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(Keys.CELESTIA_ITEM, PersistentDataType.BOOLEAN, true);

        im.setDisplayName(ChatColor.BLUE + "Astron Edge");
        List<String> lore = new ArrayList();
//        lore.add(ChatColor.ITALIC +""+ ChatColor.DARK_PURPLE + "Right click to double jump");
        double attackDamage = Celestia.getConfiguration().getDouble("celestiaWeapons.astronEdge.attack-damage");
        double attackSpeed = Celestia.getConfiguration().getDouble("celestiaWeapons.astronEdge.attack-speed");
        lore.add(ChatColor.GRAY + "When in Main Hand:");
        lore.add(ChatColor.DARK_GREEN + " " + attackDamage + " Attack Damage");
        lore.add(ChatColor.DARK_GREEN + " " + attackSpeed + " Attack Speed");
        im.setLore(lore);
        AttributeModifier speed = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -4.0D + attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, speed);
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
        item.setItemMeta(im);
        return item;
    }

    public static void ability(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!playerTimerMap.containsKey(player)) {
            playerTimerMap.put(player, System.currentTimeMillis() - 5001);
        }
        long time = System.currentTimeMillis() - playerTimerMap.get(player);

        if (time > 5000) {
            playerTimerMap.put(player, System.currentTimeMillis());
            player.setVelocity(player.getVelocity().multiply(0.5).add(player.getLocation().getDirection().multiply(1.5)));
            player.playSound(player, Sound.ENTITY_WITHER_SHOOT, .5f, 1.75f);
            player.playSound(player, Sound.ENTITY_BLAZE_SHOOT, .5f, 2.0f);
            player.playSound(player, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f, 1.55f);
        } else {
            player.sendMessage(ChatColor.YELLOW + "This item is on cooldown for " + ChatColor.WHITE + String.format("%3.1f", 5 - time / 1000.0) + ChatColor.YELLOW + " more seconds.");
        }

    }
}
