package Siege.Rune.Events;

import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import Lib.Parameters;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class ToggleFlight implements Listener {

	@EventHandler
	public void toggleFlight (PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR)) {
			e.setCancelled(false);
			return;
		}
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(p)) return;

		SiegePlayer sp = game.getSiegePlayer(p);
		e.setCancelled(true);
		p.setFlying(false);

		/* === ダブルジャンプの処理 === */
		if (sp.hasRune(Runes.SWIFT_DOUBLEJUMP) && p.getFoodLevel() >= Parameters.RUNE_DOUBLEJUMP_COST) {
			Vector v = p.getVelocity();
			Double power = sp.hasRune(Runes.MAGIC_KEYSTONE) ? Parameters.RUNE_DOUBLEJUMP_POW_ENHANCED : Parameters.RUNE_DOUBLEJUMP_POW;
			v.multiply(Parameters.RUNE_DOUBLEJUMP_MUL);
			v.setY(power);
			p.setVelocity(v);
			p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 10, 0.3, 0, 0.3);
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 0.5F, 1F);
			p.setFoodLevel(p.getFoodLevel() - Parameters.RUNE_DOUBLEJUMP_COST);
		}
	}
}
