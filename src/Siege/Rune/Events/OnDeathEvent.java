package Siege.Rune.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import Siege.Rune.Linkage;
import Siege.Rune.RandomPotion;
import Siege.Rune.RuneScheduler;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;
import net.md_5.bungee.api.ChatColor;

public class OnDeathEvent implements Listener {

	@EventHandler
	public void onDeadByPlayer(PlayerDeathEvent e) {
		Player dead = e.getEntity();
		Player killer = dead.getKiller();

		RuneScheduler.setCanSeeYouAll(dead);

		if (killer == null) {
			return;
		}

		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(dead)) return;
		SiegePlayer vi = game.getSiegePlayer(dead);
		vi.getStats().addDeathCount();

		if (!game.isSiegePlayer(killer)) return;
		SiegePlayer sp = game.getSiegePlayer(killer);
		sp.getStats().addKillCount();

		if (sp.hasRune(Runes.MAGIC_KILLPOTION)) {
			killer.getInventory().addItem(RandomPotion.getRandom());
		}

		for (Linkage l : Linkage.getList()) {
			if(l.getOwner().getPlayer().equals(vi.getPlayer())) l.remove();
		}
	}

	@EventHandler
	public void onRespawed(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(p)) return;

		SiegePlayer sp = game.getSiegePlayer(p);
		p.teleport(sp.getTeam().getSpawnLocation());

		/* === 生命保険の処理 === */
		if (sp.getInsurance() > 0) {
			sp.withdrawInsurance();
			sp.getPlayer().sendMessage("");
			sp.getPlayer().sendMessage(ChatColor.GOLD + "保険が振り込まれた!");
			sp.getPlayer().sendMessage("");
		}
	}
}
