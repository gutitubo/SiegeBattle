package Siege.Rune;

import static Lib.Parameters.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import Siege.SiegeCore.SiegeGame;
import Siege.SiegeException.IkaretaPhaseException;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;

public class RuneScheduler extends BukkitRunnable{

	private SiegeGame game;

	public RuneScheduler(SiegeGame game) {
		this.game = game;
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		if (game == null) this.cancel();
		if (game.getPhase() == 0) this.cancel();

		for (SiegePlayer sp : game.getJoinedPlayer()) {
			Player p = sp.getPlayer();

			try {
				/* === コア採掘強化 === */
				if (sp.hasRune(Runes.BATTLE_COREDIGSPEED) && sp.getDistanceWhileEnemyCore() <= RUNE_COREDIG_DISTANCE) {
					if (p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
						p.removePotionEffect(PotionEffectType.FAST_DIGGING);
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 5, RUNE_COREDIG_AMP));
				}

				/* === ホームガードの処理 === */
				if (sp.hasRune(Runes.SWIFT_HOMEGUARD) && sp.getDistanceWhileOwnCore() <= RUNE_HOMEGUARD_DISTANCE) {
					if (p.hasPotionEffect(PotionEffectType.SPEED)) {
						p.removePotionEffect(PotionEffectType.SPEED);
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, RUNE_HOMEGUARD_AMP));
				}

				/* === 逃げ足の処理 === */
				if (sp.hasRune(Runes.SWIFT_RUNNER) && p.getHealth() <= RUNE_RUNNER_HEALTH) {
					if (p.hasPotionEffect(PotionEffectType.SPEED)) {
						p.removePotionEffect(PotionEffectType.SPEED);
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 2, RUNE_RUNNER_AMP));
				}

				/* === 孤軍奮闘の処理 === */
				if (sp.hasRune(Runes.BATTLE_SOLOFIGHTER) && sp.getDistanceWhileOwnCore() <= RUNE_SOLOFIGHTER_RANGE) {
					boolean flag = true;
					for (SiegePlayer ally : sp.getTeam().getSiegePlayerList().getPlayerList()) { //ひとりかどうか
						if (ally.getDistanceWhileOwnCore() <= RUNE_SOLOFIGHTER_RANGE && !ally.equals(sp)) {
							flag = false; //ひとりじゃない
						}
					}
					if (flag) {
						sp.setAdditionalDefend(RUNE_SOLOFIGHTER_DEF);
						if (sp.hasRune(Runes.MAGIC_KEYSTONE)) {
							if (p.hasPotionEffect(PotionEffectType.REGENERATION)) {
								if (p.getPotionEffect(PotionEffectType.REGENERATION).getDuration() < (20 * 3)) {
									p.removePotionEffect(PotionEffectType.REGENERATION);
								}
							}
							p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 3, RUNE_SOLOFIGHTER_REG));
						}
					} else {
						sp.setAdditionalDefend(0);
					}
				}
				sp.statusReflect();

				/* === カモフラージュの処理 === */
				if (sp.hasRune(Runes.SWIFT_CAMO)) {
					boolean flag = false;
					SiegeTeam team = sp.getTeam().equals(game.getRedTeam()) ? game.getBlueTeam() : game.getRedTeam();
					for (SiegePlayer enem : team.getSiegePlayerList().getPlayerList()) { //ひとりかどうか
						double camo_range = sp.hasRune(Runes.MAGIC_KEYSTONE) ? RUNE_CAMO_RANGE : RUNE_CAMO_RANGE_ENHANCED;
						if (enem.getPlayer().getLocation().distance(p.getLocation()) > camo_range) {
							flag = true; //ひとり
						}
					}
					if (flag) {
						setHideFromEnemy(sp);
						if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
							if (p.getPotionEffect(PotionEffectType.INVISIBILITY).getDuration() < 30) {
								p.removePotionEffect(PotionEffectType.INVISIBILITY);
							}
						}
						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 2, 0));
					} else {
						setCanSeeYouAll(sp.getPlayer());
					}
				}

			} catch (IkaretaPhaseException e) {
			}
		}
	}

	public static void setHideFromEnemy(SiegePlayer sp) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		SiegeTeam team = sp.getTeam().equals(game.getRedTeam()) ? game.getBlueTeam() : game.getRedTeam();
		for (SiegePlayer enem : team.getSiegePlayerList().getPlayerList()) {
			enem.getPlayer().hidePlayer(Siege.SiegeBattleMain.siegeBattleMain, sp.getPlayer());
		}
	}

	public static void setCanSeeYouAll(Player p) {
		for (Player targ : Bukkit.getOnlinePlayers()) {
			targ.showPlayer(Siege.SiegeBattleMain.siegeBattleMain, p);
		}
	}
}
