package Lib;

import net.md_5.bungee.api.ChatColor;

public class ConstStrings {
	private ConstStrings() {}

	public static final String SHOP_SIGN_LINE = ChatColor.DARK_BLUE + "==============";
	public static final String SHOP_SIGN_SIEGE = ChatColor.RED + "Siege Battle";

	public static final String SHOP_NORMAL_NAME = "Darkness Shop";
	public static final String SHOP_EXP_NAME = "Level Shop";

	public static final String SHOP_NORMAL_STRING = ChatColor.BLACK.toString() + ChatColor.BOLD.toString() + SHOP_NORMAL_NAME;
	public static final String SHOP_EXP_STRING = ChatColor.GREEN + SHOP_EXP_NAME;

	public static final String SHOP_NORMAL_TITLE = ChatColor.GRAY + "Darkness Shop";

	public static final String SHOP_MESSAGE_YOUCANTBUY = ChatColor.RED + "コストが不足しているため購入できません。";
	public static final String SHOP_MESSAGE_BOUGHT_ITEM = ChatColor.GOLD + "を購入した。";
}
