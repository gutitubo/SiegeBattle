package Siege.Rune;

import java.util.ArrayList;

import Lib.Parameters;
import net.md_5.bungee.api.ChatColor;

public class RuneLore {
	private RuneLore() {}

	private static String C = ChatColor.RESET.toString();

	/* === 戦闘系 Tier1 === */
	public static final ArrayList<String> BATTLE_SOLOFIGHTER_LORE =
			asList(C+"あなたは一人のときにこそ力を発揮する",
					C+"周りに味方がいないとき戦闘力が上昇する");
	public static final ArrayList<String> BATTLE_BERSERKER_LORE =
			asList(C+"あなたは常に戦いを求める",
					C+"戦闘を行っている時間が長ければ長いほど",
					C+"あなたの力は強化される");
	public static final ArrayList<String> BATTLE_CHEERER_LORE =
			asList(C+"あなたの存在は味方を鼓舞する",
					C+"あなたの近くにいる味方は戦闘力があがる",
					C+"また、応援が必要なときにほど効果が上昇する");
	public static final ArrayList<String> BATTLE_RESIRIENCE_LORE =
			asList(C+"あなたは逆境でこそ力を発揮する",
					C+"自身のピンチ、味方のピンチにおいて戦闘力が上がる");
	public static final ArrayList<String> BATTLE_TENACITY_LORE =
			asList(C+"あなたはそう簡単に倒れない",
					C+"体力が一定以下になると衝撃吸収の効果を得る");
	public static final ArrayList<String> BATTLE_TOKKOYARO_LORE =
			asList(C+"あなたは特攻するだけしか能がない",
					C+"相手のコア付近で耐久力と移動速度が上昇する");
	public static final ArrayList<String> BATTLE_SNIPER_LORE =
			asList(C+"あなたの遠くからちまちま敵を攻撃する陰キャだ",
					C+"あなたの放つ矢は長く飛ぶほどダメージが増していく",
					C+"また、最大まで引き絞って放つ矢は重力の影響を受けない",
					C+"ただし、矢を撃つたびに弓に5秒のクールタイムが付与される");

	/* === 錬金系 Tier1 === */
	public static final ArrayList<String> MAGIC_CORESHIELD_LORE =
			asList(C+"あなたは壊したコアを力に変える",
					C+"コアを破壊すればするほど防御力が上がる",
					C+"上昇した防御力はルーンを変更しても維持される");
	public static final ArrayList<String> MAGIC_SPELLTHIEF_LORE =
			asList(C+"あなたは相手の魔法を盗み取る",
					C+"空のボトルを相手に向かって使用すると",
					C+"相手にかかっているポーション効果を盗む");
	public static final ArrayList<String> MAGIC_PERFUME_LORE =
			asList(C+"あなたはとってもいいにおいがする",
					C+"自分にかかっているポーション効果を",
					C+"近くの味方にもおすそ分けする");
	public static final ArrayList<String> MAGIC_MAGICIAN_LORE =
			asList(C+"あなたはすごい",
					C+"獲得する経験値の量が増加する");
	public static final ArrayList<String> MAGIC_MAGICARROW_LORE =
			asList(C+"あなたが放つ矢には魔法の力が込められる。",
					C+"オフハンドにポーションを持って弓をうつことで",
					C+"放たれた矢にそのポーションエフェクトを付与する");
	public static final ArrayList<String> MAGIC_LINKAGE_LORE =
			asList(C+"あなたは常に仲間とつながっている",
					C+"素手で自分の真下のブロックをクリックし10秒待つと",
					C+"テレポーターが設置される。",
					C+"テレポーターは誰でも使用でき、味方のテレポーターに飛べる。",
					C+"使用すると飛んだ距離に応じてデバフが付与される。");

	/* === 敏捷系 Tier1 === */
	public static final ArrayList<String> SWIFT_CAMO_LORE =
			asList(C+"あなたは気配に溶け込むすべを知っている",
					C+"近くに敵プレイヤーがいない間",
					C+"あなたは完全に透明化する");
	public static final ArrayList<String> SWIFT_DOUBLEJUMP_LORE =
			asList(C+"あなたはどんな壁をも超えていく",
					C+"あなたは空中でもう一度ジャンプできる",
					C+"再使用には少しの時間がかかる");
	public static final ArrayList<String> SWIFT_STORM_LORE =
			asList(C+"あなたは戦場の嵐となる",
					C+"移動速度, 相手へのノックバックを強化する",
					C+"このルーンを装備している間、常に効果は強くなっていく");
	public static final ArrayList<String> SWIFT_WINDY_LORE =
			asList(C+"あなたの存在はチームを後押しする",
					C+"近くにいる味方に移動速度と跳躍力のバフを付与する");

	/* === 採掘系 Tier1 === */
	public static final ArrayList<String> COLLECT_THIEF_LORE =
			asList(C+"あなたの手癖はめっちゃ悪い",
					C+"相手を攻撃すると鉱石を獲得することがある",
					C+"まれにめっちゃいいアイテムを手に入れることもある");
	public static final ArrayList<String> COLLECT_SCAVENGER_LORE =
			asList(C+"あなたは死体の漁り方を心得ている",
					C+"敵を倒すたびに何らかのアイテムを獲得する");
	public static final ArrayList<String> COLLECT_MINER_LORE =
			asList(C+"あなたは鉱脈の探し方を知っている",
					C+"採掘する鉱石の獲得量が増える");
	public static final ArrayList<String> COLLECT_ARROWSIGHT_LORE =
			asList(C+"あなたは第三の目で敵を見つけ出す",
					C+"自分が放った矢の着弾地点に周囲にいる敵を発光させる",
					C+"このルーンを装備している間、弓の射撃にクールタイムがつく");

