package Lib;

public class Parameters {
	private Parameters() {

	}
	/*
	 * 鉱石の経験値
	 */
	public static final int COAL_EXP = 3;
	public static final int IRON_EXP = 10;
	public static final int GOLD_EXP = 40;
	public static final int DIAMOND_EXP = 100;
	public static final int REDSTONE_EXP = 25;
	public static final int LAPIS_EXP = 10;

	/*
	 * 鉱石の獲得量
	 */
	public static final int COAL_AMT = 1;
	public static final int IRON_AMT = 1;
	public static final int GOLD_AMT = 1;
	public static final int DIA_AMT = 1;
	public static final int RED_AMT = 3;
	public static final int LAPIS_AMT = 1;

	/*
	 * SHOPコスト
	 */
	public static final int SHOP_GAMBLE_COST = 3;
	public static final int BONUS_CHEST_CHANCE = 100;
//	public static final int
	public static final int REDSHOP_GOLDINGOT_AMOUNT = 4;
	public static final int REDSHOP_GOLDINGOT_COST = 4;
	public static final int REDSHOP_COWWEB_COST = 6;
	public static final int REDSHOP_COWWEB_AMOUNT = 2;
	public static final int REDSHOP_SHEARS_COST = 7;
	public static final int REDSHOP_GCARROT_AMOUNT = 4;
	public static final int REDSHOP_GCARROT_COST = 4;

	public static final int EMESHOP_BOOK_SHARPNESS_COST = 3;
	public static final int EMESHOP_BOOK_EFFICIENCY_COST = 3;

	public static final int LAPSHOP_BOW_COST = 3;
	public static final int LAPSHOP_ARROW_COST = 1;
	public static final int LAPSHOP_ARROW_AMOUNT = 4;

	/*
	 *  RUNE定数
	 */

	/* === Tier 1 === */
	public static final double RUNE_TENACITY_CASTLINE = 8; //執念発動ライン
	public static final int RUNE_TENACITY_VALUE = 2; //執念効果
	public static final int RUNE_TENACITY_VALUE_ENHANCED = 4; //執念効果 強化
	public static final int RUNE_TENACITY_DUR = 20 * 30; //執念発動CD
	public static final int RUNE_TENACITY_DUR_ENHANCED = 20 * 20; //執念発動CD 強化

	public static final double RUNE_WINDY_RANGE = 16; //追い風の発動距離
	public static final double RUNE_WINDY_RANGE_ENHANCED = 24; //追い風の発動距離 強化
	public static final int RUNE_WINDY_DUR = 20 * 4; //追い風持続時間
	public static final int RUNE_WINDY_DUR_ENHANCED = 20 * 4; //追い風持続時間 強化
	public static final int RUNE_WINDY_AMP = 0; //追い風効果

	public static final double RUNE_EXPUP_MULTIPLY = 1.3; //魔法使い
	public static final double RUNE_EXPUP_MULTIPLY_ENHANCED = 1.5; //魔法使い強化

	public static final int RUNE_MINER_VALUE = 2; //炭鉱夫
	public static final int RUNE_MINER_VALUE_ENHANCED = 1; //炭鉱夫強化

	/* === Tier 2 === */
	public static final int RUNE_MELEEDAMAGE_VALUE = 3; //近接強化のダメージ
	public static final int RUNE_ANTIDEFENDER_VALUE = 3; //対防衛特攻のダメージ
	public static final int RUNE_ANTIATTACKER_VALUE = 3; //対攻撃特攻のダメージ
	public static final int RUNE_HEALTHBOOST_VALUE = 6; //体力増加の増加量
	public static final int RUNE_REGENERATION_TIME = 20 * 60 * 150; //自動回復の効果時間
	public static final int RUNE_REGENERATION_AMP = 0; //自動回復の効果

	public static final double RUNE_EXPUP2_MULTIPLY = 1.2;

	public static final float RUNE_SPEEDUP_VALUE = 0.05F; //スピードアップの増加量
	public static final int RUNE_JUMPBOOST_TIME = 20* 60* 150; //ジャンプブーストの効果時間
	public static final int RUNE_JUMPBOOST_AMP = 2; //ジャンプブーストの強さ

	public static final int RUNE_IRON_AMOUNT = 1; //鉄鉱石増加
	public static final int RUNE_GOLD_AMOUNT = 1; //金鉱石増加
	public static final int RUNE_DIA_AMOUNT = 1; //ダイアモンド増加
	public static final int RUNE_RED_AMOUNT = 2; //赤石増加
	public static final int RUNE_RECALL_AMOUNT = 3; //リコール短縮
}
