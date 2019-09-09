package Siege.Rune;

import java.util.ArrayList;

public class RuneLore {
	private RuneLore() {}

	/* === 戦闘系 Tier1 === */
	public static final ArrayList<String> BATTLE_SOLOFIGHTER_LORE =
			asList("あなたは一人のときにこそ力を発揮する",
					"周りに味方がいないとき戦闘力が上昇する");
	public static final ArrayList<String> BATTLE_BERSERKER_LORE =
			asList("あなたは常に戦いを求める",
					"戦闘を行っている時間が長ければ長いほど",
					"あなたの力は強化される");
	public static final ArrayList<String> BATTLE_CHEERER_LORE =
			asList("あなたは味方を鼓舞する",
					"あなたの近くにいる味方は戦闘力があがる",
					"また、応援が必要なときにほど効果が上昇する");
	public static final ArrayList<String> BATTLE_RESIRIENCE_LORE =
			asList("あなたは逆境でこそ力を発揮する",
					"自身のピンチ、味方のピンチにおいて戦闘力が上がる");
	public static final ArrayList<String> BATTLE_TENACITY_LORE =
			asList("あなたはそう簡単に倒れない",
					"体力が一定以下になると衝撃吸収の効果を得る");
	public static final ArrayList<String> BATTLE_TOKKOYARO_LORE =
			asList("あなたは特攻するだけしか能がない",
					"相手のコア付近で耐久力と移動速度が上昇する");

	/* === 錬金系 Tier1 === */
	public static final ArrayList<String> MAGIC_CORESHIELD_LORE =
			asList("あなたは壊したコアを力に変える",
					"コアを破壊すればするほど防御力が上がる",
					"上昇した防御力はルーンを変更しても維持される");
	public static final ArrayList<String> MAGIC_SPELLTHIEF_LORE =
			asList("あなたは相手の魔法を盗み取る",
					"空のボトルを相手に向かって使用すると",
					"相手にかかっているポーション効果を盗む");
	public static final ArrayList<String> MAGIC_PERFUME_LORE =
			asList("あなたはとってもいいにおいがする",
					"自分にかかっているポーション効果を",
					"近くの味方にもおすそ分けする");
	public static final ArrayList<String> MAGIC_MAGICIAN_LORE =
			asList("あなたはすごい",
					"獲得する経験値の量が増加する");
	public static final ArrayList<String> MAGIC_MAGICARROW_LORE =
			asList("あなたが放つ矢には魔法の力が込められる。",
					"オフハンドにポーションを持って弓をうつことで",
					"放たれた矢にそのポーションエフェクトを付与する");

	/* === 敏捷系 Tier1 === */
	public static final ArrayList<String> SWIFT_CAMO_LORE =
			asList("あなたは気配に溶け込むすべを知っている",
					"近くに敵プレイヤーがいない間",
					"あなたは完全に透明化する");
	public static final ArrayList<String> SWIFT_DOUBLEJUMP_LORE =
			asList("あなたはどんな壁をも超えていく",
					"あなたは空中でもう一度ジャンプできる",
					"再使用には少しの時間がかかる");
	public static final ArrayList<String> SWIFT_STORM_LORE =
			asList("あなたは戦場の嵐となる",
					"移動速度, 相手へのノックバックを強化する",
					"このルーンを装備している間、常に効果は強くなっていく");
	public static final ArrayList<String> SWIFT_WINDY_LORE =
			asList("あなたの存在はチームを後押しする",
					"近くにいる味方に移動速度と跳躍力のバフを付与する");

	/* === 採掘系 Tier1 === */
	public static final ArrayList<String> COLLECT_THIEF_LORE =
			asList("あなたの手癖はめっちゃ悪い",
					"相手を攻撃すると鉱石を獲得することがある",
					"まれにめっちゃいいアイテムを手に入れることもある");
	public static final ArrayList<String> COLLECT_SCAVENGER_LORE =
			asList("あなたは死体の漁り方を心得ている",
					"敵を倒すたびに何らかのアイテムを獲得する");
	public static final ArrayList<String> COLLECT_MINER_LORE =
			asList("あなたは鉱脈の探し方を知っている",
					"採掘する鉱石の獲得量が増える");

