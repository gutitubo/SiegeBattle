package Siege.Enchant.Event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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

	@EventHandler
	public void onOpenInventory(InventoryOpenEvent e) {
		if (e.getView().getType().equals(InventoryType.ENCHANTING)) {
			e.getInventory().setItem(1, new ItemStack(Material.LAPIS_LAZULI, 3));
		}
	}

	@EventHandler
	public void onClickLapis(InventoryClickEvent e) {
		if (e.getInventory().getType().equals(InventoryType.ENCHANTING) && e.getCurrentItem() != null) {
			if (e.getCurrentItem().getType().equals(Material.LAPIS_LAZULI)) {
				e.setCancelled(true);
			}
		}
	}
}
