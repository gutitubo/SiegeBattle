package Siege.SiegeCore;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;

public class SiegeInformationBar {
	
	private BossBar bossBar;
	
	private String barTitle;
	private BarColor color;
	private BarStyle style;
	
	public SiegeInformationBar(String barTitle, BarColor color, BarStyle style) {
		bossBar = Bukkit.getServer().createBossBar(barTitle, color, style);
		bossBar.setVisible(true);
	}
	
	public void setToTeam(SiegeTeam team) {
		for(SiegePlayer sp : team.getSiegePlayerList().getPlayerList()) {
			bossBar.addPlayer(sp.getPlayer());
		}
	}
	
	public void remove() {
		bossBar.removeAll();
	}
	
	public void setProgress(double progress) {
		bossBar.setProgress(progress);
	}
	
	public BossBar toBossbar() {
		return bossBar;
	}

	public String getBarTitle() {
		return barTitle;
	}

	public void setBarTitle(String barTitle) {
		this.barTitle = barTitle;
		bossBar.setTitle(barTitle);
	}

	public BarColor getColor() {
		return color;
	}

	public void setColor(BarColor color) {
		this.color = color;
		bossBar.setColor(color);
	}

	public BarStyle getStyle() {
		return style;
	}

	public void setStyle(BarStyle style) {
		this.style = style;
		bossBar.setStyle(style);
	}

}
