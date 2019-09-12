package Siege.Rune.Events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class CreateLinkage implements Listener {

	@EventHandler
	public void Create(PlayerInteractEvent e) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(e.getPlayer())) return;

		Player p = e.getPlayer();
		SiegePlayer sp = game.getSiegePlayer(p);

		/* === LINKAGEの処理 === */
		if (sp.hasRune(Runes.MAGIC_LINKAGE)) {
			Block b = null;
			if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				b = e.getClickedBlock();
			}
			if (b == null) return;
			Location l = p.getLocation();
			l.add(0, -1, 0);
			if (l.getBlock().equals(b)) {
				new CreateRun(p).run();
			}
		}
	}

	public class CreateRun extends BukkitRunnable {
		Player p;
		CreateRun(Player p) {
			this.p = p;
		}
		@Override
		public void run() {
			// TODO 自動生成されたメソッド・スタブ

		}
	}
}
