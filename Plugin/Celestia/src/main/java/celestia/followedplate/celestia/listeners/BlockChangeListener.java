package celestia.followedplate.celestia.listeners;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;

public class BlockChangeListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!e.getPlayer().hasPermission("celestia.protectedblockbuilder")) {
            if (Celestia.getProtectedChunks().contains(e.getBlock().getChunk().getChunkKey())) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED+"You cannot break blocks here");
            }
        }
    }
    @EventHandler
    public void onBlockBuild(BlockCanBuildEvent e) {
        if (!e.getPlayer().hasPermission("celestia.protectedblockbuilder")) {
            if (Celestia.getProtectedChunks().contains(e.getBlock().getChunk().getChunkKey())) {
                e.setBuildable(false);
                e.getPlayer().sendMessage(ChatColor.RED+"You cannot build here");
            }

        }
    }
}
