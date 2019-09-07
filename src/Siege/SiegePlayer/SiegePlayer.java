package Siege.SiegePlayer;

import static Lib.Parameters.*;

import java.util.ArrayList;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Siege.Rune.RuneCategory;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegeException.IkaretaPhaseException;
import Siege.SiegeTeam.SiegeTeam;

public class SiegePlayer {

	private Player player; //本人
	private SiegeTeam team; //所属

	private RuneCategory mainPath;
	private RuneCategory subPath;

	private Runes[] currentRunes;

	private int additionalDamage; //追加ダメージ
	private int additionalDefend; //追加防御力
	private int additionalDefendPerm; //追加永続防御力
	private double additionalHealth; //追加体力
	private float additionalSpeed; //追加移動速度
	private float speedMultipler; //追加移動速度(倍率)
	private ArrayList<PotionEffect> defaultEffect;

	private static final float defaultSpeed = 0.2F;
	private static final double defaultHealth = 20;

	//こんすとらくたー
	public SiegePlayer() {
	}

	public SiegePlayer(Player player) {
		this.player = player;
		currentRunes = new Runes[5];
		this.additionalDamage = 0;
		this.additionalDefend = 0;
		this.additionalDefendPerm = 0;
		this.additionalHealth = 0;
		this.additionalSpeed = 0;
		this.speedMultipler = 1.0F;
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
		this.resetToDefault();
	}

	public String[] getRunesString() {
		String[] str = new String[5];
		int i = 0;
		for (Runes r: this.currentRunes) {
			if (r == null) continue;
			str[i] = r.toString();
			i++;
		}
		return str;
	}

	public boolean hasRune(Runes rune) {
		for (Runes r : currentRunes) {
			if (r == rune) return true;
		}
		return false;
	}

	public void runeStatusReflect() {
		resetToDefault();
		for (Runes r : getCurrentRunes()) {
			if (r == null) continue;
			switch (r) {
			case BATTLE_HEALTHBOOST:
				setAdditionalHealth(RUNE_HEALTHBOOST_VALUE);
				break;
			case BATTLE_REGENERATION:
				getDeafultEffect().add(new PotionEffect(PotionEffectType.REGENERATION, RUNE_REGENERATION_TIME, RUNE_REGENERATION_AMP));
				break;
			case SWIFT_SPEEDUP:
				setAdditionalSpeed(RUNE_SPEEDUP_VALUE);
				break;
			case SWIFT_JUMPBOOST:
				getDeafultEffect().add(new PotionEffect(PotionEffectType.JUMP, RUNE_JUMPBOOST_TIME, RUNE_JUMPBOOST_AMP));
			default:
				break;
			}
		}
		refleshRuneStatus();
	}

	public void refleshRuneStatus() {
		//TODO 実装
		statusReflect();
		for (PotionEffect e : getDeafultEffect()) {
			if (player.hasPotionEffect(e.getType())) {
				player.removePotionEffect(e.getType());
			}
			player.addPotionEffect(e);
		}
	}

	public void statusReflect() {
		Player p = getPlayer();
		p.setWalkSpeed((defaultSpeed + getAdditionalSpeed()) * getSpeedMultipler());
		p.setHealthScale(defaultHealth + getAdditionalHealth());
		p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(getAdditionalDefend() + getAdditionalDefendPerm());
		setAdditionalSpeed(0F);
		setAdditionalHealth(0);
	}

	public void resetToDefault() {
		Player p = getPlayer();
		p.setWalkSpeed(defaultSpeed);
		p.setHealthScale(defaultHealth);
		p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(getAdditionalDefendPerm());
		setAdditionalDamage(0);
		setAdditionalDefend(0);
		setAdditionalHealth(0);
		setAdditionalSpeed(0);
		setSpeedMultipler(1F);
		clearDefaultEffect();
		for (PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
	}

	public void showRunesString(Player p) {
		String[] str = getRunesString();
		p.sendMessage("");
		p.sendMessage("--- " + this.getPlayer().getDisplayName() + "'s Rune ---");
		p.sendMessage("Tier1: [" + str[0] + "]");
		p.sendMessage("Tier2: [" + str[1] + "], [" + str[2] + "], [" + str[3] + "], [" + str[4] + "]");
		p.sendMessage("");
	}

	public double getDistanceWhileOwnCore() throws IkaretaPhaseException {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) {
			throw new IkaretaPhaseException("ゲーム中じゃないと使えないよ");
		}
		if (game.getBlueTeam().isMember(getPlayer())) {
			return game.getSiegeStage().getBlueCore().distance(getPlayer().getLocation());
		} else if (game.getRedTeam().isMember(getPlayer())) {
			return game.getSiegeStage().getRedCore().distance(getPlayer().getLocation());
		} else {
			throw new IkaretaPhaseException("チームに所属してないみたいだよ");
		}
	}

	public double getDistanceWhileEnemyCore() throws IkaretaPhaseException {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) {
			throw new IkaretaPhaseException("ゲーム中じゃないと使えないよ");
		}
		if (game.getBlueTeam().isMember(getPlayer())) {
			return game.getSiegeStage().getRedCore().distance(getPlayer().getLocation());
		} else if (game.getRedTeam().isMember(getPlayer())) {
			return game.getSiegeStage().getBlueCore().distance(getPlayer().getLocation());
		} else {
			throw new IkaretaPhaseException("チームに所属してないみたいだよ");
		}
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
		this.defaultEffect.clear();;
	}

	public double getAdditionalHealth() {
		return additionalHealth;
	}

	public void setAdditionalHealth(double additionalHealth) {
		this.additionalHealth = additionalHealth;
	}

	public float getSpeedMultipler() {
		return speedMultipler;
	}

	public void setSpeedMultipler(float speedMultipler) {
		this.speedMultipler = speedMultipler;
	}

	public int getAdditionalDefendPerm() {
		return additionalDefendPerm;
	}

	public void setAdditionalDefendPerm(int additionalDefendPerm) {
		this.additionalDefendPerm = additionalDefendPerm;
	}
}
