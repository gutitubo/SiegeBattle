package Siege.SiegeEvent;

import static Lib.Parameters.*;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BonusChestEvent implements Listener{

	public static int chance = BONUS_CHEST_CHANCE;

	@EventHandler
	public void onMining(BlockBreakEvent e) {
		Player p = e.getPlayer();

		Block b = e.getBlock();

		if (b.getType().equals(Material.STONE)) {
			Random rnd = new Random();
			int c = rnd.nextInt(chance);
			if (c <= 0) {
				//ボーナスチェスト出現処理
				p.sendMessage("あたり！！！");
			}
		}
	}
}
