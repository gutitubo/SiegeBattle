package Siege.SiegeEvent;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OrePlaceEvent implements Listener {
	@EventHandler
	public void onPlaced(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Material mat = e.getBlock().getType();
		if (isOre(mat) && !p.getGameMode().equals(GameMode.CREATIVE)) {
			e.setCancelled(true);
		}
	}

	public boolean isOre(Material material) {
		boolean flag = false;
		switch(material) {
		case COAL_ORE:
		case IRON_ORE:
		case GOLD_ORE: {
			flag = true;
			break;
		}
		default: break;
		}
		return flag;
	}
}
