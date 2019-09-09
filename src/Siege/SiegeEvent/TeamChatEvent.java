package Siege.SiegeEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class TeamChatEvent implements Listener {

	@EventHandler
	public void teamChat(AsyncPlayerChatEvent e) {
		SiegeGame sg =  Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (sg == null) return;

		Player p = e.getPlayer();
		String msg = e.getMessage();

		for (SiegePlayer s : sg.getRedTeam().getSiegePlayerList().getPlayerList()) {
			if(p.equals(s.getPlayer())) { //垢ちーム
				redTeamChat(p, msg);
				e.setCancelled(true);
			}
		}
		for (SiegePlayer s : sg.getBlueTeam().getSiegePlayerList().getPlayerList()) {
			if(p.equals(s.getPlayer())) { //青ちーム
				blueTeamChat(p, msg);
				e.setCancelled(true);
			}
		}
	}

	public void redTeamChat(Player p, String s) {
		SiegeGame sg =  Siege.SiegeBattleMain.siegeBattleMain.getGame();
		String msg = "";
		ChatColor rc = sg.getRedTeam().getColor();
		boolean isAll = false;

		if (s.startsWith("!")) { //全体チャット
			msg = s.substring(1);
			isAll = true;
		}  else {
			msg = s;
		}
		if (!isAll) {
			for (SiegePlayer sp : sg.getRedTeam().getSiegePlayerList().getPlayerList()) {
					sp.getPlayer().sendMessage(ChatColor.GRAY + "[TEAM]"
							+ ChatColor.WHITE + "<"
							+ rc + p.getDisplayName()
							+ ChatColor.WHITE + "> "
							+ msg);
			}
		} else {
			Bukkit.broadcastMessage(ChatColor.GOLD + "[ALL]"
							+ ChatColor.WHITE + "<"
							+ rc + p.getDisplayName()
							+ ChatColor.WHITE + "> "
							+ msg);
		}
	}

	public void blueTeamChat(Player p, String s) {
		SiegeGame sg =  Siege.SiegeBattleMain.siegeBattleMain.getGame();
		String msg = "";
		ChatColor bc = sg.getBlueTeam().getColor();

		boolean isAll = false;

		if (s.startsWith("!")) { //全体チャット
			msg = s.substring(1);
			isAll = true;
		}  else {
			msg = s;
		}
		if (!isAll) {
			for (SiegePlayer sp : sg.getBlueTeam().getSiegePlayerList().getPlayerList()) {
					sp.getPlayer().sendMessage(ChatColor.GRAY + "[TEAM]"
							+ ChatColor.WHITE + "<"
							+ bc + p.getDisplayName()
							+ ChatColor.WHITE + "> "
							+ msg);
			}
		} else {
			Bukkit.broadcastMessage(ChatColor.GOLD + "[ALL]"
							+ ChatColor.WHITE + "<"
							+ bc + p.getDisplayName()
							+ ChatColor.WHITE + "> "
							+ msg);
		}
	}
}
