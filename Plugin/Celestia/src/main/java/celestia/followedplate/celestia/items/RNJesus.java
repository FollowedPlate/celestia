package celestia.followedplate.celestia.items;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class RNJesus {
    public static ItemStack next() {
        return next(10);
    }
    public static ItemStack next(int maxStrength) {
        Random rng = new Random();
        ItemStack item;
        Material[] swordMaterials = {Material.DIAMOND_SWORD,Material.IRON_SWORD,Material.GOLDEN_SWORD,Material.STONE_SWORD, Material.WOODEN_SWORD};
        Material[] axeMaterials = {Material.DIAMOND_AXE,Material.IRON_AXE,Material.GOLDEN_AXE,Material.STONE_AXE, Material.WOODEN_AXE};
        //avg maxStrength is 10
        //avg dmg is 5 : 8
        //ang speed is 1.6 : .9
        int swordIndex = (int)(Math.round(rng.nextGaussian(2.0, 1.3)));
        while (swordIndex<0||swordIndex>4) {
            swordIndex = (int)(Math.round(rng.nextGaussian(2.0, 1.3)));
        }

        item = new ItemStack(swordMaterials[swordIndex]);
        //if sword
        double attackDamage = ((int)(10*Math.sqrt(maxStrength)*(rng.nextGaussian(0.0, 0.4)+2.2-swordIndex*.25)))/10.0;
        double attackSpeed = ((int)(10*(.63+rng.nextGaussian(1.0, 0.1))))/10.0;
        ItemMeta im = item.getItemMeta();


        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
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
}