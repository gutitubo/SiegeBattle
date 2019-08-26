package Siege.SiegePlayer;

import java.util.ArrayList;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import Siege.Rune.RuneCategory;
import Siege.Rune.Runes;
import Siege.SiegeTeam.SiegeTeam;

public class SiegePlayer {

	private Player player; //本人
	private SiegeTeam team; //所属

	private RuneCategory mainPath;
	private RuneCategory subPath;

	private Runes[] currentRunes;

	private int additionalDamage; //追加ダメージ
	private int additionalDefend; //追加防御力
	private float additionalSpeed; //追加移動速度
	private ArrayList<PotionEffect> defaultEffect;

	private static float defaultSpeed = 0.2F;

	//こんすとらくたー
	public SiegePlayer() {
	}

	public SiegePlayer(Player player) {
		this.player = player;
		currentRunes = new Runes[5];
		this.additionalDamage = 0;
		this.additionalDefend = 0;
		this.additionalSpeed = 0;
		defaultEffect = new ArrayList<PotionEffect>();
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

	public void refleshRuneStatus() {
		//TODO 実装
		resetToDefault();
		for (PotionEffect e : getDeafultEffect()) {
			if (player.hasPotionEffect(e.getType())) {
				player.removePotionEffect(e.getType());
			} else {
				player.addPotionEffect(e);
			}
		}
	}

	public void resetToDefault() {
		Player p = getPlayer();
		p.setWalkSpeed(defaultSpeed);
		p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
		clearDefaultEffect();
	}

	public void showRunesString(Player p) {
		String[] str = getRunesString();
		p.sendMessage("");
		p.sendMessage("--- " + this.getPlayer().getDisplayName() + "'s Rune ---");
		p.sendMessage("Tier1: [" + str[0] + "]");
		p.sendMessage("Tier2: [" + str[1] + "], [" + str[2] + "], [" + str[3] + "], [" + str[4] + "]");
		p.sendMessage("");
	}

	public int getAdditionalDamage() {
		return additionalDamage;
	}

	public void setAdditionalDamage(int additionalDamage) {
		this.additionalDamage = additionalDamage;
	}

	public int getAdditionalDefend() {
		return additionalDefend;
	}

	public void setAdditionalDefend(int additionalDefend) {
		this.additionalDefend = additionalDefend;
	}

	public float getAdditionalSpeed() {
		return additionalSpeed;
	}

	public void setAdditionalSpeed(float additionalSpeed) {
		this.additionalSpeed = additionalSpeed;
	}

	public ArrayList<PotionEffect> getDeafultEffect() {
		return this.defaultEffect;
	}

	public void clearDefaultEffect() {
		this.defaultEffect.clear();
	}
}
