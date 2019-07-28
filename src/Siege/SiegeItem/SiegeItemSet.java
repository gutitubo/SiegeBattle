package Siege.SiegeItem;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SiegeItemSet {
	
	public static Set<ItemStack> teamItem(Color color) {
		//アイテム
		Set<ItemStack> sis = new HashSet<ItemStack>();
		sis.add(new ItemStack(Material.WOODEN_SWORD));
		sis.add(new ItemStack(Material.WOODEN_AXE));
		sis.add(new ItemStack(Material.WOODEN_PICKAXE));
		sis.add(new ItemStack(Material.WOODEN_SHOVEL));
		return sis;
	}
	
}
