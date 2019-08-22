package Siege.SiegeEvent;

import static Lib.Parameters.*;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class OreMiningEvent implements Listener{
	public OreMiningEvent () {}

	@EventHandler
	public void onMining(BlockBreakEvent e) {
		Player p = e.getPlayer();

		Block b = e.getBlock();
		Material m = b.getType();
		if ( p.getInventory().getItemInMainHand() == null) {
			return;
		}
		Material hand = p.getInventory().getItemInMainHand().getType();

		int amount = 1;
		int exp = 0;
		Material mat = null;
		boolean flag = false; //鉱石だったら

		if (m.equals(Material.COAL_ORE)) {
			if (hand.equals(Material.WOODEN_PICKAXE) || hand.equals(Material.STONE_PICKAXE)
					|| hand.equals(Material.IRON_PICKAXE) || hand.equals(Material.GOLDEN_PICKAXE)
					|| hand.equals(Material.DIAMOND_PICKAXE)) {

				amount = 1;
				exp = COAL_EXP;
				mat = Material.COAL;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.IRON_ORE)) {
			if (hand.equals(Material.STONE_PICKAXE) || hand.equals(Material.IRON_PICKAXE)
					|| hand.equals(Material.DIAMOND_PICKAXE) || hand.equals(Material.GOLDEN_PICKAXE)) {
				amount = 1;
				exp = IRON_EXP;
				mat = Material.IRON_ORE;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.GOLD_ORE)) {
			if (hand.equals(Material.IRON_PICKAXE) || hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = 1;
				exp = GOLD_EXP;
				mat = Material.GOLD_ORE;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.DIAMOND_ORE)) {
			if (hand.equals(Material.IRON_PICKAXE) || hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = 1;
				exp = DIAMOND_EXP;
				mat = Material.DIAMOND;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.REDSTONE_ORE)) {
			if (hand.equals(Material.IRON_PICKAXE) || hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = 3;
				exp = REDSTONE_EXP;
				mat = Material.REDSTONE;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.LAPIS_ORE)) {
			if (hand.equals(Material.STONE_PICKAXE) || hand.equals(Material.IRON_PICKAXE)
					|| hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = 1;
				exp = LAPIS_EXP;
				mat = Material.LAPIS_BLOCK;
			} else {
				e.setCancelled(true);
			}
		} else {
			flag = true;
		}

		if (!flag) {
			p.getInventory().addItem(new ItemStack(mat, amount));
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 0.5F, 1F);
			p.giveExp(exp);
			e.setDropItems(false);
			e.setExpToDrop(0);
		}
	}
}
