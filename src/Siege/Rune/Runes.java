package Siege.Rune;

import static Siege.Rune.RuneCategory.*;
import static Siege.Rune.RuneLore.*;
import static Siege.Rune.RuneNames.*;
import static org.bukkit.Material.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
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
	MAGIC_CORESHIELD(1, MAGIC_CORESHIELD_NAME, MAGIC_CORESHIELD_LORE, END_CRYSTAL, MAGIC),//コアシールド
	MAGIC_SPELLTHIEF(1, MAGIC_SPELLTHIEF_NAME, MAGIC_SPELLTHIEF_LORE, GLASS, MAGIC),//スペルシーフ
	MAGIC_PERFUME(1, MAGIC_PERFUME_NAME, MAGIC_PERFUME_LORE, PHANTOM_MEMBRANE, MAGIC),//魔法の香水
	/* === 敏捷系 Tier1 === */
	SWIFT_CAMO(1, SWIFT_CAMO_NAME, SWIFT_CAMO_LORE, GRASS, SWIFT),
	SWIFT_DOUBLEJUMP(1, SWIFT_DOUBLEJUMP_NAME, SWIFT_DOUBLEJUMP_LORE, FEATHER, SWIFT),
	SWIFT_LORE(1, SWIFT_STORM_NAME, SWIFT_STORM_LORE, SADDLE, SWIFT),
	SWIFT_WINDY(1, SWIFT_WINDY_NAME, SWIFT_WINDY_LORE, FEATHER, SWIFT),
	/* === 採取系 Tier1 === */
	COLLECT_THIEF(1, COLLECT_THIEF_NAME, COLLECT_THIEF_LORE, GOLD_INGOT, COLLECT),
	COLLECT_SCAVENGER(1, COLLECT_SCAVENGER_NAME, COLLECT_SCAVENGER_LORE, IRON_SWORD, COLLECT),
	COLLECT_MINER(1, COLLECT_MINER_NAME, COLLECT_MINER_LORE, IRON_PICKAXE, COLLECT),
	/* === 戦闘系 Tier2 === */
	BATTLE_MELEEDAMAGE(2, BATTLE_2_MELEEDAMAGE_NAME, BATTLE_2_MELEEDAMAGE_LORE, IRON_SWORD, ATTACK),
	BATTLE_ARROWDAMAGE(2, BATTLE_2_ARROWDAMAGE_NAME, BATTLE_2_ARROWDAMAGE_LORE, BOW, ATTACK),
	BATTLE_ANTIDEFENDER(2, BATTLE_2_ANTIDEFENDER_NAME, BATTLE_2_ANTIDEFENDER_LORE, SHIELD, ATTACK),
	BATTLE_HEALTHBOOST(2, BATTLE_2_HEALTHBOOST_NAME, BATTLE_2_HEALTHBOOST_LORE, APPLE, ATTACK),
	BATTLE_REGENERATION(2, BATTLE_2_REGENERATION_NAME, BATTLE_2_REGENERATION_LORE, GOLDEN_APPLE, ATTACK),
	BATTLE_ANTIATTACKER(2, BATTLE_2_ANTIATTACKER_NAME, BATTLE_2_ANTIATTACKER_LORE, IRON_SWORD, ATTACK),
	BATTLE_COREDIGSPEED(2, BATTLE_2_COREDIGSPEED_NAME, BATTLE_2_COREDIGSPEED_LORE, SHEARS, ATTACK),
	/* === 錬金系 Tier2 === */
	MAGIC_ENCHANT(2, MAGIC_2_ENCHANT_NAME, MAGIC_2_ENCHANT_LORE, BOOK, MAGIC),
	MAGIC_POTION_AMP(2, MAGIC_2_POTION_AMP_NAME, MAGIC_2_POTION_AMP_LORE, POTION, MAGIC),
	MAGIC_POTION_DUR(2, MAGIC_2_POTION_DUR_NAME, MAGIC_2_POTION_DUR_LORE, POTION, MAGIC),
	MAGIC_EXPUP(2, MAGIC_2_EXPUP_NAME, MAGIC_2_EXPUP_LORE, DIAMOND_PICKAXE, MAGIC),
	MAGIC_KEYSTONE(2, MAGIC_2_KEYSTONE_NAME, MAGIC_2_KEYSTONE_LORE, EMERALD, MAGIC),
	MAGIC_KILLPOTION(2, MAGIC_2_KILLPOTION_NAME, MAGIC_2_KILLPOTION_LORE, POTION, MAGIC),
	MAGIC_EXPARMOR(2, MAGIC_2_EXPARMOR_NAME, MAGIC_2_EXPARMOR_LORE, GOLDEN_CHESTPLATE, MAGIC),
	/* === 俊敏系 Tier2 === */
	SWIFT_SPEEDUP(2, SWIFT_2_SPEEDUP_NAME, SWIFT_2_SPEEDUP_LORE, FEATHER, SWIFT),
	SWIFT_JUMPBOOST(2, SWIFT_2_JUMPBOOST_NAME, SWIFT_2_JUMPBOOST_LORE, FEATHER, SWIFT),
	SWIFT_NOFALL(2, SWIFT_2_NOFALL_NAME, SWIFT_2_NOFALL_LORE, IRON_BOOTS, SWIFT),
	SWIFT_NOHUNGRY(2, SWIFT_2_NOHUNGRY_NAME, SWIFT_2_NOHUNGRY_LORE, BEEF, SWIFT),
	SWIFT_HOMEGUARD(2, SWIFT_2_HOMEGUARD_NAME, SWIFT_2_HOMEGUARD_LORE, GOLDEN_BOOTS, SWIFT),
	SWIFT_RUNNER(2, SWIFT_2_RUNNER_NAME, SWIFT_2_RUNNER_LORE, LEATHER_BOOTS, SWIFT),
	/* === 収集系 Tier2 === */
	COLLECT_IRON(2, COLLECT_2_IRON_NAME, COLLECT_2_IRON_LORE, IRON_PICKAXE, COLLECT),
	COLLECT_GOLD(2, COLLECT_2_GOLD_NAME, COLLECT_2_GOLD_LORE, GOLDEN_PICKAXE, COLLECT),
	COLLECT_DIA(2, COLLECT_2_DIA_NAME, COLLECT_2_DIA_LORE, DIAMOND_PICKAXE, COLLECT),
	COLLECT_RED(2, COLLECT_2_RED_NAME, COLLECT_2_RED_LORE, STONE_PICKAXE, COLLECT),
	COLLECT_LUCK(2, COLLECT_2_LUCK_NAME, COLLECT_2_LUCK_LORE, GOLD_NUGGET, COLLECT),
	COLLECT_RECALL(2, COLLECT_2_RECALL_NAME, COLLECT_2_RECALL_LORE, STRING, COLLECT),
	COLLECT_HUMANFORGE(2, COLLECT_2_HUMANFORGE_NAME, COLLECT_2_HUMANFORGE_LORE, FURNACE, COLLECT),
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

		@SuppressWarnings("unchecked")
		ArrayList<String> lore = (ArrayList<String>)getLore().clone();
		lore.add(ChatColor.BOLD.toString() + ChatColor.DARK_RED.toString() + "Tier: " + getTier());
		lore.add(ChatColor.BOLD.toString() + ChatColor.DARK_RED.toString() + "Category: " + getCategory().getName());
		itemMeta.setLore(lore);

		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,
				ItemFlag.HIDE_DESTROYS,
				ItemFlag.HIDE_ENCHANTS,
				ItemFlag.HIDE_PLACED_ON,
				ItemFlag.HIDE_POTION_EFFECTS,
				ItemFlag.HIDE_UNBREAKABLE);

		item.setItemMeta(itemMeta);
		return item;
	}

	@Override
	public String toString() {
		String str = this.getName();
		ChatColor c = null;
		switch (this.getCategory()) {
		case ATTACK:
			c = ChatColor.RED;
			break;
		case MAGIC:
			c = ChatColor.GOLD;
			break;
		case SWIFT:
			c = ChatColor.AQUA;
			break;
		case COLLECT:
			c = ChatColor.GREEN;
			break;
		}
		str = c + str + ChatColor.RESET;
		return str;
	}
}
