package Siege.SiegeItem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class SiegeItem {
	
	private ItemStack item;
	
	public SiegeItem(String itemName, Material material, int amount) {
		item = new ItemStack(material, amount);
		item.getItemMeta().setDisplayName(itemName);
		item.setAmount(amount);
	}
	
	public ItemStack toItemStack () {
		return item;
	}
}
