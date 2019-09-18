package Siege.Rune.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import Siege.Rune.Linkage;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;
import net.md_5.bungee.api.ChatColor;

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
			if (b.getType().equals(Material.SPAWNER)) return;
			Location l = p.getLocation();
			l.add(0, -1, 0);
			if (l.getBlock().equals(b)) {
				Location bl = new Location(l.getWorld(),
						p.getLocation().getX(), p.getLocation().getY(),p.getLocation().getZ());
				bl.add(0, -1, 0);
				if (!b.getLocation().getBlock().equals(bl.getBlock())) {
					cancelPlace(p);
				}
				if (Siege.SiegeEvent.BreakCancelEvent.isDaijoubu(b)) {
					Linkage.place(new Linkage(game.getSiegePlayer(p), b, b.getType()));
				} else {
					p.sendMessage(ChatColor.RED + "そこはやばい");
				}
			}
		}
	}

	public void cancelPlace(Player p) {
		p.sendMessage(ChatColor.DARK_RED + "設置がキャンセルされました。");
	}

	public class CreateRun extends BukkitRunnable {
		Player p;
		Location l;
		Block b;
		int count = 10;
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();

		CreateRun(Player p) {
			this.p = p;
			this.l = p.getLocation();
		}
		@Override
		public void run() {
			// TODO 自動生成されたメソッド・スタブ
			if (game == null) this.cancel();
			if (game.isSiegePlayer(p)) this.cancel();
			Location bl = new Location(l.getWorld(),
					p.getLocation().getX(), p.getLocation().getY(),p.getLocation().getZ());
			bl.add(0, -1, 0);
			if (b == null) {
				b = bl.getBlock();
			} else if (b != bl.getBlock()) {
				cancelPlace();
			}
			count--;
			if (count == 0) {
				Linkage.place(new Linkage(game.getSiegePlayer(p), b, b.getType()));
			}
		}

		public void cancelPlace() {
			this.cancel();
			p.sendMessage(ChatColor.DARK_RED + "設置がキャンセルされました。");
		}
	}
}
