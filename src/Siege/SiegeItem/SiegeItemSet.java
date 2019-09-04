package Siege.SiegeItem;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;

public class SiegeItemSet {

	public static Set<ItemStack> teamItem(Color color) {
		//アイテム
		Set<ItemStack> sis = new HashSet<ItemStack>();
		sis.add(new ItemStack(Material.STONE_SWORD));
		sis.add(new ItemStack(Material.STONE_AXE));
		sis.add(new ItemStack(Material.STONE_PICKAXE));
		sis.add(new ItemStack(Material.STONE_SHOVEL));
		sis.add(new ItemStack(Material.BREAD, 16));
		sis.add(new ItemStack(Material.CRAFTING_TABLE));
		sis.add(new ItemStack(Material.STICK, 4));
		return sis;
	}

	public static void giveTeamArmor(SiegeTeam team) {
		Color c = null;
		if (team.getColor().equals(ChatColor.RED)) c = Color.RED;
		if (team.getColor().equals(ChatColor.BLUE)) c = Color.BLUE;

		ItemStack lh = new ItemStack(Material.LEATHER_HELMET);
		ItemStack lc = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack ll = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack lb = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta lm = (LeatherArmorMeta) lh.getItemMeta();
		lm.setColor(c);
		lm.setUnbreakable(true);
		lh.setItemMeta(lm);
		lc.setItemMeta(lm);
		ll.setItemMeta(lm);
		lb.setItemMeta(lm);

		for (SiegePlayer sp : team.getSiegePlayerList().getPlayerList()) {
			sp.getPlayer().getInventory().setHelmet(lh);
			sp.getPlayer().getInventory().setChestplate(lc);
			sp.getPlayer().getInventory().setLeggings(ll);
			sp.getPlayer().getInventory().setBoots(lb);
		}
	}
}
