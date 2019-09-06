package Siege.BonusChest;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public enum Epic {
	BOOK_DIGSPEED(Material.ENCHANTED_BOOK, 1, Enchantment.DIG_SPEED, 2),
	BOOK_DAMAGEALL(Material.ENCHANTED_BOOK, 1, Enchantment.DAMAGE_ALL, 3),
	BOOK_UNBREAKING(Material.ENCHANTED_BOOK, 1, Enchantment.DURABILITY, 3),
	BOOK_FIREASPECT(Material.ENCHANTED_BOOK, 1, Enchantment.FIRE_ASPECT, 1),
	EMELALD(Material.EMERALD, 3, null, null),
	DIAMOND(Material.DIAMOND, 3, null, null),
	;

	private Material mat;
	private Integer amount;
	private Enchantment ench;
	private int level;

	private Epic(Material mat, int amount, Enchantment ench, Integer level) {
		this.mat = mat;
		this.amount = amount;
		this.ench = ench;
		this.level = level;
	}

	public ItemStack toItemStack() {
		ItemStack item = new ItemStack(mat, new Random().nextInt(amount) + 1);
		if (ench != null) {
			item.addEnchantment(ench, new Random().nextInt(level));
		}
		return item; //TODO
	}
}
