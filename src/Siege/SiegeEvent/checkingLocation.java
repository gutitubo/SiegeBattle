package Siege.SiegeEvent;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class checkingLocation implements Listener{
	@EventHandler
	public void onClicked(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		if (b == null) return;
		Player p = e.getPlayer();
		if (p.getGameMode().equals(GameMode.CREATIVE) && p.getInventory().getItemInMainHand() != null
				&& p.getInventory().getItemInMainHand().getType().equals(Material.GLOWSTONE_DUST)) {
			p.sendMessage(ChatColor.GRAY + "Location - X:" + b.getX() + " Y:" + b.getY() + " Z:" + b.getZ());
		}
	}
}
