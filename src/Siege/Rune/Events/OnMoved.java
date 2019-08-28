package Siege.Rune.Events;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Lib.Parameters;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class OnMoved implements Listener{

	@EventHandler
	public void onMoved(PlayerMoveEvent e) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (game.isSiegePlayer(e.getPlayer())) return;
		Player p = e.getPlayer();
		SiegePlayer sp = game.getSiegePlayer(p);

		if (sp.hasRune(Runes.SWIFT_WINDY)) {
			if (game.getRedTeam().isMember(p)) {
				for (SiegePlayer red : game.getRedTeam().getSiegePlayerList().getPlayerList()) {
					double distance = p.getLocation().distance(red.getPlayer().getLocation());
					if (sp.hasRune(Runes.MAGIC_KEYSTONE)) {
						if (distance <= Parameters.RUNE_WINDY_RANGE_ENHANCED) {
							giveOikaze(red, true);
						}
					} else {
						if (distance <= Parameters.RUNE_WINDY_RANGE) {
							giveOikaze(red, false);
						}
					}
				}
			} else if (game.getBlueTeam().isMember(p)) {
				for (SiegePlayer blue : game.getBlueTeam().getSiegePlayerList().getPlayerList()) {
					double distance = p.getLocation().distance(blue.getPlayer().getLocation());
					if (sp.hasRune(Runes.MAGIC_KEYSTONE)) {
						if (distance <= Parameters.RUNE_WINDY_RANGE_ENHANCED) {
							giveOikaze(blue, true);
						}
					} else {
						if (distance <= Parameters.RUNE_WINDY_RANGE) {
							giveOikaze(blue, false);
						}
					}
				}
			}
		}
	}

	public void giveOikaze(SiegePlayer sp, boolean enhanced) {
		Player p = sp.getPlayer();
		if (p.hasPotionEffect(PotionEffectType.SPEED)) {
			if (p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() > Parameters.RUNE_WINDY_AMP) {
				return;
			} else {
				p.removePotionEffect(PotionEffectType.SPEED);
			}
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Parameters.RUNE_WINDY_DUR_ENHANCED, Parameters.RUNE_WINDY_AMP));
		p.getWorld().spawnParticle(Particle.CLOUD, p.getLocation(), 3);
	}
}
