package Lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getShopArrow() {
		ItemStack item = createItem(Material.ARROW,
				1,
				ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "矢",
				"これがないと始まらない");
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
				ChatColor.GOLD + "Shears +2",
				Enchantment.DIG_SPEED,
				2,
				"めっちゃはやい");
		return item;
	}
}
