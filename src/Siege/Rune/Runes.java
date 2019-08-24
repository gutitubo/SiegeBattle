package Siege.Rune;

import static Siege.Rune.RuneNames.*;
import static org.bukkit.Material.*;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Runes {
	/* === 戦闘系Tier1 === */
	BATTLE_SOLOFIGHTER(1, BATTLE_SOLOFIGHTER_NAME, BLAZE_POWDER), //孤軍奮闘
	BATTLE_BERSERKER(1, BATTLE_BERSERKER_NAME, BLAZE_POWDER), //狂戦士
	BATTLE_CHEERER(1, BATTLE_CHEERER_NAME, HOPPER), //応援団
	BATTLE_RESIRIENCE(1, BATTLE_RESIRIENCE_NAME, ENDER_EYE), //逆境魂
	/* === 錬金系Tier1 === */
	MAGIC_CORESHIELD(1, MAGIC_CORESHIELD_NAME, ENDER_EYE),//コアシールド
	MAGIC_SPELLTHIEF(1, MAGIC_SPELLTHIEF_NAME, ENDER_EYE),//スペルシーフ
	MAGIC_PERFUME(1, MAGIC_PERFUME_NAME, ENDER_EYE),//魔法の香水
	/* === 敏捷系 Tier1 === */
	SWIFT_CAMO(1, SWIFT_CAMO_NAME, GLASS),
	SWIFT_DOUBLEJUMP(1, SWIFT_DOUBLEJUMP_NAME, FEATHER),
	SWIFT_CARRIER(1, SWIFT_CARRIER_NAME, SADDLE),
	SWIFT_WINDY(1, SWIFT_WINDY_NAME, FEATHER),
	/* === 採取系 Tier1 === */
	COLLECT_THIEF(1, COLLECT_THIEF_NAME, GOLD_INGOT),
	COLLECT_SCAVENGER(1, COLLECT_SCAVENGER_NAME, IRON_SWORD),
	COLLECT_MINER(1, COLLECT_MINER_NAME, IRON_PICKAXE),
	;

	private int tier;
	private String name;
	private Material material;

	private Runes(int tier, String name, Material material) {
		this.tier = tier;
		this.name = name;
		this.material = material;
	}

	public String getName() {
		return name;
	}

	public int getTier() {
		return tier;
	}

	public Material getMaterial() {
		return material;
	}

	public static boolean isRune(ItemStack item) {
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

	public static Runes getRune(ItemStack item) {
		if (!isRune(item)) return null;
		Material mat = item.getType();
		String itemName = null;
		if (item != null) itemName = item.getItemMeta().getDisplayName();
		if (itemName == null) return null;
		switch (mat) {
		case BLAZE_POWDER:
			if (itemName.equals(Runes.BATTLE_SOLOFIGHTER.getName())) return Runes.BATTLE_SOLOFIGHTER;
		default:
			return null;
		}
	}

	public static HashSet<Runes> asHashSet() {
		HashSet<Runes> runes = new HashSet<Runes>();
		for (Runes r : Runes.values()) {
			runes.add(r);
		}
		return runes;
	}

	public ItemStack toItemStack() {
		ItemStack item = new ItemStack(getMaterial());
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(getName());
		//TODO 説明を追加する
		item.setItemMeta(itemMeta);
		return item;
	}
}
