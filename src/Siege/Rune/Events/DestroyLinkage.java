package Siege.Rune.Events;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import Siege.Rune.Linkage;

public class DestroyLinkage implements Listener {

	@EventHandler
	public void onBroke(BlockBreakEvent e) {
		Block b = e.getBlock();

		for (Linkage l : Linkage.getList()) {
			if (l.getBlock().equals(b)) {
				l.remove();
			}
		}
	}

}
