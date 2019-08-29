package Siege;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import Siege.Enchant.Event.onEnchant;
import Siege.Recipe.GappleRecipe;
import Siege.Rune.Events.OnClickedEvent;
import Siege.Rune.Events.OnDamaged;
import Siege.Rune.Events.OnDeathEvent;
import Siege.Rune.Events.OnMoved;
import Siege.Rune.Events.PlayerAttackEvent;
import Siege.SiegeCore.SiegeGame;
import Siege.SiegeEvent.BonusChestEvent;
import Siege.SiegeEvent.BreakCancelEvent;
import Siege.SiegeEvent.CoreDamageEvent;
import Siege.SiegeEvent.FallDamageEvent;
import Siege.SiegeEvent.GappleEatEvent;
import Siege.SiegeEvent.LoginEvent;
import Siege.SiegeEvent.OreMiningEvent;
import Siege.SiegeEvent.PlayerAttackWithHandEvent;
import Siege.SiegeEvent.PumpkinBreakEvent;
import Siege.SiegeEvent.Recall;
import Siege.SiegeEvent.RespawnEvent;
import Siege.SiegeEvent.RuneInteractEvent;
import Siege.SiegeEvent.ShearsDropEvent;
import Siege.SiegeEvent.ShopItemInteractEvent;
import Siege.SiegeEvent.SignInteractEvent;
import Siege.SiegeEvent.SignPlaceEvent;
import Siege.SiegeEvent.SignTeleport;
import Siege.SiegeEvent.TeamChatEvent;
import Siege.SiegeEvent.WoodBreakEvent;
import Siege.SiegeEvent.checkingLocation;
import Siege.SiegeException.IkaretaPhaseException;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegePlayer.SiegePlayerList;
import Siege.SiegeTeam.SiegeTeam;

public class SiegeBattleMain extends JavaPlugin implements Listener {

	private SiegeGame game;
	public static SiegeBattleMain siegeBattleMain;

	@Override
	public void onEnable() {
		siegeBattleMain = this;
		getServer().getPluginManager().registerEvents(this, this);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		eventRegist(pm);
		runeEventRegist(pm);
		enchantmentEventRegist(pm);
		recipeRegist();
		Bukkit.getWorlds().forEach( w -> {
			w.setGameRule(GameRule.KEEP_INVENTORY, true);
		});
	}

