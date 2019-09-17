package Siege.SiegeEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import Siege.Rune.RuneInventory;
import Siege.SiegeCore.SiegeGame;

public class UseLobbyItem implements Listener {

	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null || game.getPhase() < 1) {
			Player p = e.getPlayer();
			if (!Siege.SiegeBattleMain.preSelect.containsKey(p)) return;
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				p.openInventory(RuneInventory.getRuneInventory(p));
			}
		}
	}
}
