package Siege.Rune.Events;

import static Lib.ConstStrings.*;
import static Lib.Parameters.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Siege.Rune.Runes;
import Siege.SiegePlayer.SiegePlayer;

public class OnClickedEvent implements Listener{

	@EventHandler
	public void onClicked(InventoryClickEvent e) {
		HumanEntity human = e.getWhoClicked();

		Player p = human instanceof Player ? Bukkit.getPlayer(human.getName()) : null ;
		if (p == null) { return; }

		InventoryView inv = p.getOpenInventory();
		String iname = inv.getTitle();

		if (!iname.equalsIgnoreCase(RUNE_INV_TITLE_MAIN)) {
			return;
		}

		e.setCancelled(true);
		SiegePlayer sp = null;
		if (Siege.SiegeBattleMain.siegeBattleMain.getGame() != null && Siege.SiegeBattleMain.siegeBattleMain.getGame().isSiegePlayer(p)) {
			sp = Siege.SiegeBattleMain.siegeBattleMain.getGame().getSiegePlayer(p);
		}
		ItemStack clicked = e.getCurrentItem();
		if (clicked == null) return;
		if (!clicked.getEnchantments().isEmpty()) return;

		if (clicked.getType().equals(Material.NETHER_STAR)) return;

		if (Runes.isRune(clicked)) {
			runeStatusReflect(sp);
		} else {
			return;
		}
		/* === ここから実装 === */

	}

	public void runeStatusReflect(SiegePlayer p) {
		for (Runes r : p.getCurrentRunes()) {
			switch (r) {
			case BATTLE_HEALTHBOOST:
				p.setAdditionalHealth(RUNE_HEALTHBOOST_VALUE);
				break;
			case BATTLE_REGENERATION:
				p.getDeafultEffect().add(new PotionEffect(PotionEffectType.REGENERATION, RUNE_REGENERATION_TIME, RUNE_REGENERATION_AMP));
				break;
			case SWIFT_SPEEDUP:
				p.setAdditionalSpeed(RUNE_SPEEDUP_VALUE);
				break;
			case SWIFT_JUMPBOOST:
				p.getDeafultEffect().add(new PotionEffect(PotionEffectType.JUMP, RUNE_JUMPBOOST_TIME, RUNE_JUMPBOOST_AMP));
			default:
				break;
			}
		}
		p.refleshRuneStatus();
	}
}
