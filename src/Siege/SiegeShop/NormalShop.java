package Siege.SiegeShop;

import static Lib.ConstStrings.*;
import static Lib.ItemFactory.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import Lib.SiegeLib;

public class NormalShop extends SiegeShop{

	public NormalShop () {

	}

	public static Inventory getInventory(String type) {
		//Type RED = REDSTONE
		//Type EME = EMELALD
		//Type DIA = DIAMOND
		//Type LAP = LAPIS

		Inventory inv = Bukkit.createInventory(null, 54, SHOP_NORMAL_TITLE);

		if (type.equals("RED")) {
			inv = getRedInventory();
		} else if (type.equals("EME")) {
			inv = getEmeInventory();
		} else if (type.equals("DIA")) {
			inv = getDiaInventory();
		} else if (type.equals("LAP")) {
			inv = getLapInventory();
		}

		return inv;
	}

	//経験値ビン売ってもいいかも?
	public static Inventory getRedInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, SHOP_NORMAL_TITLE);
		SiegeLib.setInventoryFlame(inv, new ItemStack(Material.RED_STAINED_GLASS_PANE));
		inv.setItem(20, getEnchantedShears());
		inv.setItem(22, getGoldIngots());
		inv.setItem(24, getWebItem());
		return inv;
	}

	public static Inventory getEmeInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, SHOP_NORMAL_TITLE);
		SiegeLib.setInventoryFlame(inv, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
//		inv.setItem(20, getSharp5Book());
//		inv.setItem(24, getEff4Book());
		return inv;
	}

	public static Inventory getDiaInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, SHOP_NORMAL_TITLE);
		SiegeLib.setInventoryFlame(inv, new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
		return inv;
	}

	public static Inventory getLapInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, SHOP_NORMAL_TITLE);
		SiegeLib.setInventoryFlame(inv, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
		inv.setItem(20, getShopBow());
		inv.setItem(24, getShopArrow());
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
