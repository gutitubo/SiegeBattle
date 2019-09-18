package Siege.SiegeEvent;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Lib.ConstStrings;
import Siege.Rune.PreRunes;

public class LoginEvent implements Listener{

	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Siege.SiegeBattleMain.siegeBattleMain.getGame()==null || Siege.SiegeBattleMain.siegeBattleMain.getGame().getPhase() <= 1) { //ゲームが始まっていない場合
			Lib.SiegeLib.teleportSpawn(p);
			Inventory inv = p.getInventory();
			Siege.SiegeBattleMain.preSelect.put(p, new PreRunes());
			inv.clear();
//			inv.setItem(0, siegeBook());
			inv.setItem(1, runeSelector());

			joinInfo(p);
		} else { //始まってる場合

		}
	}

	public ItemStack siegeBook() {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);

		return item;
	}

	public ItemStack runeSelector() {
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "RUNE SELECTOR");
		return item;
	}

	public void joinInfo(Player p) {
		for (String msg : ConstStrings.JOIN_MESSAGE) {
			p.sendMessage(msg);
		}
	}
}
