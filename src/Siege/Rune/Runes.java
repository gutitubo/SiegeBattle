package Siege.Rune;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Runes {
	BATTLE_SOLOFIGHTER, //孤軍奮闘
	BATTLE_BERSERKER, //狂戦士
	BATTLE_CHEERER, //応援団
	BATTLE_RESIRIENCE; //逆境魂

	private Runes() {

	}

	public boolean isRune(ItemStack item) {
		Material mat = item.getType();
		String itemName = null;
		if (item != null) itemName = item.getItemMeta().getDisplayName();
		if (itemName == null) return false;
		switch(mat) {
		case BLAZE_POWDER:
			if (itemName.equals("test string")) return true;
			if (itemName.equals("test string 2")) return true;
		default:
			return false;
		}
	}
}
