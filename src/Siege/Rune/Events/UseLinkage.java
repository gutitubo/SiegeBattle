package Siege.Rune.Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import Siege.Rune.Linkage;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;

public class UseLinkage implements Listener {

	@EventHandler
	public void linkageInteract(PlayerInteractEvent e) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;

		Player p = e.getPlayer();
		if (!game.isSiegePlayer(p)) return;
		SiegePlayer sp = game.getSiegePlayer(p);

		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Block b = e.getClickedBlock();
			if (b == null) return;
			if (b.getType().equals(Material.SPAWNER)) {
				p.openInventory(linkageInventory(p, sp.getTeam()));
			}
		}
	}

	public static Inventory linkageInventory(Player p, SiegeTeam team) {
		ArrayList<Linkage> link = Linkage.getListAsTeam(team);
		int size = link.size() + 1;
		size = size < 9 ? 9 : size;
		size += (9 - (size % 9));
		String title = team.getColor() + team.getTeamName() + "'s Linkage";
		Inventory inv = Bukkit.createInventory(null, size, title);
		for (Linkage l : link) {
			inv.addItem(l.toItemStack(p.getLocation()));
		}
		return inv;
	}

	@EventHandler
	public void onInventoryClicked(InventoryClickEvent e) {
		if (e.getView().getTitle().contains("Linkage")) {
			e.setCancelled(true);
		} else {
			return;
		}
		if (e.getCurrentItem() == null) return;

		ItemStack clicked = e.getCurrentItem();
		Linkage link = Linkage.getLinkage(clicked);
		if (link == null) return;
		//TODO debuff中使えなくする？
		link.teleport((Player)e.getWhoClicked());
	}


}
