package Siege.Rune.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import Siege.Rune.RandomPotion;
import Siege.Rune.RuneScheduler;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class OnDeathEvent implements Listener {

	@EventHandler
	public void onDeadByPlayer(PlayerDeathEvent e) {
		Player dead = e.getEntity();
		Player killer = dead.getKiller();

		RuneScheduler.setCanSeeYouAll(dead);

		if (killer == null) {
			return;
		}

		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(killer)) return;

		SiegePlayer sp = game.getSiegePlayer(killer);

		if (sp.hasRune(Runes.MAGIC_KILLPOTION)) {
			killer.getInventory().addItem(RandomPotion.getRandom());
		}
	}
}
