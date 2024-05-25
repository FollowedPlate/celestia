package celestia.followedplate.celestia.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuelCommand implements TabExecutor {

    public static final HashMap<Player, Location> playerLocationHashMap = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!playerLocationHashMap.containsKey(player)) {
                playerLocationHashMap.put(player, player.getLocation());
                player.teleport(new Location(Bukkit.getWorld("world"), 10.0, 100.0, 10.0));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> players = new ArrayList<>();
        if (args.length==1) {
            for(Player player:Bukkit.getOnlinePlayers()) {
                players.add(player.getName());
            }
        }
        return players;
    }
}
