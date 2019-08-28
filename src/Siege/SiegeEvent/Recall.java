package Siege.SiegeEvent;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import Lib.Parameters;
import Siege.SiegeBattleMain;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import net.md_5.bungee.api.ChatColor;

public class Recall implements Listener{

	private static HashMap<Player, Recall> recalling = new HashMap<Player, Recall>();

	private Player player;
	protected int count;

	public Recall() {}

	private Recall(Player p) {
		player = p;
		SiegeGame game = SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(p)) return;
		Location teamCore;
		if (game.getBlueTeam().isMember(p)) { //青チームだったら
			teamCore = game.getSiegeStage().getBlueCore();
		} else if (game.getRedTeam().isMember(p)){ //赤チーム
			teamCore = game.getSiegeStage().getRedCore();
		} else {
			return;
		}
		
		setCount(getRecallDelay(p, game.getSiegeStage().getMiddleOfMap(), teamCore));
		recallStart();
		p.sendMessage(ChatColor.DARK_RED+ "" + getCount() + "秒後にリコールします");
	}

	public static Recall createInstance(Player p) {

		if (!recalling.containsKey(p)) {
			Recall r = new Recall(p);
			recalling.put(p, r);
			return r;
		} else {
			p.sendMessage(ChatColor.RED + "すでにリコールを開始しています");
			return null;
		}
	}

	public void recallStart () {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!recalling.containsKey(player)) {
					this.cancel();
				}
				if (count <= 0) { //リコール完了処理
					player.sendMessage(ChatColor.DARK_RED + "RECAaaaaaaaaaaaaAAL");
					/*
					 * ここにリコール処理つける
					 */
					SiegeGame game = SiegeBattleMain.siegeBattleMain.getGame();
					if (game.getBlueTeam().isMember(player)) { //青チームだったら
						player.teleport(game.getSiegeStage().getBlueSpawnCenter());
					} else if (game.getRedTeam().isMember(player)){ //赤チーム
						player.teleport(game.getSiegeStage().getRedSpawnCenter());
					} else {

					}
					player.setHealth(player.getHealthScale());
					player.setFoodLevel(20);
					/*
					 * デバフ解除処理入れる
					 */

					player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5F, 0.5F);
					recalling.remove(player);
					this.cancel();
				}
				count--;
			}
		}.runTaskTimer(SiegeBattleMain.siegeBattleMain, 0, 20);
	}

	//動きやがったおｔきの処理
	@EventHandler
	public void onMoved(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (e.getFrom().equals(e.getTo())) {
			return;
		}

		if (recalling.containsKey(p)) {
			p.sendMessage(ChatColor.RED + "動いたためリコールをキャンセルしました");
			recalling.remove(p);
		}
	}

	//抜けやがった時の処理
	@EventHandler
	public void onQuited (PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (recalling.containsKey(p)) {
			recalling.remove(p);
		}
	}

	public static int getRecallDelay(Player p,Location mid, Location core) {
		int delay = 10;
		double ten = mid.distance(core);
		double pl = p.getLocation().distance(core);
		if (pl >= ten) {
			delay = 10;
		} else {
			delay = (int)(pl/ten * 10);
		}
		if (SiegeBattleMain.siegeBattleMain.getGame().getSiegePlayer(p).hasRune(Runes.COLLECT_RECALL)) {
			if (delay > Parameters.RUNE_RECALL_AMOUNT) {
				return Parameters.RUNE_RECALL_AMOUNT;
			}
		}
		return delay;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
