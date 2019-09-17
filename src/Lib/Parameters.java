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
	public static final int REDSHOP_SHEARS_COST = 9;
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
	public static final double RUNE_SOLOFIGHTER_RANGE = 16; //孤軍奮闘発動ライン
	public static final int RUNE_SOLOFIGHTER_REG = 0; //孤軍奮闘強化時 再生力レベル
	public static final int RUNE_SOLOFIGHTER_DEF = 20; //上昇防御力

	public static final double RUNE_TENACITY_CASTLINE = 8; //執念発動ライン
	public static final int RUNE_TENACITY_VALUE = 1; //執念効果
	public static final int RUNE_TENACITY_VALUE_ENHANCED = 2; //執念効果 強化
	public static final int RUNE_TENACITY_DUR = 20 * 60; //執念発動CD
	public static final int RUNE_TENACITY_DUR_ENHANCED = 20 * 60; //執念発動CD 強化

	public static final int RUNE_CHEERER_RANGE = 24; //応援団発動距離
	public static final int RUNE_CHEERER_RANGE_ENHANCED = 32; //応援団発動距離 強化
	public static final int RUNE_CHEERER_OWN_DIS = 256; //応援団自陣制限距離
	public static final int RUNE_CHEERER_OWN_LIM = 2; //自陣最大強化
	public static final int RUNE_CHEERER_ENE_DIS = 128; //応援団敵陣強化距離
	public static final int RUNE_CHEERER_HLT = 5; //体力ギリ強化ライン
	public static final int RUNE_CHEERER_LV1_AMP = 0; //応援団Lv1
	public static final int RUNE_CHEERER_LV2_AMP = 0; //応援団Lv2
	public static final int RUNE_CHEERER_LV3_AMP = 1; //応援団Lv3
	public static final int RUNE_CHEERER_LV4_AMP = 1; //応援団Lv4
	public static final int RUNE_CHEERER_LV5_AMP = 0; //応援団Lv5
	public static final int RUNE_SNIPER_CD = 20 * 5; //狙撃手CD
	public static final double RUNE_SNIPER_ENHANCED = 1.2; //強化狙撃手 弾速強化

	public static final double RUNE_CAMO_RANGE = 24; //カモフラレンジ
	public static final double RUNE_CAMO_RANGE_ENHANCED = 16; //カモフラレンジ

	public static final double RUNE_WINDY_RANGE = 16; //追い風の発動距離
	public static final double RUNE_WINDY_RANGE_ENHANCED = 24; //追い風の発動距離 強化
	public static final int RUNE_WINDY_DUR = 20 * 4; //追い風持続時間
	public static final int RUNE_WINDY_DUR_ENHANCED = 20 * 4; //追い風持続時間 強化
	public static final int RUNE_WINDY_AMP = 0; //追い風効果
	public static final double RUNE_DOUBLEJUMP_POW = 0.8; //ダブルジャンプ跳躍力
	public static final double RUNE_DOUBLEJUMP_POW_ENHANCED = 1.2; //強化ダブルジャンプ跳躍力
	public static final double RUNE_DOUBLEJUMP_MUL = 2.5; //ダブルジャンプ速度上昇
	public static final int RUNE_DOUBLEJUMP_COST = 12;

	public static final double RUNE_EXPUP_MULTIPLY = 1.3; //魔法使い
	public static final double RUNE_EXPUP_MULTIPLY_ENHANCED = 1.5; //魔法使い強化

	public static final int RUNE_THIEF_CHANCE = 50; //盗賊の盗む確率 MAX100
	public static final int RUNE_THIEF_CHANCE_ENHANCED = 100; //盗賊の盗む確率強化 MAX100
	public static final int RUNE_MINER_VALUE = 3; //炭鉱夫
	public static final int RUNE_MINER_VALUE_ENHANCED = 1; //炭鉱夫強化
	public static final int RUNE_ARROWSIGHT_CD = 20 * 15; //明鏡止水クールタイム
	public static final double RUNE_ARROWSIGHT_RANGE = 12; //明鏡止水効果範囲
	public static final double RUNE_ARROWSIGHT_RANGE_ENHANCED = 18; //明鏡止水強化時 効果範囲

	/* === Tier 2 === */
	public static final int RUNE_MELEEDAMAGE_VALUE = 2; //近接強化のダメージ
	public static final int RUNE_ANTIDEFENDER_VALUE = 2; //対防衛特攻のダメージ
	public static final int RUNE_ANTIATTACKER_VALUE = 2; //対攻撃特攻のダメージ
	public static final int RUNE_ARROWDAMAGE_VALUE = 4; //遠距離攻撃ダメージ増加
	public static final int RUNE_HEALTHBOOST_VALUE = 6; //体力増加の増加量
	public static final int RUNE_REGENERATION_TIME = 20 * 60 * 150; //自動回復の効果時間
	public static final int RUNE_REGENERATION_AMP = 0; //自動回復の効果
	public static final double RUNE_COREDIG_DISTANCE = 16; //コア採掘発動距離
	public static final int RUNE_COREDIG_AMP = 0; //コア採掘 強さ

	public static final double RUNE_POTDUR_MULTIPLY = 1.2; //ポーションの延長の上昇率
	public static final double RUNE_EXPUP2_MULTIPLY = 1.2; //経験値アップの上昇率
	public static final double RUNE_EXPPASSER_RANGE = 32; //経験値おすそ分け距離
	public static final double RUNE_EXPPASSER_RATIO = 0.25; //経験値おすそ分け量

	public static final float RUNE_SPEEDUP_VALUE = 0.08F; //スピードアップの増加量
	public static final int RUNE_JUMPBOOST_TIME = 20* 60* 150; //ジャンプブーストの効果時間
	public static final int RUNE_JUMPBOOST_AMP = 2; //ジャンプブーストの強さ
	public static final int RUNE_HOMEGUARD_AMP = 1; //ホームガードの強さ
	public static final int RUNE_HOMEGUARD_DISTANCE = 48; //ホームガードの発動距離
	public static final int RUNE_RUNNER_AMP = 1; //逃げ足の強さ
	public static final int RUNE_RUNNER_HEALTH = 3; //逃げ足発動体力
	public static final float RUNE_UNBURDEN_AMOUNT = 0.08F; //かるわざ移動速度増加量

	public static final int RUNE_IRON_AMOUNT = 2; //鉄鉱石増加
	public static final int RUNE_GOLD_AMOUNT = 2; //金鉱石増加
	public static final int RUNE_DIA_AMOUNT = 2; //ダイアモンド増加
	public static final int RUNE_RED_AMOUNT = 3; //赤石増加
	public static final int RUNE_RECALL_AMOUNT = 3; //リコール短縮
	public static final int RUNE_LUMBERJACK_AMOUNT = 3; //木こりさんの増加量
	public static final double RUNE_SAFETY_MULTI = 0.25; //木こりさんの増加量

	/* == RandomPotionのMaxStatus == */
	public static final int NORMALPOT_MAXLEVEL = 1; //普通のポーションの最大レベル
	public static final int NORMALPOT_MAXDUR = 20 * 60 * 3; //普通のポーションの最大効果時間

	public static final int STRE_MAXLEVEL = 1; //ストポ最大レベル
	public static final int STRE_MAXDUR = 20 * 30 * 1; //ストポ最大効果時間

	public static final int INSTANT_MAXDUR = 1; //インスタント系ポット効果時間
}
