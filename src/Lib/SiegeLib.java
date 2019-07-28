package Lib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;

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
}
