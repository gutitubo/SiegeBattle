package Siege.SiegeTeam;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Siege.SiegeCore.SiegeInformationBar;
import Siege.SiegeItem.SiegeItemSet;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegePlayer.SiegePlayerList;

public class SiegeTeam {

	private String teamName;
	private SiegePlayerList siegePlayerList;
	private SiegeTeamDetails siegeTeamDetails;
	private ChatColor color;

	private Location spawnLocation;

	private int core;

	private SiegeInformationBar infoBar;

	public SiegeTeam() {

	}

	public SiegeTeam(String teamName, SiegePlayerList siegePlayerList, ChatColor color) {
		this.teamName = teamName;
		this.siegePlayerList = siegePlayerList;
		this.color = color;
		siegeTeamDetails = new SiegeTeamDetails();
		core = 100;
		
		for (SiegePlayer sp : siegePlayerList.getPlayerList()) {
			sp.setTeam(this);
		}
	}

	public void startUp(Location spawnLocation) {
		setColor(this.color);

		//		giveTeamItem(this.color);
		//		setSpawnLocation(spawnLocation);
	}

	public void giveTeamItem(Color color) {
		for (SiegePlayer sp : siegePlayerList.getPlayerList()) {
			for (ItemStack item : SiegeItemSet.teamItem(color)) {
				sp.getPlayer().getInventory().addItem(item);
			}
		}
	}

	public void gotoSpawn(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
		Location loc = new Location(spawnLocation.getWorld(),
				spawnLocation.getX() + 0.5,
				spawnLocation.getY() + 1.5,
				spawnLocation.getZ() + 0.5);
		for (SiegePlayer sp : siegePlayerList.getPlayerList()) {
			Player p = sp.getPlayer();
			p.teleport(loc);
			p.setBedSpawnLocation(loc);
		}
	}

	public boolean isMember(Player p) { //チーム解散
		for (SiegePlayer s : getSiegePlayerList().getPlayerList()) {
			Player q = s.getPlayer();
			if (q.equals(p)) {
				return true;
			}
		}
		return false;
	}

	public SiegePlayer getMember(Player p) {
		for (SiegePlayer s : getSiegePlayerList().getPlayerList()) {
			Player q = s.getPlayer();
			if (q.equals(p)) {
				return s;
			}
		}
		return null;
	}

	public void createNewBar(SiegeTeam enemy) {
		this.infoBar = new SiegeInformationBar(getInfoString(enemy),
		BarColor.PURPLE, BarStyle.SOLID);
		
		this.infoBar.setToTeam(this);
	}
	
	public void barReflesh(SiegeTeam enemy) {
		getInfoBar().setBarTitle(getInfoString(enemy));
	}
	
	public String getInfoString(SiegeTeam enemy) {
		String msg;
		msg = getColor() + getTeamName() + ": " + getCore() 
		+ ChatColor.RESET + " | " + enemy.getColor() + enemy.getTeamName() + ": " + enemy.getCore();
		return msg;
	}
	
	public SiegeInformationBar getInfoBar() {
		return this.infoBar;
	}
	
	@Override
	public String toString() {
		String ret = color + teamName;
		return ret;
	}

	//こっからせったーげーーたーー
	public SiegeTeamDetails getSiegeTeamDetails() {
		return siegeTeamDetails;
	}

	public void setSiegeTeamDetails(SiegeTeamDetails siegeTeamDetails) {
		this.siegeTeamDetails = siegeTeamDetails;
	}

	public void setTeamName (String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setSiegePlayerList(SiegePlayerList siegePlayerList) {
		this.siegePlayerList = siegePlayerList;
	}

	public SiegePlayerList getSiegePlayerList() {
		return siegePlayerList;
	}

	public ChatColor getColor() {
		return color;
	}

	public void setColor(ChatColor color) {
		this.color = color;
		for (SiegePlayer s : siegePlayerList.getPlayerList()) {
			Player p = s.getPlayer();
			p.setPlayerListName(color + p.getDisplayName());
		}
	}

	public Location getSpawnLocation() {
		return spawnLocation;
	}

	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}

	public int getCore() {
		return core;
	}

	public void setCore(int core) {
		this.core = core;
	}
}
