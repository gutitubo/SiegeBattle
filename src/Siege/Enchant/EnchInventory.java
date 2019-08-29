package Siege.Enchant;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import Siege.SiegePlayer.SiegePlayer;

public class EnchInventory {

	public static Inventory getEnchInventory(SiegePlayer sp) {
		Inventory inv = Bukkit.createInventory(null, 9, "NAME"); //TODO インベントリネームを追加する
		if (sp == null) return inv;
		Player p = sp.getPlayer();
		int level = p.getLevel();
		return inv;
	}

	public static ArrayList<ItemStack> suggestCalc(SiegePlayer sp, ItemStack hand) {
		ArrayList<ItemStack> itemAry = new ArrayList<>();

		return itemAry;
	}

	public static ArrayList<Enchants> enchantCalc(int level, EnchantType type) {
		ArrayList<Enchants> enchAry = new ArrayList<>();

		return enchAry;
	}
}
