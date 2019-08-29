package Siege.SiegeEvent;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import Siege.SiegeBattleMain;

public class PumpkinBreakEvent implements Listener{

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (p.getGameMode().equals(GameMode.CREATIVE)) return;
		if (b.getType().equals(Material.PUMPKIN) || b.getType().equals(Material.CARVED_PUMPKIN)) {
			//復活処理
			int amount = 1;
			Material mat = Material.BREAD;

			p.getInventory().addItem(new ItemStack(mat, amount));
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 0.5F, 1F);
			e.setDropItems(false);

			new BukkitRunnable() {
				@Override
				public void run() {
					b.setType(Material.PUMPKIN);
					b.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOOD_BREAK, 0.8F, 0.5F);
				}
			}.runTaskLater(SiegeBattleMain.siegeBattleMain, 20 * 10);
		}
	}
}