	/* === 戦闘系 Tier2 === */
	public static final ArrayList<String> BATTLE_2_MELEEDAMAGE_LORE =
			asList(C+"近接攻撃でのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_ARROWDAMAGE_LORE =
			asList(C+"遠距離攻撃でのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_ANTIDEFENDER_LORE =
			asList(C+"相手のコア付近にいる相手へのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_HEALTHBOOST_LORE =
			asList(C+"最大体力が増加する");
	public static final ArrayList<String> BATTLE_2_REGENERATION_LORE =
			asList(C+"体力が自動回復する効果を得る");
	public static final ArrayList<String> BATTLE_2_ANTIATTACKER_LORE =
			asList(C+"自陣のコア付近にいる相手へのダメージが増加する");
	public static final ArrayList<String> BATTLE_2_COREDIGSPEED_LORE =
			asList(C+"相手のコア付近にいるとき、採掘速度が上昇する");
	public static final ArrayList<String> BATTLE_2_NEBANEBA_LORE =
			asList(C+"相手を攻撃したとき、相手に鈍足と採掘低下を与える",
					C+"CD:" + Parameters.RUNE_NEBANEBA_CD + "秒");
	public static final ArrayList<String> BATTLE_2_PICAPICA_LORE =
			asList(C+"相手を攻撃したとき、その相手を発光させる");

	/* === 錬金系 Tier2 === */
	public static final ArrayList<String> MAGIC_2_ENCHANT_LORE =
			asList(C+"エンチャントの効果が上昇する");
	public static final ArrayList<String> MAGIC_2_POTION_AMP_LORE =
			asList(C+"ポーションの効果が上昇する");
	public static final ArrayList<String> MAGIC_2_POTION_DUR_LORE =
			asList(C+"ポーション効果の持続時間が伸びる");
	public static final ArrayList<String> MAGIC_2_EXPUP_LORE =
			asList(C+"獲得経験値が増加する");
	public static final ArrayList<String> MAGIC_2_EXPPASSER_LORE =
			asList(C+"経験値を獲得すると周りにもおすそわけする"); //DONE
	public static final ArrayList<String> MAGIC_2_KEYSTONE_LORE =
			asList(C+"装備しているTier1ルーンが強化される");
	public static final ArrayList<String> MAGIC_2_KILLPOTION_LORE =
			asList(C+"相手を倒すたびにランダムなポーションを得る");
	public static final ArrayList<String> MAGIC_2_EXPARMOR_LORE =
			asList(C+"現在の経験値量に応じてダメージを軽減する");
	public static final ArrayList<String> MAGIC_2_SAFETY_LORE =
			asList(C+"受けるダメージと与えるダメージが1/4になる",
					C+"この効果はコア無敵時間中のみ発動する");
	public static final ArrayList<String> MAGIC_2_INSURANCE_LORE =
			asList(C+"採掘で得る経験値が1/4引かれる",
					C+"あなたが死んだときに引かれていた分が振り込まれる");

	/* === 俊敏系 Tier2 === */
	public static final ArrayList<String> SWIFT_2_SPEEDUP_LORE =
			asList(C+"移動速度が上昇する");
	public static final ArrayList<String> SWIFT_2_JUMPBOOST_LORE =
			asList(C+"跳躍力が上昇する");
	public static final ArrayList<String> SWIFT_2_NOFALL_LORE =
			asList(C+"転落で受けるダメージを無効化する");
	public static final ArrayList<String> SWIFT_2_NOHUNGRY_LORE =
			asList(C+"お腹が一切減らなくなる");
	public static final ArrayList<String> SWIFT_2_HOMEGUARD_LORE =
			asList(C+"自陣付近にいるとき移動速度が上昇する");
	public static final ArrayList<String> SWIFT_2_RUNNER_LORE =
			asList(C+"体力が一定以下になると移動速度が上昇する");
	public static final ArrayList<String> SWIFT_2_UNBURDEN_LORE =
			asList(C+"全裸のとき足が早くなる");

	/* === 収集系 Tier2 === */
	public static final ArrayList<String> COLLECT_2_IRON_LORE =
			asList(C+"鉄鉱石採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_GOLD_LORE =
			asList(C+"金鉱石採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_DIA_LORE =
			asList(C+"ダイヤモンド採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_RED_LORE =
			asList(C+"赤石採掘時の獲得量が増加する");
	public static final ArrayList<String> COLLECT_2_LUCK_LORE =
			asList(C+"少し幸運になる");
	public static final ArrayList<String> COLLECT_2_RECALL_LORE =
			asList(C+"リコール時間が3秒に固定される");
	public static final ArrayList<String> COLLECT_2_HUMANFORGE_LORE =
			asList(C+"鉄/金鉱石採掘時にインゴットで入手できる");
	public static final ArrayList<String> COLLECT_2_LUMBERJACK_LORE =
			asList(C+"木を切ったときの採掘量が増える");

	private static ArrayList<String> asList(String...str) {
		ArrayList<String> ary = new ArrayList<>();
		for (String s : str) ary.add(s);
		return ary;
	}
}
