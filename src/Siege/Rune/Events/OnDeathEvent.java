package Siege.Rune.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeathEvent implements Listener {

	@EventHandler
	public void onDeadByPlayer(PlayerDeathEvent e) {
		Player dead = e.getEntity();
		Player killer = dead.getKiller();

		if (killer == null) {
			return;
		}
	}
}
