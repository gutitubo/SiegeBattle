package Siege.SiegeEvent;

import static Lib.ConstStrings.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignPlaceEvent implements Listener {

	@EventHandler
	public void onPlaced (SignChangeEvent e) {
		if (e.getPlayer().isOp()) {
			if (e.getLine(0).equalsIgnoreCase("siegeshop")) {
				e.setLine(0, SHOP_SIGN_LINE);
				e.setLine(1, SHOP_SIGN_SIEGE);
				e.setLine(2, SHOP_NORMAL_STRING);
				e.setLine(3, SHOP_SIGN_LINE);
			} else if (e.getLine(0).equalsIgnoreCase("expshop")) {
				e.setLine(0, SHOP_SIGN_LINE);
				e.setLine(1, SHOP_SIGN_SIEGE);
				e.setLine(2, SHOP_EXP_STRING);
				e.setLine(3, SHOP_SIGN_LINE);
			} else if (e.getLine(0).equalsIgnoreCase("siegerune")) {
				e.setLine(0, RUNE_SIGN_LINE);
				e.setLine(1, RUNE_SIGN_SIEGE);
				e.setLine(2, RUNE_SIGN_STRING);
				e.setLine(3, RUNE_SIGN_LINE);
			}
		}
	}
}
