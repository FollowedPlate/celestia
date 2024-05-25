package celestia.followedplate.celestia;


import celestia.followedplate.celestia.commands.CelestiaWeaponsMenuCommand;
import celestia.followedplate.celestia.commands.DuelCommand;
import celestia.followedplate.celestia.commands.EnchantingTableCommand;
import celestia.followedplate.celestia.commands.ProtectedChunkCommand;
import celestia.followedplate.celestia.items.AmuletOfUndying;
import celestia.followedplate.celestia.listeners.*;
import celestia.followedplate.celestia.menusystem.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;

public final class Celestia extends JavaPlugin {
    private static Celestia instance;
    private static FileConfiguration config;
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<Player, PlayerMenuUtility>();
    private static final HashSet<Long> protectedChunks = new HashSet<>();
    public static Celestia getInstance() {return instance;}
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        if (!playerMenuUtilityMap.containsKey(p)) playerMenuUtilityMap.put(p, new PlayerMenuUtility(p));
        return playerMenuUtilityMap.get(p);
    }
    public static HashSet<Long> getProtectedChunks() {return protectedChunks;}
    @NotNull
    public static FileConfiguration getConfiguration() {return config;}
    @Override
    public void onEnable() {
        instance = this;
        config = this.getConfig();
        config.options().copyDefaults();
        saveDefaultConfig();

        String chunks = getConfig().getString("protected-chunks");
        if (chunks!=null && chunks.length() > 0) {
            for (String str : chunks.split(",")) {
                long key = Long.parseLong(str);
                protectedChunks.add(key);
            }
        }

        AmuletOfUndying.init();

        getCommand("celestiaChunk").setExecutor(new ProtectedChunkCommand());
        getCommand("celestiaWeaponsMenu").setExecutor(new CelestiaWeaponsMenuCommand());
        getCommand("duel").setExecutor(new DuelCommand());
        getCommand("enchantmentTable").setExecutor(new EnchantingTableCommand());
        getServer().getPluginManager().registerEvents(new BlockChangeListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new ResourcepackChecker(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerAttackListener(), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Celestia has been enabled!");

    }
    @Override
    public void onDisable() {
        String str = " ";
        for (long l:protectedChunks) {
            str+= l + ",";
        }
        str = str.substring(0,str.length()-1).trim();

        getConfig().set("protected-chunks", str);
        saveConfig();

        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(ChatColor.RED + "Celestia has been disabled!");
    }
}
