package Siege.Rune.Events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Lib.Parameters;
import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegeException.IkaretaPhaseException;
import Siege.SiegePlayer.SiegePlayer;
import net.md_5.bungee.api.ChatColor;

public class PlayerAttackEvent implements Listener {

	@EventHandler
	public void onAttacked(EntityDamageByEntityEvent e) {
		Player p = null;
		if (e.getDamager() instanceof Player) {
			p = (Player) e.getDamager();
		} else {
			return;
		}

		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (game.getPhase() < 2) return;
		if (!game.isSiegePlayer(p)) return;
		SiegePlayer sp = game.getSiegePlayer(p);

		/* ---------- 共通処理 ---------- */
		if (p.getInventory().getItemInMainHand() != null && !isSword(p.getInventory().getItemInMainHand().getType())) {
			e.setDamage(e.getDamage() / 3);
		}

		/* ---------- 攻撃者側用ルーン --------- */
		if (sp.hasRune(Runes.BATTLE_MELEEDAMAGE)) { //近接攻撃強化の場合
			e.setDamage(e.getDamage() + Parameters.RUNE_MELEEDAMAGE_VALUE);
		}

		if (sp.hasRune(Runes.COLLECT_THIEF)) {
			thiefAttack(sp, sp.hasRune(Runes.MAGIC_KEYSTONE));
		}

		/* ---------- 被害者用ルーン ---------- */
		Player v = null;
		if (e.getEntity() instanceof Player) {
			v = (Player) e.getEntity();
		} else {
			return;
		}
		SiegePlayer vic = game.getSiegePlayer(v);

		if (vic.hasRune(Runes.BATTLE_TENACITY)) { //執念
			if (v.getHealth() < Parameters.RUNE_TENACITY_CASTLINE && v.getPotionEffect(PotionEffectType.ABSORPTION)==null) {
				if (vic.hasRune(Runes.MAGIC_KEYSTONE)) {
					v.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Parameters.RUNE_TENACITY_DUR_ENHANCED, Parameters.RUNE_TENACITY_VALUE_ENHANCED));
				} else {
					v.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Parameters.RUNE_TENACITY_DUR, Parameters.RUNE_TENACITY_VALUE));
				}
				v.getWorld().playSound(v.getLocation(), Sound.ENTITY_VINDICATOR_HURT, 0.5F, 0.5F);
				v.playSound(v.getLocation(), Sound.BLOCK_ANVIL_USE, 0.5F, 0.5F);
				Location l = v.getLocation();
				l.add(0,2,0);
				v.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, l, 5);
			}
		}
		if (sp.hasRune(Runes.BATTLE_ANTIDEFENDER)) { //対防衛特攻の場合
			try {
				if(vic.getDistanceWhileOwnCore() <= 10)
				e.setDamage(e.getDamage() + Parameters.RUNE_ANTIDEFENDER_VALUE);
			} catch (IkaretaPhaseException e1) {
			};
		}
		if (sp.hasRune(Runes.BATTLE_ANTIATTACKER)) { //対攻撃特攻の場合
			try {
				if(vic.getDistanceWhileEnemyCore() <= 10)
				e.setDamage(e.getDamage() + Parameters.RUNE_ANTIATTACKER_VALUE);
			} catch (IkaretaPhaseException e1) {
			};
		}

		if (vic.hasRune(Runes.MAGIC_EXPARMOR)) { //経験値アーマー
			int level = v.getLevel();
			double guard = (level / 10) * 0.1;
			if (guard > 0.5) {
				guard = 0.5;
			}
			e.setDamage(e.getDamage() - (e.getDamage() * guard));
		}

		if (vic.hasRune(Runes.MAGIC_SAFETY) || sp.hasRune(Runes.MAGIC_SAFETY)) {
			if (game.getPhase() < 3)
			e.setDamage(e.getDamage() * Parameters.RUNE_SAFETY_MULTI);
		}
	}

	public boolean isSword(Material mat) {
		boolean flag = false;
		switch(mat) {
		case WOODEN_SWORD:
		case STONE_SWORD:
		case GOLDEN_SWORD:
		case IRON_SWORD:
		case DIAMOND_SWORD:
			flag = true;
			break;
		default:
			break;
		}
		return flag;
	}

	public void thiefAttack(SiegePlayer sp, boolean enhanced) {
		int dice = new Random().nextInt(100) + 1;
		ArrayList<ItemStack> table = new ArrayList<>();
		table.add(new ItemStack(Material.DIAMOND, 1));
		table.add(new ItemStack(Material.DIAMOND, 2));
		table.add(new ItemStack(Material.IRON_INGOT, 1));
		table.add(new ItemStack(Material.IRON_INGOT, 2));
		table.add(new ItemStack(Material.IRON_INGOT, 3));
		table.add(new ItemStack(Material.COAL, 1));
		table.add(new ItemStack(Material.COAL, 2));
		table.add(new ItemStack(Material.COAL, 3));
		table.add(new ItemStack(Material.GOLD_INGOT, 1));
		table.add(new ItemStack(Material.GOLD_INGOT, 2));
		table.add(new ItemStack(Material.GOLD_INGOT, 3));
		table.add(new ItemStack(Material.LAPIS_BLOCK, 1));
		table.add(new ItemStack(Material.LAPIS_BLOCK, 2));
		table.add(new ItemStack(Material.LAPIS_BLOCK, 3));
		table.add(new ItemStack(Material.REDSTONE, 1));
		table.add(new ItemStack(Material.REDSTONE, 2));
		table.add(new ItemStack(Material.REDSTONE, 3));
		table.add(new ItemStack(Material.REDSTONE, 4));
		table.add(new ItemStack(Material.REDSTONE, 5));
		Collections.shuffle(table);
		ItemStack loot = table.get(0);
		if (enhanced && dice <= Parameters.RUNE_THIEF_CHANCE_ENHANCED) {
		} else if (dice <= Parameters.RUNE_THIEF_CHANCE) {
		} else {
			return;
		}
		//TODO 共通処理
		sp.getPlayer().getInventory().addItem(loot);
		sp.getPlayer().sendMessage(ChatColor.GOLD + "鉱石を盗み取った！");
	}
}
