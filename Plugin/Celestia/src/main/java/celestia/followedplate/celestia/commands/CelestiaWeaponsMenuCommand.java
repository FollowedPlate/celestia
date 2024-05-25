package celestia.followedplate.celestia.commands;

import celestia.followedplate.celestia.menusystem.menus.WeaponsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import celestia.followedplate.celestia.Celestia;

public class CelestiaWeaponsMenuCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (sender instanceof Player player) {
            new WeaponsMenu(Celestia.getPlayerMenuUtility(player)).open();
        }
        return true;
    }
}
