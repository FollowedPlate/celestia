package celestia.followedplate.celestia.listeners;


import celestia.followedplate.celestia.Celestia;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ResourcepackChecker implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Celestia.getConfiguration().getBoolean("enable-resource-pack") && !Bukkit.getServer().getPluginManager().isPluginEnabled("platesweapons")) {
            event.getPlayer().setResourcePack("https://cdn.modrinth.com/data/UL2i8RBA/versions/Egw1HFXd/Celestia_Resource_Pack.zip");
        }
    }
}