package celestia.followedplate.celestia.commands;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProtectedChunkCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            Chunk chunk = player.getChunk();
            HashSet<Long> protectedChunks = Celestia.getProtectedChunks();
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (!protectedChunks.contains(chunk.getChunkKey())) {
                        protectedChunks.add(chunk.getChunkKey());
                        player.sendMessage(ChatColor.GREEN + "Chunk added successfully");
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "Chunk was already protected");
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (protectedChunks.contains(chunk.getChunkKey())) {
                        protectedChunks.remove(chunk.getChunkKey());
                        player.sendMessage(ChatColor.DARK_RED + "Chunk removed successfully");
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "Chunk was already unprotected");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Specify whether to add or remove chunks.");
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> ret= new ArrayList<>();
        if (args.length == 1) {
            ret.add("add");
            ret.add("remove");
        }
        return ret;
    }
}
