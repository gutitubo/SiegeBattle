package Siege.SiegeEvent;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class BreakCancelEvent implements Listener{

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) { return; }
		if (!isDaijoubu(e.getBlock())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) { return; }
		if (!isDaijoubu(e.getBlock())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void WaterMov(BlockFromToEvent e){
		Block b = e.getBlock();
		if(!isDaijoubu(b)){
			if( b.getType().equals(Material.WATER) || b.getType().equals(Material.WATER)){
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void Bucket(PlayerBucketEmptyEvent e){
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {return;}
		if(!isDaijoubu(e.getBlockClicked())){
			e.setCancelled(true);
		}

	}

	public boolean isDaijoubu(Block b) {
		boolean is = true;
		Location loc = b.getLocation();
		loc.setY(1);
		if(loc.getBlock().getType().equals(Material.MELON)){
			is = false;
		}
		return is;
	}
}
