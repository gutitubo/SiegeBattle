package Siege.SiegeShop;

import org.bukkit.entity.Player;

public enum GambleItem {
	COAL,
	SAND,
	CACTUS,
	DIAMOND,
	EXP30,
	EXP50,
	ULTRA_PHANTOM,
	RECALL_STICK,
	;
	
	public void pickRandom(Player p) {
		switch (getRandom()) {
		case CACTUS:
			break;
		case COAL:
			break;
		case DIAMOND:
			break;
		case EXP30:
			break;
		case EXP50:
			break;
		case RECALL_STICK:
			break;
		case SAND:
			break;
		case ULTRA_PHANTOM:
			break;
		default:
			break;
		}
	}
	
	private GambleItem getRandom() {
		return COAL;
	}
}
