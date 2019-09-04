package Siege.SiegeEvent;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import Siege.SiegeBattleMain;
import Siege.SiegeCore.SiegeGame;
import net.md_5.bungee.api.ChatColor;

public class WoodBreakEvent implements Listener {

	@EventHandler
	public void onBroke(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (p.getGameMode().equals(GameMode.CREATIVE)) return;
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if ((game == null || game.getPhase() < 1) && !p.getGameMode().equals(GameMode.CREATIVE)) e.setCancelled(true);

		if (isWood(b)) {
			//復活処理
			int amount = 1;
			Material mat = b.getType();

			p.getInventory().addItem(new ItemStack(mat, amount));
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 0.5F, 1F);
			e.setDropItems(false);

			new BukkitRunnable() {
				@Override
				public void run() {
					b.setType(mat);
					b.getWorld().playSound(b.getLocation(), Sound.BLOCK_WOOD_BREAK, 0.8F, 0.5F);
				}
			}.runTaskLater(SiegeBattleMain.siegeBattleMain, 20 * 10);
		}
	}

	public boolean isWood(Block b) {
		boolean bool = false;
		Material mat = b.getType();
		if (mat.equals(Material.ACACIA_LOG)) { bool = true; }
		if (mat.equals(Material.BIRCH_LOG)) { bool = true; }
		if (mat.equals(Material.DARK_OAK_LOG)) { bool = true; }
		if (mat.equals(Material.JUNGLE_LOG)) { bool = true; }
		if (mat.equals(Material.OAK_LOG)) { bool = true; }
		if (mat.equals(Material.SPRUCE_LOG)) { bool = true; }

		if (mat.equals(Material.STRIPPED_ACACIA_LOG)) { bool = true; }
		if (mat.equals(Material.STRIPPED_BIRCH_LOG)) { bool = true; }
		if (mat.equals(Material.STRIPPED_DARK_OAK_LOG)) { bool = true; }
		if (mat.equals(Material.STRIPPED_JUNGLE_LOG)) { bool = true; }
		if (mat.equals(Material.STRIPPED_OAK_LOG)) { bool = true; }
		if (mat.equals(Material.STRIPPED_SPRUCE_LOG)) { bool = true; }
		return bool;
	}

	@EventHandler
	public void onPlaced(BlockPlaceEvent e) {
		Block b = e.getBlock();
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
		if (isWood(b)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "それはできない");
		}
	}
}
