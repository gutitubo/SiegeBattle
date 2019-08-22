package Lib;
import static Lib.ItemFactory.*;
import static Lib.Parameters.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SiegeItems {
	ENCHANTED_SHEARS(1),
	SHOP_WEB(2),
	SUPER_STRONG_SWORD(3);

	//フィールド
	private ItemStack item;

	//コンストラクタ
	private SiegeItems(int id) {
		switch(id) {
		case 1: //ENCHANTED_SHEARS
			this.item = getEnchantedShears();
			break;
		case 2:
			this.item = new ItemStack(Material.COBWEB, REDSHOP_COWWEB_AMOUNT);
			break;
		}
	}

	public ItemStack toItemStack() {
		return this.item;
	}
}
