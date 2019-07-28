package Siege.SiegeEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import Siege.SiegeCore.SiegeGame;

public class RespawnEvent implements Listener{
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		SiegeGame sg = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (sg == null) return;
		if (sg.getPhase() >= 2) {
			Player p = e.getPlayer();
			if (sg.getBlueTeam().isMember(p)) {
				e.setRespawnLocation(sg.getSiegeStage().getBlueSpawnCenter());
			} else if (sg.getRedTeam().isMember(p)) {
				e.setRespawnLocation(sg.getSiegeStage().getRedSpawnCenter());
			}
		}
	}
}
