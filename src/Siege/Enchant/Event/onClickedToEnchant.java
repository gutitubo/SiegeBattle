package Siege.Enchant.Event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class onClickedToEnchant implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();
		if(e.getClickedBlock() == null) return;

		if (b.getType().equals(Material.ENCHANTING_TABLE)) {
			if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				p.getOpenInventory();
			}
		}
	}
}
