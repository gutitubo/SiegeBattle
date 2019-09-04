package Siege.Rune.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class CancelHungry implements Listener {

	@EventHandler
	public void onHunger(FoodLevelChangeEvent e) {
		Player p = (Player)e.getEntity();
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(p)) return;

		SiegePlayer sp = game.getSiegePlayer(p);

		if (sp.hasRune(Runes.SWIFT_NOHUNGRY)) {
			e.setFoodLevel(20);
			e.setCancelled(true);
		}
	}

}