	@Override
	public void onDisable() {
		if (getGame() != null) {
			getGame().disbandTeam();
		}
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("sb")){
			sender.sendMessage(ChatColor.GOLD + "=== Siege Battle ===");
			if (args[0] != null && args[0].equalsIgnoreCase("help")) {
				sender.sendMessage("/recall - リコールコマンド");
				sender.sendMessage("/r - リコールコマンド");
				sender.sendMessage("/info - 自分の情報を確認(未実装)");
				if (sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "/start - ゲームを開始する");
					sender.sendMessage(ChatColor.RED + "/info <name> - 他人の情報を確認する");
					sender.sendMessage(ChatColor.RED + "<各看板の作り方>");
					sender.sendMessage(ChatColor.RED + "1行目に指定された文字を入れることでショップ等になる");
					sender.sendMessage(ChatColor.RED + "siegeshop - 鉱石ショップを設置");
					sender.sendMessage(ChatColor.RED + "expshop - 経験値ショップを設置");
					sender.sendMessage(ChatColor.RED + "siegerune - ルーンセレクターを設置");
				}
			} else {
				sender.sendMessage("/sb help - このプラグインのコマンドが見れます");
				return true;
			}
			sender.sendMessage(ChatColor.GOLD + "===================");
		}
		if(cmd.getName().equalsIgnoreCase("recall") || cmd.getName().equalsIgnoreCase("r")) {
			if (getGame().getPhase() >= 2) {
				if(sender instanceof Player) {
					Player p = ((Player) sender).getPlayer();
					Recall r = Recall.createInstance(p);
					if (r != null) {
						Bukkit.getPluginManager().registerEvents(r, this);
					}
					return true;
				}
			}
			return false;
		}
		if(cmd.getName().equalsIgnoreCase("info")) {
			if (getGame().getPhase() >= 2) {
				if(sender instanceof Player) {
					Player p = ((Player) sender).getPlayer();
					if (getGame() == null) return false;
					if (!getGame().isSiegePlayer(p)) return false;
					getGame().getSiegePlayer(p).showRunesString(p);
					return true;
				}
			}
			return false;
		}
		if(cmd.getName().equalsIgnoreCase("start")){
			//チームつくんないといけない
			ScoreboardManager sbm = Bukkit.getScoreboardManager();
			Scoreboard sb = sbm.getMainScoreboard();

			SiegePlayerList redList = new SiegePlayerList();
			SiegePlayerList blueList = new SiegePlayerList();

			ArrayList<Player> players = new ArrayList<Player>();
			Bukkit.getOnlinePlayers().forEach(p -> {
				if (!p.getGameMode().equals(GameMode.CREATIVE)) players.add(p);
			});
			Collections.shuffle(players);

			//任意のチームを創れるようにする
			//ex) 先に赤ブロックに乗っていると赤チーム
			if (sb.getTeam("RED") != null) {
				sb.getTeam("RED").unregister();
			}
			Team redTeam = sb.registerNewTeam("RED");
			redTeam.setPrefix(ChatColor.RED.toString());
			redTeam.setCanSeeFriendlyInvisibles(true);
			redTeam.setAllowFriendlyFire(false);
			redTeam.setOption(Option.COLLISION_RULE, OptionStatus.NEVER);
			redTeam.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OTHER_TEAMS);

			if (sb.getTeam("BLUE") != null) {
				sb.getTeam("BLUE").unregister();
			}
			Team blueTeam = sb.registerNewTeam("BLUE");
			blueTeam.setPrefix(ChatColor.BLUE.toString());
			blueTeam.setCanSeeFriendlyInvisibles(true);
			blueTeam.setAllowFriendlyFire(false);
			blueTeam.setOption(Option.COLLISION_RULE, OptionStatus.NEVER);
			blueTeam.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OTHER_TEAMS);


			int i = 0;
			for (Player p : players) {
				if (i%2==0) {
					redList.getPlayerList().add(new SiegePlayer(p));
					redTeam.addPlayer(p);
				} else {
					blueList.getPlayerList().add(new SiegePlayer(p));
					blueTeam.addPlayer(p);
				}
				i++;
			}

			SiegeTeam red = new SiegeTeam("RED TEAM", redList, ChatColor.RED);
			SiegeTeam blue = new SiegeTeam("BLUE TEAM", blueList, ChatColor.BLUE);

			try {
				if (game == null) {
					game = new SiegeGame(red, blue, redTeam, blueTeam);
				}
				game.ready();
			} catch(IkaretaPhaseException e){
				Bukkit.broadcastMessage("ぐちつぼさんなんかエラー出てますよ");
				e.printStackTrace();
			}

			return true;
		}
		return false;
	}

	public void eventRegist(PluginManager pm) {
		pm.registerEvents(new TeamChatEvent(), this);
		pm.registerEvents(new checkingLocation(), this);
		pm.registerEvents(new CoreDamageEvent(), this);
		pm.registerEvents(new PlayerAttackWithHandEvent(), this);
		pm.registerEvents(new OreMiningEvent(), this);
		pm.registerEvents(new WoodBreakEvent(), this);
		pm.registerEvents(new BonusChestEvent(), this);
		pm.registerEvents(new Recall(), this);
		pm.registerEvents(new BreakCancelEvent(), this);
		pm.registerEvents(new PumpkinBreakEvent(), this);
		pm.registerEvents(new RespawnEvent(), this);
		pm.registerEvents(new SignTeleport(), this);
		pm.registerEvents(new LoginEvent(), this);
		pm.registerEvents(new ShearsDropEvent(), this);
		pm.registerEvents(new SignPlaceEvent(), this);
		pm.registerEvents(new SignInteractEvent(), this);
		pm.registerEvents(new ShopItemInteractEvent(), this);
		pm.registerEvents(new FallDamageEvent(), this);
		pm.registerEvents(new GappleEatEvent(), this);
		pm.registerEvents(new RuneInteractEvent(), this);
	}

	public void runeEventRegist(PluginManager pm) {
		pm.registerEvents(new PlayerAttackEvent(), this);
		pm.registerEvents(new OnDeathEvent(), this);
		pm.registerEvents(new OnClickedEvent(), this);
		pm.registerEvents(new OnMoved(), this);
		pm.registerEvents(new OnDamaged(), this);
	}

	public void enchantmentEventRegist(PluginManager pm) {
		pm.registerEvents(new onEnchant(), this);
//		pm.registerEvents(new onClickedToEnchant(), this);
	}

	public void recipeRegist() {
		GappleRecipe.addRecipe();
	}

	public SiegeGame getGame() {
		return this.game;
	}
}
