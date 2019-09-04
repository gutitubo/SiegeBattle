package Siege.SiegeEvent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ShearsDropEvent implements Listener {

	@EventHandler
	public void onDead(PlayerDeathEvent e) {
		Player p = e.getEntity();
		p.getInventory().remove(Material.SHEARS);
		p.setLevel(0);
		p.setExp(0F);
	}

}
