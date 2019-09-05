package Siege.BonusChest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Legendary {
	GAPPLE(Material.ENCHANTED_GOLDEN_APPLE, 1),
	EFF5SHEARS(Material.SHEARS ,1),
	STRPOT(Material.POTION, 1),
	;

	private Material mat;
	private int amount;

	private Legendary(Material mat, int amount) {
		this.mat = mat;
		this.amount = amount;
	}

	public ItemStack toItemStack() {
		ItemStack item = new ItemStack(mat, amount);
		return item;
	}
}
