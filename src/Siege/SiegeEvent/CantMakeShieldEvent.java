package Siege.SiegeEvent;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CantMakeShieldEvent implements Listener {
	@EventHandler
	public void onCraft(CraftItemEvent e) {
		if(e.getRecipe().getResult().getType().equals(Material.SHIELD)) e.setCancelled(true);
	}
}
