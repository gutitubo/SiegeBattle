package Siege.SiegeEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginEvent implements Listener{

	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Siege.SiegeBattleMain.siegeBattleMain.getGame()==null || Siege.SiegeBattleMain.siegeBattleMain.getGame().getPhase() <= 1) { //ゲームが始まっていない場合
			Lib.SiegeLib.teleportSpawn(p);
			p.getInventory().clear();
		} else { //始まってる場合

		}
	}
}
