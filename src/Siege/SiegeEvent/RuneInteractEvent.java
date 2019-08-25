package Siege.SiegeEvent;

import static Lib.ConstStrings.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import Siege.Rune.RuneCategory;
import Siege.Rune.RuneInventory;
import Siege.Rune.Runes;
import Siege.SiegePlayer.SiegePlayer;

public class RuneInteractEvent implements Listener {

	@EventHandler
	public void onClicked(InventoryClickEvent e) {
		HumanEntity human = e.getWhoClicked();

		Player p = human instanceof Player ? Bukkit.getPlayer(human.getName()) : null ;
		if (p == null) { return; }

		InventoryView inv = p.getOpenInventory();
		String iname = inv.getTitle();

		int flag = 0;
		/*
		 * flag 1 = main path
		 * flag 2 = sub path
		 * flag 3 = main view
		 */

		if (iname.equalsIgnoreCase(RUNE_INV_TITLE_PATH)) {
			flag = 1;
		} else if (iname.equalsIgnoreCase(RUNE_INV_TITLE_PATH_SUB)) {
			flag = 2;
		} else if (iname.equalsIgnoreCase(RUNE_INV_TITLE_MAIN)) {
			flag = 3;
		} else {
			return;
		}

		e.setCancelled(true);
		SiegePlayer sp = null;
		if (Siege.SiegeBattleMain.siegeBattleMain.getGame() != null && Siege.SiegeBattleMain.siegeBattleMain.getGame().isSiegePlayer(p)) {
			sp = Siege.SiegeBattleMain.siegeBattleMain.getGame().getSiegePlayer(p);
			Bukkit.broadcastMessage("debug 1");
		}
		ItemStack clicked = e.getCurrentItem();
		Material material = null;
		if (clicked == null) return; else clicked.getType();
		Bukkit.broadcastMessage("debug 2");
		if (flag == 1) {
			if (material == Material.DIAMOND_SWORD) {
				sp.setMainPath(RuneCategory.ATTACK);
			} else if (material == Material.BOOK) {
				sp.setMainPath(RuneCategory.MAGIC);
			} else if (material == Material.FEATHER) {
				sp.setMainPath(RuneCategory.SWIFT);
			} else if (material == Material.DIAMOND_PICKAXE) {
				sp.setMainPath(RuneCategory.COLLECT);
			}
			p.openInventory(RuneInventory.getRuneInventory(sp));
			Bukkit.broadcastMessage("debug 3");
			return;
		} else if (flag == 2) {
			if (material == Material.DIAMOND_SWORD) {
				sp.setSubPath(RuneCategory.ATTACK);
			} else if (material == Material.BOOK) {
				sp.setSubPath(RuneCategory.MAGIC);
			} else if (material == Material.FEATHER) {
				sp.setSubPath(RuneCategory.SWIFT);
			} else if (material == Material.DIAMOND_PICKAXE) {
				sp.setSubPath(RuneCategory.COLLECT);
			}
			p.openInventory(RuneInventory.getRuneInventory(sp));
			Bukkit.broadcastMessage("debug 4");
			return;
		}

		Runes r = null;
		Runes[] rs = sp.getCurrentRunes();
		if (Runes.isRune(clicked)) {
			r = Runes.getRune(clicked);
		} else {
			if (clicked.getType().equals(Material.BARRIER)) {
				sp.clearRune();
				p.openInventory(RuneInventory.getRuneInventory(sp));
				return;
			}
		}

		if (r.getTier() == 1) {
			rs[0] = r;
			p.openInventory(RuneInventory.getRuneInventory(sp));
			return;
		} else if (r.getCategory() == sp.getMainPath()) {
			/* メインパスだった場合 */
			if (rs[1] == null) {
				rs[1] = r;
			} else if (rs[2] == null) {
				rs[2] = r;
			} else {
				rs[1] = rs[2];
				rs[2] = r;
			}
			p.openInventory(RuneInventory.getRuneInventory(sp));
			return;
		} else if (r.getCategory() == sp.getSubPath()){
			/* サブパスだった場合 */
			if (rs[3] == null) {
				rs[4] = r;
			} else if (rs[4] == null) {
				rs[4] = r;
			} else {
				rs[3] = rs[4];
				rs[4] = r;
			}
			p.openInventory(RuneInventory.getRuneInventory(sp));
			return;
		}
	}
}
