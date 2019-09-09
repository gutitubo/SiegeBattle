package Siege.Enchant.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class onEnchant implements Listener {

	@EventHandler
	public void onEnchanted(EnchantItemEvent e) {
		Player p = e.getEnchanter();
		int level = p.getLevel() - e.getExpLevelCost();
		p.setLevel(level + e.whichButton() + 1);
	}
}
