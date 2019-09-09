package Siege.BonusChest;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

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
		switch(this) {
		case EFF5SHEARS:
			item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 4);
			break;
		case STRPOT:
			PotionMeta potme = (PotionMeta)item.getItemMeta();
			potme.setBasePotionData(new PotionData(PotionType.STRENGTH, false, true));
			item.setItemMeta(potme);
			break;
		default:
			break;

		}
		return item;
	}
}
