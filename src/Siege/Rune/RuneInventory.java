package Siege.Rune;

import static Lib.ConstStrings.*;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class RuneInventory {
	private RuneInventory() {}

	public static Inventory getRunePathInventory() {
		Inventory inv = Bukkit.createInventory(null, 9, RUNE_INV_TITLE_PATH);
		inv.setItem(2, null); //攻撃
		inv.setItem(3, null); //錬金
		inv.setItem(4, null); //採取
		inv.setItem(5, null); //俊敏
		return inv;
	}
}
