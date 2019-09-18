package Siege.Rune.Events;
import static Lib.Parameters.*;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import Lib.Parameters;
import Siege.SiegeBattleMain;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class OnArrowShoot implements Listener {

	@EventHandler
	public void onShoot(ProjectileLaunchEvent e) {
		e.getEntity().getShooter();
		if (!(e.getEntity() instanceof Arrow)) return;
		if (!(e.getEntity().getShooter() instanceof Player)) return;

		Player str = (Player) e.getEntity().getShooter();
		Arrow arw = (Arrow) e.getEntity();

		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;

		if (!game.isSiegePlayer(str)) return;
		SiegePlayer sp = game.getSiegePlayer(str);

		/* === テスト用に実装 TODO 実装版からは消す === */
		//		if (arw.isCritical()) arw.setGravity(false);
		//		BukkitRunnable arwRun = new BukkitRunnable() {
		//			int count = 0;
		//			@Override
		//			public void run() {
		//				// TODO 自動生成されたメソッド・スタブ
		//				arw.getWorld().spawnParticle(Particle.CRIT_MAGIC, arw.getLocation(), 1, 0, 0, 0);
		//				count ++;
		//				if (count > 200) {
		//					arw.remove();
		//					this.cancel();
		//				}
		//			}
		//		};
		//		arwRun.runTaskTimer(Siege.SiegeBattleMain.siegeBattleMain, 0, 1);

		/* === 明鏡止水の処理 === */
		if (sp.hasRune(Runes.COLLECT_ARROWSIGHT)) {
			sp.getPlayer().setCooldown(Material.BOW, Parameters.RUNE_ARROWSIGHT_CD);
		}

		/* === 狙撃手の処理 === */
		if (sp.hasRune(Runes.BATTLE_SNIPER)) {
			if (arw.isCritical()) {
				arw.setGravity(false);
				if (sp.hasRune(Runes.MAGIC_KEYSTONE)) {
					arw.setVelocity(arw.getVelocity().multiply(RUNE_SNIPER_SPI_ENHANCED));
				} else {
					arw.setVelocity(arw.getVelocity().multiply(RUNE_SNIPER_SPI));
				}
				sp.getPlayer().setCooldown(Material.BOW, Parameters.RUNE_SNIPER_CD);
				BukkitRunnable runner = new BukkitRunnable() {
					int count = 0;
					@Override
					public void run() {
						count ++;
						arw.getWorld().spawnParticle(Particle.END_ROD, arw.getLocation(), 1, 0, 0, 0, 0);
						if (count > 20 * 20) {
							arw.remove();
							this.cancel();
						}
					}
				};
				runner.runTaskTimer(SiegeBattleMain.siegeBattleMain, 0, 1);
			}
		}

		/* === 屋をハナッタナオロアｋジョイラｊコ === */

		if (sp.hasRune(Runes.MAGIC_MAGICARROW)) {
			ItemStack off = str.getInventory().getItemInOffHand();
			if (off != null && (off.getType().equals(Material.POTION) || off.getType().equals(Material.SPLASH_POTION))) {
				PotionMeta meta = (PotionMeta) off.getItemMeta();
				arw.setBasePotionData(meta.getBasePotionData());
				for (PotionEffect eff : meta.getCustomEffects()) {
					arw.addCustomEffect(eff, false);
				}
				str.getInventory().setItemInOffHand(new ItemStack(Material.GLASS_BOTTLE));
			}
		}
	}

	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		if (!(e.getEntity() instanceof Arrow)) return;
		Arrow arw = (Arrow)e.getEntity();
		if (!(arw.getShooter() instanceof Player)) return;
		Player str = (Player) arw.getShooter();
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(str)) return;
		SiegePlayer sp = game.getSiegePlayer(str);

		/* === 明鏡止水の処理 === */
		if (sp.hasRune(Runes.COLLECT_ARROWSIGHT)) {
			arw.setGlowing(true);
			arw.setColor(Color.BLACK);
			arw.setPickupStatus(PickupStatus.DISALLOWED);

			BukkitRunnable runner = new BukkitRunnable() {
				int count = 0;
				double range = sp.hasRune(Runes.MAGIC_KEYSTONE) ? RUNE_ARROWSIGHT_RANGE : RUNE_ARROWSIGHT_RANGE_ENHANCED;
				@Override
				public void run() {
					count ++;
					for (SiegePlayer tg : game.getAllPlayer()) {
						if (!tg.getTeam().equals(sp.getTeam())
								&& tg.getPlayer().getLocation().distance(arw.getLocation()) < range) {
							Lib.SiegeLib.giveEffect(tg.getPlayer(),
									new PotionEffect(PotionEffectType.GLOWING, 20 * 2, 0));
						}
					}
					if (count > 25) {
						arw.getWorld().spawnParticle(Particle.SMOKE_NORMAL, arw.getLocation(), 5, 0.1, 0.1, 0.1, 1);
						arw.remove();
						this.cancel();
					}
				}
			};
			runner.runTaskTimer(SiegeBattleMain.siegeBattleMain, 0, 4);
		}
	}

}
