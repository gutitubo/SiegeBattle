package Lib;

import net.md_5.bungee.api.ChatColor;

public class ConstStrings {
	private ConstStrings() {}

	/* ========== ログインメッセージ ========== */
	public static final String[] JOIN_MESSAGE = new String[] {
			"",
			"",
			ChatColor.RED + ChatColor.BOLD.toString() + "シージバトルサーバーへようこそ！",
			"",
			ChatColor.DARK_RED + "現在のバージョン: " + Siege.SiegeBattleMain.siegeBattleMain.getDescription().getVersion(),
			"",
			""
	};

	/* ========== SHOP関連 ========== */
	public static final String SHOP_SIGN_LINE = ChatColor.DARK_BLUE + "==============";
	public static final String SHOP_SIGN_SIEGE = ChatColor.RED + "Siege Battle";

	public static final String SHOP_NORMAL_PLANE = "Darkness Shop";
	public static final String SHOP_EXP_PLANE = "Level Shop";

	public static final String SHOP_NORMAL_STRING = ChatColor.BLACK.toString() + ChatColor.BOLD.toString() + SHOP_NORMAL_PLANE;
	public static final String SHOP_EXP_STRING = ChatColor.GREEN + SHOP_EXP_PLANE;

	public static final String SHOP_NORMAL_TITLE = ChatColor.GRAY + "Darkness Shop";

	public static final String SHOP_MESSAGE_YOUCANTBUY = ChatColor.RED + "コストが不足しているため購入できません。";
	public static final String SHOP_MESSAGE_BOUGHT_ITEM = ChatColor.GRAY + "を購入した。";

	/* ========== RUNE関連 ========== */
	public static final String RUNE_SIGN_LINE = ChatColor.DARK_BLUE + "==============";
	public static final String RUNE_SIGN_SIEGE = ChatColor.RED + "Siege Battle";
	public static final String RUNE_SIGN_PLANE = "Rune Selector";
	public static final String RUNE_SIGN_STRING = ChatColor.BLACK.toString() + ChatColor.BOLD.toString() + RUNE_SIGN_PLANE;

	public static final String RUNE_INV_TITLE_PATH = "メインパスを選択してください";
	public static final String RUNE_INV_TITLE_PATH_SUB = "サブパスを選択してください";
	public static final String RUNE_INV_TITLE_MAIN = "ルーンを選択してください";
}
