package Lib;

import static Lib.ItemFactory.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class SiegeLib {

	private SiegeLib() {}

	public static void siegeMsg(String msg) {
		Bukkit.broadcastMessage(siegeString(msg));
	}

	public static String siegeString(String msg) {
		return ChatColor.GOLD + "[SiegeBattle] "+ ChatColor.RESET + msg;
	}

	public static BarColor chatColorToBarColor (ChatColor c) {
		BarColor b = BarColor.RED;
		return b;
	}

	public static void teleportSpawn(Player p) {
		p.teleport(new Location(Bukkit.getWorld("Lobby"), 0.5, 31.5, 0.5));
	}

	public static void setInventoryFlame(Inventory inv, ItemStack item) {
		 for (int i = 0; i<9; i++) {
			 inv.setItem(i, item);
			 inv.setItem(i+36, item);
		 }
		 for (int i = 0; i<37; i+=9) {
			 inv.setItem(i, item);
			 inv.setItem(i+8, item);
		 }
		 inv.setItem(47, getRedStoneShopIcon());
		 inv.setItem(48, getLapisShopIcon());
		 inv.setItem(49, getGambleIcon());
		 inv.setItem(50, getDiamondShopIcon());
		 inv.setItem(51, getEmeraldShopIcon());
	}

	public static void giveEffect(Player p, PotionEffect give) {
		if (p.hasPotionEffect(give.getType())) {
			PotionEffect pe = p.getPotionEffect(give.getType());
			if (pe.getAmplifier() > give.getAmplifier()) return;
			if (pe.getAmplifier() < give.getAmplifier()) {
				p.removePotionEffect(pe.getType());
			} else if (pe.getDuration() < give.getDuration()) {
				p.removePotionEffect(pe.getType());
			}
		}
		p.addPotionEffect(give);
	}
}
