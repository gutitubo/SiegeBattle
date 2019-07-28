package Siege.SiegeEvent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GappleEatEvent implements Listener{
	
	@EventHandler
	public void onAte (PlayerItemConsumeEvent e) {
		if (e.getItem().getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
			Player player = e.getPlayer();
			boolean flag = false;
			e.setCancelled(true);
			if (player.getInventory().getItemInMainHand() != null 
					&& player.getInventory().getItemInMainHand().getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
				//右手にガップル
				flag = true;
			} else if (player.getInventory().getItemInOffHand() != null 
					&& player.getInventory().getItemInOffHand().getType().equals(Material.ENCHANTED_GOLDEN_APPLE)) {
				//左手にガップル
				flag = true;
			}
			if (flag) {
				int slot = player.getInventory().getHeldItemSlot();
				int amount = player.getInventory().getItem(slot).getAmount();
				if (amount >= 2) {
					player.getInventory().getItem(slot).setAmount(player.getInventory().getItem(slot).getAmount() - 1);
				} else if (amount == 1) {
					player.getInventory().clear(slot);
				}
				
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION , 20 * 20, 3));
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE , 20 * 60 * 5, 0));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE , 20 * 60 * 5, 0));
			}
		}
	}
	
}
