package Siege.SiegePlayer;

public class PlayerStats {

	private int killCount; //キル数
	private int defendDamageCount; //防衛として与えたダメージ
	private int defendingTime; //コア付近滞在時間
	private int attakingTime; //敵陣滞在時間
	private int deathCount; //デス数
	private int coreDamageDealt; //与えたコアダメージ
	private int brokeBlockCount; //破壊したブロック数
	private int getOreCount; //獲得した鉱石数
	private int enchantCount; //エンチャントした回数
	private int maxLostExp; //失った最大経験値
	private int naesaseScore; //奪い取った経験値量

	private int attackerScore; //総合攻撃スコア
	private int defenderScore; //総合防衛スコア

	public PlayerStats() {
		super();
		this.killCount = 0;
		this.defendDamageCount = 0;
		this.defendingTime = 0;
		this.attakingTime = 0;
		this.deathCount = 0;
		this.coreDamageDealt = 0;
		this.brokeBlockCount = 0;
		this.getOreCount = 0;
		this.enchantCount = 0;
		this.maxLostExp = 0;
		this.naesaseScore = 0;
		this.attackerScore = 0;
		this.defenderScore = 0;
	}

	public void addKillCount() {
		this.killCount++;
	}

	public int getKillCount() {
		return killCount;
	}

	public void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	public void addDefendDamageCount(int damage) {
		this.defendDamageCount += damage;
	}

	public int getDefendDamageCount() {
		return defendDamageCount;
	}

	public void setDefendDamageCount(int defendDamageCount) {
		this.defendDamageCount = defendDamageCount;
	}

	public int getDefendingTime() {
		return defendingTime;
	}

	public void setDefendingTime(int defendingTime) {
		this.defendingTime = defendingTime;
	}

	public int getAttakingTime() {
		return attakingTime;
	}

	public void setAttakingTime(int attakingTime) {
		this.attakingTime = attakingTime;
	}

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}

	public void addCoreDamageDealt() {
		this.coreDamageDealt++;
	}

	public int getCoreDamageDealt() {
		return coreDamageDealt;
	}

	public void setCoreDamageDealt(int coreDamageDealt) {
		this.coreDamageDealt = coreDamageDealt;
	}

	public int getBrokeBlockCount() {
		return brokeBlockCount;
	}

	public void setBrokeBlockCount(int brokeBlockCount) {
		this.brokeBlockCount = brokeBlockCount;
	}

	public int getGetOreCount() {
		return getOreCount;
	}

	public void setGetOreCount(int getOreCount) {
		this.getOreCount = getOreCount;
	}

	public int getEnchantCount() {
		return enchantCount;
	}

	public void setEnchantCount(int enchantCount) {
		this.enchantCount = enchantCount;
	}

	public int getMaxLostExp() {
		return maxLostExp;
	}
	public void setMaxLostExp(int maxLostExp) {
		this.maxLostExp = maxLostExp;
	}
	public int getNaesaseScore() {
		return naesaseScore;
	}
	public void setNaesaseScore(int naesaseScore) {
		this.naesaseScore = naesaseScore;
	}
	public int getAttackerScore() {
		return attackerScore;
	}
	public void setAttackerScore(int attackerScore) {
		this.attackerScore = attackerScore;
	}
	public int getDefenderScore() {
		return defenderScore;
	}
	public void setDefenderScore(int defenderScore) {
		this.defenderScore = defenderScore;
	}

	public void addDeathCount() {
		this.deathCount++;
	}

}
