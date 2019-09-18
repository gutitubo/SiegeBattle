package Siege.SiegeEvent;

import static Lib.ConstStrings.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import Siege.Rune.PreRunes;
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
		}
		ItemStack clicked = e.getCurrentItem();
		Material material = null;
		if (clicked == null) return; else material = clicked.getType();
		if (!clicked.getEnchantments().isEmpty()) return;

		RuneCategory cat = null;
		if (flag == 1) {
			if (material == Material.DIAMOND_SWORD) {
				cat = RuneCategory.ATTACK;
			} else if (material == Material.BOOK) {
				cat = RuneCategory.MAGIC;
			} else if (material == Material.FEATHER) {
				cat = RuneCategory.SWIFT;
			} else if (material == Material.DIAMOND_PICKAXE) {
				cat = RuneCategory.COLLECT;
			}
			if (sp != null) {
				sp.setMainPath(cat);
				p.openInventory(RuneInventory.getRuneInventory(sp));
			} else {
				Siege.SiegeBattleMain.preSelect.get(p).setMain(cat);
				p.openInventory(RuneInventory.getRuneInventory(p));
			}
			return;
		} else if (flag == 2) {
			if (material == Material.DIAMOND_SWORD) {
				cat = RuneCategory.ATTACK;
			} else if (material == Material.BOOK) {
				cat = RuneCategory.MAGIC;
			} else if (material == Material.FEATHER) {
				cat = RuneCategory.SWIFT;
			} else if (material == Material.DIAMOND_PICKAXE) {
				cat = RuneCategory.COLLECT;
			}
			if (sp != null) {
				sp.setSubPath(cat);
				p.openInventory(RuneInventory.getRuneInventory(sp));
			} else {
				Siege.SiegeBattleMain.preSelect.get(p).setSub(cat);
				p.openInventory(RuneInventory.getRuneInventory(p));
			}
			return;
		}

		if (clicked.getType().equals(Material.NETHER_STAR)) {
			if (sp != null) {
				sp.clearRune();
				p.openInventory(RuneInventory.getRuneInventory(sp));
			} else {
				Siege.SiegeBattleMain.preSelect.get(p).clear();
				p.openInventory(RuneInventory.getRuneInventory(p));
			}
			p.playSound(p.getLocation(), Sound.BLOCK_GRASS_BREAK, 0.5F, 0.5F);
			return;
		}
		if (sp != null) {
			Runes r = null;
			Runes[] rs = sp.getCurrentRunes();
			if (Runes.isRune(clicked)) {
				r = Runes.getRune(clicked);
			}

			if (r.getTier() == 1) {
				rs[0] = r;
				p.openInventory(RuneInventory.getRuneInventory(sp));
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5F, 0.5F);
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
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5F, 0.5F);
				return;
			} else if (r.getCategory() == sp.getSubPath()){
				/* サブパスだった場合 */
				if (rs[3] == null) {
					rs[3] = r;
				} else if (rs[4] == null) {
					rs[4] = r;
				} else {
					rs[3] = rs[4];
					rs[4] = r;
				}
				p.openInventory(RuneInventory.getRuneInventory(sp));
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5F, 0.5F);
				return;
			}
		} else {
			Runes r = null;
			PreRunes pre = Siege.SiegeBattleMain.preSelect.get(p);
			Runes[] rs = pre.getRunes();
			if (Runes.isRune(clicked)) {
				r = Runes.getRune(clicked);
			}

			if (r.getTier() == 1) {
				rs[0] = r;
				p.openInventory(RuneInventory.getRuneInventory(p));
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5F, 0.5F);
				return;
			} else if (r.getCategory() == pre.getMain()) {
				/* メインパスだった場合 */
				if (rs[1] == null) {
					rs[1] = r;
				} else if (rs[2] == null) {
					rs[2] = r;
				} else {
					rs[1] = rs[2];
					rs[2] = r;
				}
				p.openInventory(RuneInventory.getRuneInventory(p));
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5F, 0.5F);
				return;
			} else if (r.getCategory() == pre.getSub()){
				/* サブパスだった場合 */
				if (rs[3] == null) {
					rs[3] = r;
				} else if (rs[4] == null) {
					rs[4] = r;
				} else {
					rs[3] = rs[4];
					rs[4] = r;
				}
				p.openInventory(RuneInventory.getRuneInventory(p));
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5F, 0.5F);
				return;
			}
		}
	}
}
