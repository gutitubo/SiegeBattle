package Siege.SiegeEvent;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import Siege.Rune.Runes;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;
import net.md_5.bungee.api.ChatColor;

public class CoreDamageEvent implements Listener{
	@EventHandler
	public void breakingCore (BlockBreakEvent e) {
		Player p = e.getPlayer();
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) {
			return ;
		}
		if (p.getInventory().getItemInMainHand() == null) return;
		if (!p.getInventory().getItemInMainHand().getType().equals(Material.SHEARS)) return;

		Block b = e.getBlock();
		Location redCore = Siege.SiegeBattleMain.siegeBattleMain.getGame().getSiegeStage().getRedCore();
		Location blueCore = Siege.SiegeBattleMain.siegeBattleMain.getGame().getSiegeStage().getBlueCore();

		SiegePlayer sp = game.getSiegePlayer(p);

		if (Siege.SiegeBattleMain.siegeBattleMain.getGame().getPhase() >= 3) {
			//ゲームが開始していた場合

			SiegeTeam redTeam = Siege.SiegeBattleMain.siegeBattleMain.getGame().getRedTeam();
			SiegeTeam blueTeam = Siege.SiegeBattleMain.siegeBattleMain.getGame().getBlueTeam();
			if (b.equals(redCore.getBlock())) { //レッドコアだった場合
				e.setCancelled(true);
				if (redTeam.isMember(p)) { //赤の人だった場合
					p.sendMessage(Lib.SiegeLib.siegeString(ChatColor.RED + "みかた の こあ を なぐるなんて とんでもない▼"));
				} else if (blueTeam.isMember(p)) { //青の人だった場合
					//コア破壊処理
					Siege.SiegeBattleMain.siegeBattleMain.getGame().coreEffect(redCore);
					Siege.SiegeBattleMain.siegeBattleMain.getGame().coreDamage(blueTeam.getMember(p), redTeam);
					onBrokenRuneEffect(p);
					sp.getStats().addCoreDamageDealt();
				}
			} else if (b.equals(blueCore.getBlock())) { //ブルーコアだった場合
				e.setCancelled(true);
				if (blueTeam.isMember(p)) { //青の人だった場合
					p.sendMessage(Lib.SiegeLib.siegeString(ChatColor.RED + "みかた の こあ を なぐるなんて とんでもない▼"));
				} else if (redTeam.isMember(p)) { //垢の人だった場合
					//コア破壊処理

					Siege.SiegeBattleMain.siegeBattleMain.getGame().coreEffect(blueCore);
					Siege.SiegeBattleMain.siegeBattleMain.getGame().coreDamage(redTeam.getMember(p), blueTeam);
					onBrokenRuneEffect(p);
					sp.getStats().addCoreDamageDealt();
				}
			}
		} else {
			if (b.equals(redCore.getBlock()) || b.equals(blueCore.getBlock())) {
				e.setCancelled(true);
				return;
			}
		}
	}

	public void onBrokenRuneEffect(Player p) {
		SiegeGame game = Siege.SiegeBattleMain.siegeBattleMain.getGame();
		if (game == null) return;
		if (!game.isSiegePlayer(p)) return;
		SiegePlayer sp = game.getSiegePlayer(p);

		if (sp.hasRune(Runes.MAGIC_CORESHIELD)) {
			sp.setAdditionalDefendPerm(sp.getAdditionalDefendPerm() + 1);
			sp.statusReflect();
		}
	}
}
