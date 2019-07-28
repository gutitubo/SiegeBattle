package Siege.SiegeEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import net.md_5.bungee.api.ChatColor;

public class SignPlaceEvent implements Listener {
	
	public static final String line = ChatColor.DARK_BLUE + "==============";
	public static final String shop = ChatColor.RED + "Siege Battle";
	
	public static final String normalshopname = "Darkness Shop";
	public static final String expshopname = "Level Shop";
	
	public static final String normalshop = ChatColor.BLACK.toString() + ChatColor.BOLD.toString() + normalshopname;
	public static final String expshop = ChatColor.GREEN + expshopname;
	
	@EventHandler
	public void onPlaced (SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("siegeshop")) {
			e.setLine(0, line);
			e.setLine(1, shop);
			e.setLine(2, normalshop);
			e.setLine(3, line);
		} else if (e.getLine(0).equalsIgnoreCase("expshop")) {
			e.setLine(0, line);
			e.setLine(1, shop);
			e.setLine(2, expshop);
			e.setLine(3, line);
		}
	}

}
