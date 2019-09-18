package Siege.Rune;

import static Lib.Parameters.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

		/* === プレイヤー依存なしの共通処理 === */

		/* === 共鳴 ポータルエフェクト === */
		for (Linkage link : Linkage.getList()) {
			Location loc = new Location(link.getBlock().getLocation().getWorld(),
					link.getBlock().getLocation().getX(),
					link.getBlock().getLocation().getY(),
					link.getBlock().getLocation().getZ());
			loc.add(0, 1, 0);
			link.getBlock().getWorld().spawnParticle(Particle.DRAGON_BREATH, link.getBlock().getLocation(),
					5, 0.5F, 1F, 0.5F);
		}

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

				/* === 応援団の処理 === */
				int count = 0;
				for (SiegePlayer tar : game.getAllPlayer()) {
					int range = tar.hasRune(Runes.MAGIC_KEYSTONE) ? RUNE_CHEERER_RANGE_ENHANCED : RUNE_CHEERER_RANGE;
					if (sp.getTeam().equals(tar.getTeam()) && tar.hasRune(Runes.BATTLE_CHEERER)
							&& sp.getPlayer().getLocation().distance(tar.getPlayer().getLocation()) < range) {
						count++;
					}
					if (sp.getDistanceWhileOwnCore() < RUNE_CHEERER_OWN_DIS && count > RUNE_CHEERER_OWN_LIM) count = RUNE_CHEERER_OWN_LIM;
				}
				if (sp.getDistanceWhileEnemyCore() < RUNE_CHEERER_ENE_DIS && count > 0) count ++;
				if (sp.getPlayer().getHealth() < RUNE_CHEERER_HLT && count > 0) count ++;
				//count は spの周りにいる応援団の数
				if (count != 0) {
					switch(count) {
					case 5:
						Lib.SiegeLib.giveEffect(p, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 5, RUNE_CHEERER_LV5_AMP));
					case 4:
						Lib.SiegeLib.giveEffect(p, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 5, RUNE_CHEERER_LV4_AMP));
					case 3:
						Lib.SiegeLib.giveEffect(p, new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, RUNE_CHEERER_LV3_AMP));
					case 2:
						Lib.SiegeLib.giveEffect(p, new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 5, RUNE_CHEERER_LV2_AMP));
					case 1:
						Lib.SiegeLib.giveEffect(p, new PotionEffect(PotionEffectType.REGENERATION, 20 * 5, RUNE_CHEERER_LV1_AMP));
						break;
					default:
						break;
					}
				}

				/* === ホームガードの処理 === */
				if (sp.hasRune(Runes.SWIFT_HOMEGUARD) && sp.getDistanceWhileOwnCore() <= RUNE_HOMEGUARD_DISTANCE) {
					if (p.hasPotionEffect(PotionEffectType.SPEED)) {
						p.removePotionEffect(PotionEffectType.SPEED);
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 3, RUNE_HOMEGUARD_AMP));
				}

				/* === 共鳴消す処理 === */
				try {
					for (Linkage l : Linkage.getList()) {
						if(l.getOwner().equals(sp) && !sp.hasRune(Runes.MAGIC_LINKAGE)) l.remove();
					}
				} catch (Exception e) {
				}

				/* === かるわざの処理 === */
				//				if (sp.hasRune(Runes.SWIFT_UNBURDEN) && isZenra(p)) {
				//					sp.setAdditionalSpeed(RUNE_UNBURDEN_AMOUNT);
				//				}

				/* === 逃げ足の処理 === */
				if (sp.hasRune(Runes.SWIFT_RUNNER) && p.getHealth() <= RUNE_RUNNER_HEALTH) {
					if (p.hasPotionEffect(PotionEffectType.SPEED)) {
						p.removePotionEffect(PotionEffectType.SPEED);
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 2, RUNE_RUNNER_AMP));
				}

				/* === ダブルジャンプの処理 === */
				if (sp.hasRune(Runes.SWIFT_DOUBLEJUMP)) {
					sp.getPlayer().setAllowFlight(true);
				} else {
					sp.getPlayer().setAllowFlight(false);
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
					}
				} else {
					sp.setAdditionalDefend(0);
				}
				sp.statusReflect();

				/* === カモフラージュの処理 === */
//				if (sp.hasRune(Runes.SWIFT_CAMO)) {
//					boolean flag = false;
//					SiegeTeam team = sp.getTeam().equals(game.getRedTeam()) ? game.getBlueTeam() : game.getRedTeam();
//					for (SiegePlayer enem : team.getSiegePlayerList().getPlayerList()) { //ひとりかどうか
//						double camo_range = sp.hasRune(Runes.MAGIC_KEYSTONE) ? RUNE_CAMO_RANGE_ENHANCED : RUNE_CAMO_RANGE;
//						if (enem.getPlayer().getLocation().distance(p.getLocation()) > camo_range) {
//							flag = true; //ひとり
//						}
//					}
//					if (flag) {
//						setHideFromEnemy(sp);
//						if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
//							if (p.getPotionEffect(PotionEffectType.INVISIBILITY).getDuration() < 30) {
//								p.removePotionEffect(PotionEffectType.INVISIBILITY);
//							}
//						}
//						p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 2, 0));
//					} else {
//						setCanSeeYouAll(sp.getPlayer());
//					}
//				} else {
//					setCanSeeYouAll(sp.getPlayer());
//				}

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

	public boolean isZenra(Player p) {
		boolean flag = true;
		ItemStack[] items = p.getInventory().getArmorContents();
		for (ItemStack item : items) {
			if (item != null) flag = false;
		}
		return flag;
	}
}
