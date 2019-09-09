package Siege.Rune.Events;

import static Lib.Parameters.*;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;

public class OnDrunk implements Listener {
	@EventHandler
	public void onDrunkPotion(PlayerItemConsumeEvent e) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		Player p = e.getPlayer();
		if (!game.isSiegePlayer(p)) return;
		SiegePlayer sp = game.getSiegePlayer(p);

		if (sp.hasRune(Runes.MAGIC_POTION_DUR)) {
			if (e.getItem().getType().equals(Material.POTION)) {
				ItemStack pot = e.getItem();
				PotionMeta potMeta = (PotionMeta) pot.getItemMeta();
				if (!potMeta.getBasePotionData().getType().equals(PotionType.WATER) || potMeta.hasColor()) {
					for (PotionEffect eff : p.getActivePotionEffects()) {
						p.removePotionEffect(eff.getType());
						p.addPotionEffect(new PotionEffect(eff.getType(), (int) (eff.getDuration() * RUNE_POTDUR_MULTIPLY), eff.getAmplifier()));
					}
				}
			} else {
				return;
			}
		}
	}
}
