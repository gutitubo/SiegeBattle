package Siege.Rune.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import Lib.Parameters;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegeException.IkaretaPhaseException;
import Siege.SiegePlayer.SiegePlayer;

public class PlayerAttackEvent implements Listener {

	@EventHandler
	public void onAttacked(EntityDamageByEntityEvent e) {
		Player p = null;
		if (e.getDamager() instanceof Player) {
			p = (Player) e.getDamager();
		} else {
			return;
		}

		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (game.getPhase() < 2) return;
		if (game.isSiegePlayer(p)) return;
		SiegePlayer sp = game.getSiegePlayer(p);

		/* ---------- 攻撃者側用ルーン --------- */
		if (sp.hasRune(Runes.BATTLE_MELEEDAMAGE)) { //近接攻撃強化の場合
			e.setDamage(e.getDamage() + Parameters.RUNE_MELEEDAMAGE_VALUE);
		}

		/* ---------- 被害者用ルーン ---------- */
		Player v = null;
		if (e.getEntity() instanceof Player) {
			v = (Player) e.getEntity();
		} else {
			return;
		}
		SiegePlayer vic = game.getSiegePlayer(v);

		if (vic.hasRune(Runes.BATTLE_TENACITY)) { //執念
			e.setDamage(e.getDamage() + Parameters.RUNE_MELEEDAMAGE_VALUE);
		}
		if (sp.hasRune(Runes.BATTLE_ANTIDEFENDER)) { //対防衛特攻の場合
			try {
				if(vic.getDistanceWhileOwnCore() <= 10)
				e.setDamage(e.getDamage() + Parameters.RUNE_ANTIDEFENDER_VALUE);
			} catch (IkaretaPhaseException e1) {
			};
		}
		if (sp.hasRune(Runes.BATTLE_ANTIATTACKER)) { //対攻撃特攻の場合
			try {
				if(vic.getDistanceWhileEnemyCore() <= 10)
				e.setDamage(e.getDamage() + Parameters.RUNE_ANTIATTACKER_VALUE);
			} catch (IkaretaPhaseException e1) {
			};
		}

		if (vic.hasRune(Runes.MAGIC_EXPARMOR)) { //経験値アーマー
			int level = v.getLevel();
			double guard = (level / 10) * 0.1;
			if (guard > 0.5) {
				guard = 0.5;
			}
			e.setDamage(e.getDamage() - (e.getDamage() * guard));
		}
	}
}
