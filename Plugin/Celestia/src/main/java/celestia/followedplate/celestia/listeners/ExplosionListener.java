package celestia.followedplate.celestia.listeners;

import celestia.followedplate.celestia.Celestia;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Iterator;

public class ExplosionListener implements Listener {
    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent e) {
        Iterator<Block> iter = e.blockList().iterator();
        while(iter.hasNext()) {
            if (Celestia.getProtectedChunks().contains(iter.next().getChunk().getChunkKey())) {
                iter.remove();
            }
        }
    }
    @EventHandler
    public void onCreeperExplosion(EntityExplodeEvent e) {
        Iterator<Block> iter = e.blockList().iterator();
        while(iter.hasNext()) {
            if (Celestia.getProtectedChunks().contains(iter.next().getChunk().getChunkKey())) {
                iter.remove();
            }
        }
    }
}
