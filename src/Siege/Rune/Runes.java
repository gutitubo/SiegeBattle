package Siege.Rune;

import static Siege.Rune.RuneCategory.*;
import static Siege.Rune.RuneLore.*;
import static Siege.Rune.RuneNames.*;
import static org.bukkit.Material.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public enum Runes {
	/* === 戦闘系Tier1 === */
	//NAME(Tier, 名前定数, ロア定数, アイコン用マテリアル, カテゴリー);
	BATTLE_SOLOFIGHTER(1, BATTLE_SOLOFIGHTER_NAME, BATTLE_SOLOFIGHTER_LORE, BLAZE_POWDER, ATTACK), //孤軍奮闘
	BATTLE_BERSERKER(1, BATTLE_BERSERKER_NAME, BATTLE_BERSERKER_LORE, BLAZE_POWDER, ATTACK), //狂戦士
	BATTLE_CHEERER(1, BATTLE_CHEERER_NAME, BATTLE_CHEERER_LORE, HOPPER, ATTACK), //応援団
	BATTLE_RESIRIENCE(1, BATTLE_RESIRIENCE_NAME, BATTLE_RESIRIENCE_LORE, ENDER_EYE, ATTACK), //逆境魂
	/* === 錬金系Tier1 === */
	MAGIC_CORESHIELD(1, MAGIC_CORESHIELD_NAME, MAGIC_CORESHIELD_LORE, ENDER_EYE, MAGIC),//コアシールド
	MAGIC_SPELLTHIEF(1, MAGIC_SPELLTHIEF_NAME, MAGIC_SPELLTHIEF_LORE, ENDER_EYE, MAGIC),//スペルシーフ
	MAGIC_PERFUME(1, MAGIC_PERFUME_NAME, MAGIC_PERFUME_LORE, ENDER_EYE, MAGIC),//魔法の香水
	/* === 敏捷系 Tier1 === */
	SWIFT_CAMO(1, SWIFT_CAMO_NAME, SWIFT_CAMO_LORE, GLASS, SWIFT),
	SWIFT_DOUBLEJUMP(1, SWIFT_DOUBLEJUMP_NAME, SWIFT_DOUBLEJUMP_LORE, FEATHER, SWIFT),
	SWIFT_LORE(1, SWIFT_STORM_NAME, SWIFT_STORM_LORE, SADDLE, SWIFT),
	SWIFT_WINDY(1, SWIFT_WINDY_NAME, SWIFT_WINDY_LORE, FEATHER, SWIFT),
	/* === 採取系 Tier1 === */
	COLLECT_THIEF(1, COLLECT_THIEF_NAME, COLLECT_THIEF_LORE, GOLD_INGOT, COLLECT),
	COLLECT_SCAVENGER(1, COLLECT_SCAVENGER_NAME, COLLECT_SCAVENGER_LORE, IRON_SWORD, COLLECT),
	COLLECT_MINER(1, COLLECT_MINER_NAME, COLLECT_MINER_LORE, IRON_PICKAXE, COLLECT),
	;

	private int tier; //Tier
	private String name; //ルーンの名前
	private Material material; //アイコンのマテリアル
	private RuneCategory category; //ルーンのカテゴリー
	private ArrayList<String> lore; //ルーンの説明

	private Runes(int tier, String name, ArrayList<String> lore, Material material, RuneCategory category) {
		this.tier = tier;
		this.name = name;
		this.material = material;
		this.category = category;
		this.lore = lore;
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

	public RuneCategory getCategory() {
		return category;
	}

	public ArrayList<String> getLore() {
		return lore;
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

	public static HashSet<Runes> asHashSet(RuneCategory category) {
		HashSet<Runes> runes = new HashSet<Runes>();
		for (Runes r : Runes.values()) {
			if (r.getCategory() == category) runes.add(r);
		}
		return runes;
	}

	public static HashSet<Runes> asHashSet(RuneCategory category, int tier) {
		HashSet<Runes> runes = new HashSet<Runes>();
		for (Runes r : Runes.values()) {
			if (r.getCategory() == category && r.getTier() == tier) runes.add(r);
		}
		return runes;
	}

	public ItemStack toItemStack() {
		ItemStack item = new ItemStack(getMaterial());
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(getName());
		ArrayList<String> lore = getLore();
		lore.add(ChatColor.BOLD.toString() + ChatColor.DARK_RED.toString() + "Tier: " + getTier());
		lore.add(ChatColor.BOLD.toString() + ChatColor.DARK_RED.toString() + "Category: " + getCategory().getName());
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}
}
