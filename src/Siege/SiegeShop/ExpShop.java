package Siege.SiegeShop;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ExpShop extends SiegeShop{

	public static String invName = "ExpShop";

	public ExpShop() {

	}

	public static Inventory getInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, invName);

//		inv.setItem(0, RandomPotion.getRandom());

		return inv;
	}

	@Override
	public void openShopInventory() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void createShopInventory() {
		// TODO 自動生成されたメソッド・スタブ

	}
}
