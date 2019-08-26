package Siege.SiegePlayer;

import org.bukkit.entity.Player;

import Siege.Rune.RuneCategory;
import Siege.Rune.Runes;
import Siege.SiegeTeam.SiegeTeam;

public class SiegePlayer {

	private Player player; //本人
	private SiegeTeam team; //所属

	private RuneCategory mainPath;
	private RuneCategory subPath;

	private Runes[] currentRunes;

	//こんすとらくたー
	public SiegePlayer() {
	}

	public SiegePlayer(Player player) {
		this.player = player;
		currentRunes = new Runes[5];
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

	public RuneCategory getMainPath() {
		return this.mainPath;
	}

	public void setMainPath(RuneCategory category) {
		this.mainPath = category;
	}

	public RuneCategory getSubPath() {
		return this.subPath;
	}

	public void setSubPath(RuneCategory category) {
		this.subPath = category;
	}

	public Runes[] getCurrentRunes() {
		return this.currentRunes;
	}

	public boolean isHolder(Runes rune) {
		if (currentRunes == null) {
			return false;
		}
		for (Runes r : currentRunes) {
			if (rune == r) return true;
		}
		return false;
	}

	public void clearRune() {
		this.mainPath = null;
		this.subPath = null;
		this.currentRunes = new Runes[5];
	}

	public String[] getRunesString() {
		String[] str = new String[5];
		int i = 0;
		for (Runes r: this.currentRunes) {
			str[i] = r.toString();
			i++;
		}
		return str;
	}

	public void showRunesString(Player p) {
		String[] str = getRunesString();
		p.sendMessage("--- " + this.getPlayer().getDisplayName() + "'s Rune ---");
		p.sendMessage("Tier1: [" + str[0] + "]");
		p.sendMessage("Tier2: [" + str[1] + "], [" + str[2] + "], [" + str[3] + "], [" + str[4] + "]");
	}
}
