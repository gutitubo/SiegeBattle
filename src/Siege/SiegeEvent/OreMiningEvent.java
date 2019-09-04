package Siege.SiegeEvent;

import static Lib.Parameters.*;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class OreMiningEvent implements Listener{
	public OreMiningEvent () {}

	@EventHandler
	public void onMining(BlockBreakEvent e) {
		Player p = e.getPlayer();
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(p)) return;
		SiegePlayer sp = game.getSiegePlayer(p);

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

				amount = COAL_AMT;
				exp = COAL_EXP;
				mat = Material.COAL;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.IRON_ORE)) {
			if (hand.equals(Material.STONE_PICKAXE) || hand.equals(Material.IRON_PICKAXE)
					|| hand.equals(Material.DIAMOND_PICKAXE) || hand.equals(Material.GOLDEN_PICKAXE)) {
				amount = sp.hasRune(Runes.COLLECT_IRON) ? IRON_AMT + RUNE_IRON_AMOUNT : IRON_AMT;
				exp = IRON_EXP;
				mat = sp.hasRune(Runes.COLLECT_HUMANFORGE) ? Material.IRON_INGOT : Material.IRON_ORE;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.GOLD_ORE)) {
			if (hand.equals(Material.IRON_PICKAXE) || hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = sp.hasRune(Runes.COLLECT_GOLD) ? GOLD_AMT + RUNE_GOLD_AMOUNT : GOLD_AMT;
				exp = GOLD_EXP;
				mat = sp.hasRune(Runes.COLLECT_HUMANFORGE) ? Material.GOLD_INGOT : Material.GOLD_ORE;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.DIAMOND_ORE)) {
			if (hand.equals(Material.IRON_PICKAXE) || hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = sp.hasRune(Runes.COLLECT_DIA) ? DIA_AMT + RUNE_DIA_AMOUNT : DIA_AMT;
				exp = DIAMOND_EXP;
				mat = Material.DIAMOND;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.REDSTONE_ORE)) {
			if (hand.equals(Material.IRON_PICKAXE) || hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = sp.hasRune(Runes.COLLECT_RED) ? RED_AMT + RUNE_RED_AMOUNT : RED_AMT;
				exp = REDSTONE_EXP;
				mat = Material.REDSTONE;
			} else {
				e.setCancelled(true);
			}
		} else if (m.equals(Material.LAPIS_ORE)) {
			if (hand.equals(Material.STONE_PICKAXE) || hand.equals(Material.IRON_PICKAXE)
					|| hand.equals(Material.DIAMOND_PICKAXE)) {
				amount = LAPIS_AMT;
				exp = LAPIS_EXP;
				mat = Material.LAPIS_BLOCK;
			} else {
				e.setCancelled(true);
			}
		} else {
			flag = true;
		}

		if (!flag) {
			if (sp.hasRune(Runes.COLLECT_MINER)) {
				if (sp.hasRune(Runes.MAGIC_KEYSTONE)) {
					amount += RUNE_MINER_VALUE_ENHANCED;
				} else {
					amount += RUNE_MINER_VALUE;
				}
			}
			p.getInventory().addItem(new ItemStack(mat, new Random().nextInt(amount) + 1));
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 0.5F, 1F);
			if (sp.hasRune(Runes.MAGIC_MAGICIAN)) {
				if (sp.hasRune(Runes.MAGIC_KEYSTONE)) {
					exp *= RUNE_EXPUP_MULTIPLY_ENHANCED;
				} else {
					exp *= RUNE_EXPUP_MULTIPLY;
				}
			}
			if (sp.hasRune(Runes.MAGIC_EXPUP)) exp *= RUNE_EXPUP2_MULTIPLY;
			if (sp.hasRune(Runes.MAGIC_EXPPASSER)) {
				for (SiegePlayer ally : sp.getTeam().getSiegePlayerList().getPlayerList()) {
					if (ally.getPlayer().getLocation().distance(p.getLocation()) <= RUNE_EXPPASSER_RANGE) {
						ally.getPlayer().giveExp((int) (exp * RUNE_EXPPASSER_RATIO));
					}
				}
			}
			p.giveExp(exp);
			e.setDropItems(false);
			e.setExpToDrop(0);
		}
	}
}
