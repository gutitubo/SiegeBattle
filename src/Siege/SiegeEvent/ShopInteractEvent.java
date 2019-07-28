package Siege.SiegeEvent;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import Siege.SiegeShop.ExpShop;
import Siege.SiegeShop.NormalShop;

public class ShopInteractEvent implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();

		if (b == null) { return; }
		if (isSign(b)) {
			Sign sign = (Sign) b.getState();
			if (sign.getLine(2).contains(SignPlaceEvent.normalshopname)) { //DARKNESS SHOP
				p.openInventory(NormalShop.getInventory());
			}
			if (sign.getLine(2).contains(SignPlaceEvent.expshopname)) { //EXP SHOP
				p.openInventory(ExpShop.getInventory());
			}
		}
	}

	public boolean isSign(Block block) {
		boolean flag = false;
		if (block == null) return false;
		Material mat = block.getType();
		if (mat.equals(Material.ACACIA_SIGN) || mat.equals(Material.ACACIA_WALL_SIGN)) {
			flag = true;
		}
		if (mat.equals(Material.BIRCH_SIGN) || mat.equals(Material.BIRCH_WALL_SIGN)) {
			flag = true;
		}
		if (mat.equals(Material.DARK_OAK_SIGN) || mat.equals(Material.DARK_OAK_WALL_SIGN)) {
			flag = true;
		}
		if (mat.equals(Material.JUNGLE_SIGN) || mat.equals(Material.JUNGLE_WALL_SIGN)) {
			flag = true;
		}
		if (mat.equals(Material.OAK_SIGN) || mat.equals(Material.OAK_WALL_SIGN)) {
			flag = true;
		}
		if (mat.equals(Material.SPRUCE_SIGN) || mat.equals(Material.SPRUCE_WALL_SIGN)) {
			flag = true;
		}
		return flag;
	}
}
