package Siege.SiegeEvent;

import static Lib.ConstStrings.*;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import Siege.Rune.RuneInventory;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeShop.ExpShop;
import Siege.SiegeShop.NormalShop;

public class SignInteractEvent implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = e.getClickedBlock();

		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();

		if (b == null) { return; }
		if (isSign(b)) {
			Sign sign = (Sign) b.getState();
			if (sign.getLine(2).contains(SHOP_NORMAL_PLANE)) { //DARKNESS SHOP
				p.openInventory(NormalShop.getInventory("RED"));
			}
			if (sign.getLine(2).contains(SHOP_EXP_PLANE)) { //EXP SHOP
				p.openInventory(ExpShop.getInventory());
			}
			if (game != null && game.isSiegePlayer(p) && sign.getLine(2).contains(RUNE_SIGN_PLANE)) {
				SiegePlayer sp = game.getSiegePlayer(p);
				p.openInventory(RuneInventory.getRuneInventory(sp));
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
