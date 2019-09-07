package Siege.BonusChest;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public enum Rare {
	GOLD(Material.GOLD_INGOT, 4, null, false),
	IRON(Material.IRON_INGOT, 4, null, false),
	HEALPOT(Material.POTION, 2, PotionType.INSTANT_HEAL, true),
	HEALPOT_THROW(Material.SPLASH_POTION, 2, PotionType.INSTANT_HEAL, true),
	;

	private Material mat;
	private int amount;
	private PotionType type;
	private boolean upgraded;

	private Rare(Material mat, int amount, PotionType type, boolean upgraded) {
		this.mat = mat;
		this.amount = amount;
		if (type != null) this.type = type;
		this.upgraded = upgraded;
	}

	public ItemStack toItemStack() {
		ItemStack item = new ItemStack(mat, new Random().nextInt(amount) + 1);
		if (type != null) {
			PotionMeta potme = (PotionMeta)item.getItemMeta();
			potme.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, upgraded));
			item.setItemMeta(potme);
		}
		return item;
	}
}
