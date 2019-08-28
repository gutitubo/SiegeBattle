package Siege.Rune.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class OnDamaged implements Listener {

	@EventHandler
	public void onDamaged(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player p = (Player)e.getEntity();
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(p)) return;
		SiegePlayer sp = game.getSiegePlayer(p);

		if (e.getCause().equals(DamageCause.FALL)) { //転落ダメージ
			if(sp.hasRune(Runes.SWIFT_NOFALL)) { //NOFALLを持っているとき
				p.setFallDistance(0F);
				e.setCancelled(true);
			}
		}
	}
}
