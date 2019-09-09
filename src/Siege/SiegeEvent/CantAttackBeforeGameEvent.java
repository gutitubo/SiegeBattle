package Siege.SiegeEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import Siege.SiegeCore.SiegeGame;

public class CantAttackBeforeGameEvent implements Listener {

	@EventHandler
	public void onDamaged(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null || game.getPhase() < 1) e.setCancelled(true);
	}

}
