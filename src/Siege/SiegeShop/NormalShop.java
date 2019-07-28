package Siege.SiegeShop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NormalShop extends SiegeShop{
	
	public static String invName = ChatColor.GRAY + "Darkness Shop";
	
	public NormalShop () {
		
	}
	
	public static Inventory getInventory(String type) {
		//Type RED = REDSTONE
		//Type EME = EMELALD
		//Type DIA = DIAMOND
		//Type LAP = LAPIS
		
		Inventory inv = Bukkit.createInventory(null, 54, invName);
		
		inv.setItem(0, new ItemStack(Material.ACACIA_BOAT));
		
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
