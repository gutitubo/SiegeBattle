package Siege.Enchant.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class onEnchant implements Listener {

	@EventHandler
	public void onEnchanted(EnchantItemEvent e) {
		Player p = e.getEnchanter();
		if (e.getExpLevelCost() == 3 && p.getLevel() >= 30) {
			p.setLevel(p.getLevel() - 30);
		}
	}

}
