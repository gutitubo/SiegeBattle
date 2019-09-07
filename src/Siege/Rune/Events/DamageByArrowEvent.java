package Siege.Rune.Events;

import static Lib.Parameters.*;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class DamageByArrowEvent implements Listener {

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		Player victim = null;
		if (ent instanceof Player) {
			victim = (Player) ent;
		} else {
			return;
		}

		Arrow arw = null;
		if (e.getDamager() instanceof Arrow) {
			arw = (Arrow) e.getDamager();
		} else {
			return;
		}

		Player str = null;
		if (arw.getShooter() instanceof Player) {
			str = (Player) arw.getShooter();
		}
		if (str == null) return; 

		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		SiegePlayer s_str = null;
		SiegePlayer s_vim = null;
		if (!game.isSiegePlayer(str)) return;
		if (!game.isSiegePlayer(victim)) return;
		s_str = game.getSiegePlayer(str);
		s_vim = game.getSiegePlayer(victim);
		
		/* ここから弓 */
		
		/* === 遠距離攻撃強化 === */
		if (s_str.hasRune(Runes.BATTLE_ARROWDAMAGE)) {
			e.setDamage(e.getDamage() + RUNE_ARROWDAMAGE_VALUE);
		}
	}

}
