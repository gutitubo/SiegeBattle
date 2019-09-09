package Siege.SiegeEvent;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import Siege.SiegeCore.SiegeGame;
import Siege.SiegeItem.SiegeItemSet;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;

public class LateJoinAndLeave implements Listener {

	@EventHandler
	public void rageQuit(PlayerQuitEvent e) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (game.getPhase() > 0 && game.isSiegePlayer(e.getPlayer())) {
			holdRageQuitter(e.getPlayer(), game, game.getSiegePlayer(e.getPlayer()).getTeam());
		}
	}

	@EventHandler
	public void lateJoin(PlayerJoinEvent e) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (game.getPhase() > 0) {
			lateJoinPlayer(e.getPlayer(), game);
		}
	}

	public void holdRageQuitter(Player p, SiegeGame game, SiegeTeam team) {
		game.addLeaver(p, team); //TODO チーム登録
		game.getSiegePlayer(p).getTeam().getSiegePlayerList().getPlayerList().remove(game.getSiegePlayer(p));
		p.setHealth(0.01);
		p.damage(2);
	}

	@SuppressWarnings("deprecation")
	public void lateJoinPlayer(Player p, SiegeGame game) {
		SiegePlayer sp;
		SiegeTeam team = null;
		if (game.isLeaver(p)) {
			/* すでに参加したことある人だった場合の処理 */
			team = game.getLeaverTeam(p);
		} else {
			/* 途中参加だった場合の処理 */
			team = getSukuneeTeam();
			for (ItemStack item : SiegeItemSet.teamItem(Color.PURPLE)) {
				p.getInventory().addItem(item);
			}
		}
		sp = new SiegePlayer(p);
		team.getInfoBar().toBossbar().addPlayer(p);
		sp.setTeam(team);
		team.getTeam().addPlayer(p);
		team.getSiegePlayerList().getPlayerList().add(sp);
		if (game.getPhase() > 1) {
			team.gotoSpawnHitori(sp);
		}
	}

	public SiegeTeam getSukuneeTeam() {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		SiegeTeam team1 = game.getRedTeam();
		SiegeTeam team2 = game.getBlueTeam();

		if (team1.getSiegePlayerList().getPlayerList().size() > team2.getSiegePlayerList().getPlayerList().size()) {
			return team2;
		} else if (team2.getSiegePlayerList().getPlayerList().size() > team1.getSiegePlayerList().getPlayerList().size()) {
			return team1;
		} else {
			/* === ランダム処理 === */
			Random rnd = new Random();
			return rnd.nextDouble() > 0.5 ? team1 : team2 ;
		}
	}
}
