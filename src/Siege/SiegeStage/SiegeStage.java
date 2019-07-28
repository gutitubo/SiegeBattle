package Siege.SiegeStage;

import org.bukkit.Location;

public class SiegeStage {

	private Location redCore;
	private Location blueCore;
	private Location redSpawnRight;
	private Location redSpawnCenter;
	private Location redSpawnLeft;
	private Location blueSpawnRight;
	private Location blueSpawnCenter;
	private Location blueSpawnLeft;
	private Location middleOfMap;
	
	private String stageName;
	
	public SiegeStage() {
		
	}
	
	public SiegeStage(String name, Location redCore, Location redSpawnRight, Location redSpawnLeft, Location redSpawnCenter, Location blueCore, Location blueSpawnRight, Location blueSpawnLeft, Location blueSpawnCenter, Location middleOfMap) {
		setStageName(name);
		setRedCore(redCore);
		setRedSpawnRight(redSpawnRight);
		setRedSpawnLeft(redSpawnLeft);
		setRedSpawnCenter(redSpawnCenter);
		setBlueCore(blueCore);
		setBlueSpawnRight(blueSpawnRight);
		setBlueSpawnLeft(blueSpawnLeft);
		setBlueSpawnCenter(blueSpawnCenter);
		setMiddleOfMap(middleOfMap);
	}

	public Location getRedCore() {
		return redCore;
	}
 
	public void setRedCore(Location redCore) {
		this.redCore = redCore;
	}

	public Location getBlueCore() {
		return blueCore;
	}

	public void setBlueCore(Location blueCore) {
		this.blueCore = blueCore;
	}

	public Location getRedSpawnRight() {
		return redSpawnRight;
	}

	public void setRedSpawnRight(Location redSpawnRight) {
		this.redSpawnRight = redSpawnRight;
	}

	public Location getRedSpawnLeft() {
		return redSpawnLeft;
	}

	public void setRedSpawnLeft(Location redSpawnLeft) {
		this.redSpawnLeft = redSpawnLeft;
	}

	public Location getBlueSpawnRight() {
		return blueSpawnRight;
	}

	public void setBlueSpawnRight(Location blueSpawnRight) {
		this.blueSpawnRight = blueSpawnRight;
	}

	public Location getBlueSpawnLeft() {
		return blueSpawnLeft;
	}

	public void setBlueSpawnLeft(Location blueSpawnLeft) {
		this.blueSpawnLeft = blueSpawnLeft;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public Location getRedSpawnCenter() {
		return redSpawnCenter;
	}

	public void setRedSpawnCenter(Location redSpawnCenter) {
		this.redSpawnCenter = redSpawnCenter;
	}

	public Location getBlueSpawnCenter() {
		return blueSpawnCenter;
	}

	public void setBlueSpawnCenter(Location blueSpawnCenter) {
		this.blueSpawnCenter = blueSpawnCenter;
	}

	public Location getMiddleOfMap() {
		return middleOfMap;
	}

	public void setMiddleOfMap(Location middleOfMap) {
		this.middleOfMap = middleOfMap;
	}
}
