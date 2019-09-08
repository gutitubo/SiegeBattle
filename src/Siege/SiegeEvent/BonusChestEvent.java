package Siege.SiegeEvent;

import static Lib.Parameters.*;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;

import Lib.RndFirework;
import Siege.BonusChest.BonusChestLoot;

public class BonusChestEvent implements Listener{

	public static int chance = BONUS_CHEST_CHANCE;

	@EventHandler
	public void onMining(BlockBreakEvent e) {
		Player p = e.getPlayer();

		Block b = e.getBlock();

		if (b.getType().equals(Material.STONE)) {
			Random rnd = new Random();
			int c = rnd.nextInt(chance);
			if (c <= 1) {
				//ボーナスチェスト出現処理
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
				spawnBonusChest(b);
				e.setCancelled(true);
			}
		}
	}

	public static void spawnBonusChest(Block b) {
		b.setType(Material.TRAPPED_CHEST);
		Location loc = b.getLocation();
		loc.add(0.5, 0.5, 0.5);
		RndFirework.spawn(b.getLocation());
		loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 20, 0.5, 0.5, 0.5);
		Chest chest = (Chest) b.getState();
		Inventory inv = chest.getInventory();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, BonusChestLoot.getLoot());
		}
	}
}
