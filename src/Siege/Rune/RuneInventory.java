package Siege.Rune;

import static Lib.ConstStrings.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import Lib.ItemFactory;
import Siege.SiegePlayer.SiegePlayer;

public class RuneInventory {
	private RuneInventory() {}

	public static Inventory getRunePathInventory(int state, SiegePlayer sp) { //state 0 = main nothing 1 = subnothing
		String str ;
		str = state == 0 ? RUNE_INV_TITLE_PATH : RUNE_INV_TITLE_PATH_SUB;
		Inventory inv = Bukkit.createInventory(null, 9, str);
		inv.setItem(2, ItemFactory.getRunePathIcon(RuneCategory.ATTACK, false)); //攻撃
		inv.setItem(3, ItemFactory.getRunePathIcon(RuneCategory.MAGIC, false)); //錬金
		inv.setItem(4, ItemFactory.getRunePathIcon(RuneCategory.SWIFT, false)); //採取
		inv.setItem(5, ItemFactory.getRunePathIcon(RuneCategory.COLLECT, false)); //俊敏
		if (str.equalsIgnoreCase(RUNE_INV_TITLE_PATH_SUB)) { //サブの場合
			int main = 0;
			switch(sp.getMainPath()) {
			case ATTACK:
				main = 2;
				break;
			case COLLECT:
				main = 3;
				break;
			case MAGIC:
				main = 4;
				break;
			case SWIFT:
				main = 5;
				break;
			}
			inv.setItem(main, ItemFactory.getRunePathSelected()); //俊敏
		}
		return inv;
	}

	public static Inventory getRuneInventory(SiegePlayer sp) {
		if (sp.getMainPath() == null) {
			return getRunePathInventory(0, sp);
		} else if (sp.getSubPath() == null) {
			return getRunePathInventory(1, sp);
		}
		Inventory inv = Bukkit.createInventory(null, 54, RUNE_INV_TITLE_MAIN);
		int i = 0; //index
		/* MainのTier1ルーン */
		for (Runes r : Runes.asHashSet(sp.getMainPath(), 1)) {
			inv.setItem(i, r.toItemStack());
			i++;
		}
		/* MainのTier2ルーン */
		i = 9;
		for (Runes r : Runes.asHashSet(sp.getMainPath(), 2)) {
			inv.setItem(i, r.toItemStack());
			i++;
		}
		/* SubのTier2ルーン */
		i = 27;
		for (Runes r : Runes.asHashSet(sp.getSubPath(), 2)) {
			inv.setItem(i, r.toItemStack());
			i++;
		}
		//下の枠
		for (i = 45; i<54; i++) {
			inv.setItem(i, ItemFactory.createItem(Material.GRAY_STAINED_GLASS_PANE, 1, ""));
		}
		inv.setItem(49, ItemFactory.getResetRunes());
		return inv;
	}
}