	/* === 戦闘系 Tier2 === */
	public static final ArrayList<String> BATTLE_2_MELEEDAMAGE_LORE =
			asList("近接攻撃でのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_ARROWDAMAGE_LORE =
			asList("遠距離攻撃でのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_ANTIDEFENDER_LORE =
			asList("相手のコア付近にいる相手へのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_HEALTHBOOST_LORE =
			asList("最大体力が増加する");
	public static final ArrayList<String> BATTLE_2_REGENERATION_LORE =
			asList("体力が自動回復する効果を得る");
	public static final ArrayList<String> BATTLE_2_ANTIATTACKER_LORE =
			asList("自陣のコア付近にいる相手へのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_COREDIGSPEED_LORE =
			asList("相手のコア付近にいるとき、採掘速度が上昇する");

	/* === 錬金系 Tier2 === */
	public static final ArrayList<String> MAGIC_2_ENCHANT_LORE =
			asList("エンチャントの効果が上昇する");
	public static final ArrayList<String> MAGIC_2_POTION_AMP_LORE =
			asList("ポーションの効果が上昇する");
	public static final ArrayList<String> MAGIC_2_POTION_DUR_LORE =
			asList("ポーション効果の持続時間が伸びる");
	public static final ArrayList<String> MAGIC_2_EXPUP_LORE =
			asList("獲得経験値が増加する");
	public static final ArrayList<String> MAGIC_2_EXPPASSER_LORE =
			asList("経験値を獲得すると周りにもおすそわけする"); //DONE
	public static final ArrayList<String> MAGIC_2_KEYSTONE_LORE =
			asList("装備しているTier1ルーンが強化される");
	public static final ArrayList<String> MAGIC_2_KILLPOTION_LORE =
			asList("相手を倒すたびにランダムなポーションを得る");
	public static final ArrayList<String> MAGIC_2_EXPARMOR_LORE =
			asList("現在の経験値量に応じてダメージを軽減する");
	public static final ArrayList<String> MAGIC_2_SAFETY_LORE =
			asList("受けるダメージと与えるダメージが1/4になる",
					"この効果はコア無敵時間中のみ発動する");
	public static final ArrayList<String> MAGIC_2_INSURANCE_LORE =
			asList("採掘で得る経験値が1/4引かれる",
					"あなたが死んだときに引かれていた分が振り込まれる");

	/* === 俊敏系 Tier2 === */
	public static final ArrayList<String> SWIFT_2_SPEEDUP_LORE =
			asList("移動速度が上昇する");
	public static final ArrayList<String> SWIFT_2_JUMPBOOST_LORE =
			asList("跳躍力が上昇する");
	public static final ArrayList<String> SWIFT_2_NOFALL_LORE =
			asList("転落で受けるダメージを無効化する");
	public static final ArrayList<String> SWIFT_2_NOHUNGRY_LORE =
			asList("お腹が一切減らなくなる");
	public static final ArrayList<String> SWIFT_2_HOMEGUARD_LORE =
			asList("自陣付近にいるとき移動速度が上昇する");
	public static final ArrayList<String> SWIFT_2_RUNNER_LORE =
			asList("体力が一定以下になると移動速度が上昇する");
	public static final ArrayList<String> SWIFT_2_UNBURDEN_LORE =
			asList("全裸のとき足が早くなる");

	/* === 収集系 Tier2 === */
	public static final ArrayList<String> COLLECT_2_IRON_LORE =
			asList("鉄鉱石採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_GOLD_LORE =
			asList("金鉱石採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_DIA_LORE =
			asList("ダイヤモンド採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_RED_LORE =
			asList("赤石採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_LUCK_LORE =
			asList("少し幸運になる");
	public static final ArrayList<String> COLLECT_2_RECALL_LORE =
			asList("リコール時間が3秒に固定される");
	public static final ArrayList<String> COLLECT_2_HUMANFORGE_LORE =
			asList("鉄/金鉱石採掘時にインゴットで入手できる");
	public static final ArrayList<String> COLLECT_2_LUMBERJACK_LORE =
			asList("木を切ったときの採掘量が増える");

	private static ArrayList<String> asList(String...str) {
		ArrayList<String> ary = new ArrayList<>();
		for (String s : str) ary.add(s);
		return ary;
	}
}
