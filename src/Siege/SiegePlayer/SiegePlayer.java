package Siege.SiegePlayer;

import org.bukkit.entity.Player;

import Siege.SiegeTeam.SiegeTeam;

public class SiegePlayer {
	
	private Player player; //本人
	private SiegeTeam team; //所属

	//こんすとらくたー
	public SiegePlayer() {
	}
	
	public SiegePlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public String toString() {
		String ret = team.getColor() + player.getDisplayName();
		return ret;
	}
	
	
	//せったーげったーーー
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public SiegeTeam getTeam() {
		return team;
	}

	public void setTeam(SiegeTeam team) {
		this.team = team;
	}
}
