package Siege.BonusChest;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Normal {
	SAND(Material.SAND, 3),
	CACTUS(Material.CACTUS, 3),
	BOTTLEOENCH(Material.EXPERIENCE_BOTTLE, 16),
	;

	private Material mat;
	private int amount;

	private Normal(Material mat, int amount) {
		this.mat = mat;
		this.amount = amount;
	}

	public ItemStack toItemStack() {
		return new ItemStack(mat, new Random().nextInt(amount) + 1);
	}
}
