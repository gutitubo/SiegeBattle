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
					"相手を攻撃するたびにランダムで鉱石を獲得する",
					"まれにめっちゃいいアイテムを手に入れることもある");
	public static final ArrayList<String> COLLECT_SCAVENGER_LORE =
			asList("あなたは死体の漁り方を心得ている",
					"敵を倒すたびに何らかのアイテムを獲得する");
	public static final ArrayList<String> COLLECT_MINER_LORE =
			asList("あなたは鉱脈の探し方を知っている",
					"採掘する鉱石の獲得量が増える");

	private static ArrayList<String> asList(String...str) {
		ArrayList<String> ary = new ArrayList<>();
		for (String s : str) ary.add(s);
		return ary;
	}
}
