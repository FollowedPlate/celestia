package celestia.followedplate.celestia.commands;

import celestia.followedplate.celestia.Celestia;
import celestia.followedplate.celestia.menusystem.menus.EnchantingTableMenu;
import celestia.followedplate.celestia.menusystem.menus.WeaponsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnchantingTableCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            new EnchantingTableMenu(Celestia.getPlayerMenuUtility(player)).open();
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> ret = new ArrayList<>();
        return ret;
    }
}
