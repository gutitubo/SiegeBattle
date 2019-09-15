package Siege.Rune.Events;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

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

}
