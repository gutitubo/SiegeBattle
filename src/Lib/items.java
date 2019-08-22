package Lib;

import org.bukkit.inventory.ItemStack;

public enum items {
	ENCHANTED_SHEARS,
	WEB,
	SUPER_STRONG_SWORD;

	//フィールド
	private ItemStack item;

	//コンストラクタ
	private items() {
//		switch(this) {
//		case ENCHANTED_SHEARS:
//			break;
//		}
	}

	public ItemStack toItemStack() {
		return this.item;
	}
}
