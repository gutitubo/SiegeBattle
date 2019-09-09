package Lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import Siege.Rune.RuneCategory;

public class ItemFactory {

	private ItemFactory() {

	}

	public static ItemStack createItem(Material material, int amount) {
		ItemStack item = new ItemStack(material, amount);
		return item;
	}

	public static ItemStack createItem(Material material, int amount, String itemName) {
		ItemStack item = createItem(material, amount);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(itemName);
		item.setItemMeta(itemMeta);
		return item;
	}

	public static ItemStack createItem(Material material, int amount, String itemName, String... itemLore) {
		ItemStack item = createItem(material, amount, itemName);
		ItemMeta itemMeta = item.getItemMeta();
		List<String> loreList = Arrays.asList(itemLore);
		itemMeta.setLore(loreList);
		item.setItemMeta(itemMeta);
		return item;
	}

	public static ItemStack createItem(Material material, int amount, String itemName, Enchantment enchant, int enchLv, String... itemLore) {
		ItemStack item = createItem(material, amount, itemName, itemLore);
		item.addEnchantment(enchant, enchLv);
		return item;
	}

	public static ItemStack createEnchantedBook(Enchantment ench, int level) {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		meta.addEnchant(ench, level, true);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createEnchantedBook(String name, Enchantment ench, int level, String... itemLore) {
		ItemStack item = createEnchantedBook(ench, level);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> loreList = Arrays.asList(itemLore);
		meta.setLore(loreList);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getGambleIcon () {
		ItemStack gamble = new ItemStack(Material.NETHER_STAR);
		ItemMeta gamble_meta = gamble.getItemMeta();
		gamble_meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "ギャンブル");
		List<String> gamble_lore = new ArrayList<String>();
		gamble_lore.add("何が当たるか");
		gamble_lore.add("わからない");
		gamble_lore.add("");
		gamble_lore.add("cost:" + Parameters.SHOP_GAMBLE_COST + "diamonds");
		gamble_meta.setLore(gamble_lore);
		gamble.setItemMeta(gamble_meta);
		return gamble;
	}

	public static ItemStack getRedStoneShopIcon() {
		ItemStack red = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta red_meta = red.getItemMeta();
		red_meta.setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD.toString() + "RedStoneItem");
		List<String> red_lore = new ArrayList<String>();
		red_lore.add("赤石で購入できるアイテムを表示する");
		red_meta.setLore(red_lore);
		red.setItemMeta(red_meta);
		return red;
	}

	public static ItemStack getEmeraldShopIcon() {
		ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD.toString() + "EmetaldItem");
		List<String> lore = new ArrayList<String>();
		lore.add("エメラルドで購入できるアイテムを表示する");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getDiamondShopIcon() {
		ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD.toString() + "DiamondItem");
		List<String> lore = new ArrayList<String>();
		lore.add("ダイヤモンドで購入できるアイテムを表示する");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getLapisShopIcon() {
		ItemStack item = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_BLUE.toString() + ChatColor.BOLD.toString() + "LapisItem");
		List<String> lore = new ArrayList<String>();
		lore.add("ラピスラズリで購入できるアイテムを表示する");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getGoldIngots() {
		ItemStack item = new ItemStack(Material.GOLD_INGOT, 3);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "金塊");
		List<String> lore = new ArrayList<String>();
		lore.add("めっちゃ高級な金塊");
		lore.add(ChatColor.DARK_RED + "Cost: " + Parameters.REDSHOP_GOLDINGOT_COST);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getWebItem() {
		ItemStack item = new ItemStack(Material.COBWEB);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "蜘蛛の巣");
		List<String> lore = new ArrayList<String>();
		lore.add("人を苦しませるための蜘蛛の巣");
		lore.add(ChatColor.DARK_RED + "Cost: " + Parameters.REDSHOP_COWWEB_COST);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getShopBow() {
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "弓");
		List<String> lore = new ArrayList<String>();
		lore.add("※矢は付属しておりません");
		lore.add(ChatColor.BLUE + "Cost: " + Parameters.LAPSHOP_BOW_COST);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getShopArrow() {
		ItemStack item = createItem(Material.ARROW,
				1,
				ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "矢",
				"これがないと始まらない",
				ChatColor.BLUE + "Cost: " + Parameters.LAPSHOP_ARROW_COST);
		//		ItemStack item = new ItemStack(Material.ARROW);
		//		ItemMeta meta = item.getItemMeta();
		//		meta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "矢");
		//		List<String> lore = new ArrayList<String>();
		//		lore.add("これがないと始まらない");
		//		meta.setLore(lore);
		//		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getEnchantedShears() {
		ItemStack item = createItem(Material.SHEARS,
				1,
				ChatColor.GOLD + "Shears +1",
				Enchantment.DIG_SPEED,
				1,
				ChatColor.GOLD + "ちょっとはやい",
				ChatColor.DARK_RED + "Cost: " + Parameters.REDSHOP_SHEARS_COST);
		return item;
	}

	public static ItemStack getSharp5Book() {
		return createEnchantedBook("ダメージ増加5の本", Enchantment.DAMAGE_ALL, 5,ChatColor.GREEN + "Cost: 3 Emerald");
	}

	public static ItemStack getEff4Book() {
		return createEnchantedBook("効率強化4の本", Enchantment.DIG_SPEED, 4,ChatColor.GREEN + "Cost: 3 Emerald");
	}

	public static ItemStack getResetRunes() {
		ItemStack item = createItem(Material.NETHER_STAR,
				1,
				ChatColor.RED.toString() + ChatColor.BOLD.toString() + "RESET RUNE",
				"ルーンをリセットする");
		return item;
	}

	public static void setSelected(ItemStack item) {
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 0);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(meta);
	}

	public static ItemStack getRunePathIcon(RuneCategory category, boolean selected) {
		ItemStack item = createItem(Material.BARRIER, 1, "error.");
		if (category == RuneCategory.ATTACK) {
			item = createItem(Material.DIAMOND_SWORD,
					1,
					ChatColor.RED.toString() + ChatColor.BOLD.toString() + category.getName(),
					"殴り合いに特化したルーン");
		} else if (category == RuneCategory.MAGIC) {
			item = createItem(Material.BOOK,
					1,
					ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + category.getName(),
					"魔法の力を強化するルーン");
		} else if (category == RuneCategory.SWIFT) {
			item = createItem(Material.FEATHER,
					1,
					ChatColor.AQUA.toString() + ChatColor.BOLD.toString() + category.getName(),
					"移動力を強化するルーン");
		} else if (category == RuneCategory.COLLECT) {
			item = createItem(Material.DIAMOND_PICKAXE,
					1,
					ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + category.getName(),
					"アイテム集めに特化したルーン");
		}
		if(selected) setSelected(item);
		return item;
	}

	public static ItemStack getRunePathSelected() {
		ItemStack item = createItem(Material.BARRIER,
				1,
				ChatColor.RED.toString() + ChatColor.BOLD.toString() + "選択済",
				"メインパスとして選択済");
		return item;
	}
}
