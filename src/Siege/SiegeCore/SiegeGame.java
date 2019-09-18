package Siege.SiegeCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import Siege.SiegeBattleMain;
import Siege.Rune.RuneScheduler;
import Siege.Rune.Runes;
import Siege.SiegeException.IkaretaPhaseException;
import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeStage.SiegeStage;
import Siege.SiegeStage.StageLoader;
import Siege.SiegeTeam.SiegeTeam;

public class SiegeGame {

	private SiegeTeam redTeam;
	private SiegeTeam blueTeam;

	private Team red;
	private Team blue;

	private SiegeStage siegeStage;

	private SiegeInformationBar infoBar;

	private int phase = 0;

	private final int INVINCIBLE_TIME = 60 * 6; //default = 60 * 6
	private final int READY_TIME = 30; //default = 30

	private HashMap<Player, SiegeTeam> leaver;

	public SiegeGame(SiegeTeam redTeam, SiegeTeam blueTeam, Team red, Team blue) {
		phase = 0;
		this.redTeam = redTeam;
		this.blueTeam = blueTeam;

		this.red = red;
		this.blue = blue;

		leaver = new HashMap<>();
	}

	public void start() throws IkaretaPhaseException{
		if (getPhase() != 1) {
			throw new IkaretaPhaseException("何かがおかしい…");
		}
		//服を着せる & アイテムを渡す
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(ChatColor.GOLD + "ゲームが開始されました。");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");

		for (SiegePlayer sp : getAllPlayer()) {
			Runes[] cur = sp.getCurrentRunes();
			Runes[] pre = Siege.SiegeBattleMain.preSelect.get(sp.getPlayer()).getRunes();
			sp.setMainPath(Siege.SiegeBattleMain.preSelect.get(sp.getPlayer()).getMain());
			sp.setSubPath(Siege.SiegeBattleMain.preSelect.get(sp.getPlayer()).getSub());
			for (int i=0; i<5; i++) {
				cur[i] = pre[i];
			}
			sp.runeStatusReflect();
		}

		redTeam.giveTeamItem(Color.RED);
		blueTeam.giveTeamItem(Color.BLUE);
		//		ロビーに飛ばす
		redTeam.gotoSpawn(siegeStage.getRedSpawnCenter());
		blueTeam.gotoSpawn(siegeStage.getBlueSpawnCenter());

		setPhase(2);
		//スケヂューラーーー

		//チームごとのボスバー出す
		redTeam.createNewBar(blueTeam);
		blueTeam.createNewBar(redTeam);
		//無敵時間ボスバー出す
		infoBar = new SiegeInformationBar("無敵時間: ", BarColor.YELLOW, BarStyle.SEGMENTED_6);
		infoBar.setToTeam(getRedTeam());
		infoBar.setToTeam(getBlueTeam());

		//スケジューラー
		new BukkitRunnable() {
			int count = INVINCIBLE_TIME;
			@Override
			public void run() {
				infoBar.setBarTitle(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "無敵時間: " + ChatColor.YELLOW + (count/60+1) +" 分");
				infoBar.setProgress(count/(double)INVINCIBLE_TIME);

				if (count <= 0) { //準備終了のヤツ
					setPhase(3);
					infoBar.remove();
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(ChatColor.RED + "Coreダメージが有効になりました");
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("");
					this.cancel();
				}
				count--;
			}
		}.runTaskTimer(SiegeBattleMain.siegeBattleMain, 20, 20);

	}

	public void ready() throws IkaretaPhaseException{
		if (getPhase() != 0) {
			throw new IkaretaPhaseException("何かがおかしい…");
		}
		//アナウンスする
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(ChatColor.GOLD + "ゲームを開始します");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");

		//赤チームアナウンス
		teamJoinAnnounce(redTeam);
		teamJoinAnnounce(blueTeam);

		//ステージローダー
		setSiegeStage(StageLoader.loadOrenoird()); //TODO ステージ選択もできるようにしたいね

		redTeam.startUp(getSiegeStage().getRedSpawnCenter());
		blueTeam.startUp(getSiegeStage().getBlueSpawnCenter());

		setPhase(1);

		RuneScheduler rs = new RuneScheduler(this);
		rs.runTaskTimer(SiegeBattleMain.siegeBattleMain, 20, 20); //1秒おきに実行

		//スケヂューラーーー
		new BukkitRunnable() {
			int count = READY_TIME;
			@Override
			public void run() {
				Lib.SiegeLib.siegeMsg("準備しろ 残り" + count + "秒");
				if (count <= 0) {
					try {
						start();
						this.cancel();
					} catch (IkaretaPhaseException e) {
						// TODO 自動生成された catch ブロック
						Bukkit.broadcastMessage("やばいよ");
						e.printStackTrace();
					}
				}
				count--;
			}
		}.runTaskTimer(SiegeBattleMain.siegeBattleMain, 20, 20);
	}

	public void end(SiegeTeam t) {
		SiegeTeam win;
		SiegeTeam lose = t;
		//勝利チーム 敗北チーム変数格納
		if (t.equals(redTeam)) {
			win = blueTeam;
		} else {
			win = redTeam;
		}

		//インフォメーション処理
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(win.getColor() + "■ " + win.getTeamName() + " WIN! ■");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("");

		//エフェクト処理
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);
		}

		//ボスバー処理
		String winText =win.getColor() + "■ " + win.getTeamName() + " WIN! ■" + ChatColor.RESET + " | " + ChatColor.GOLD + "Core: " + win.getCore();
		win.getInfoBar().setBarTitle(winText);
		lose.getInfoBar().setBarTitle(winText);

		setPhase(0);
		//スケヂューラーーー
		new BukkitRunnable() {
			@Override
			public void run() {
				for (SiegePlayer sp : getAllPlayer()) {
					displayOwnScore(sp);
				}
			}
		}.runTaskLater(SiegeBattleMain.siegeBattleMain, 20 * 5);

		new BukkitRunnable() {
			@Override
			public void run() {
				displayLeaderBoard();
			}
		}.runTaskLater(SiegeBattleMain.siegeBattleMain, 20 * 10);

		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage(ChatColor.YELLOW + "20秒後にServerを再起動します。");
				for (Player p : Bukkit.getOnlinePlayers()) {
					Lib.SiegeLib.teleportSpawn(p);

					red.unregister();
					blue.unregister();
				}
			}
		}.runTaskLater(SiegeBattleMain.siegeBattleMain, 20 * 20);

		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.shutdown();
			}
		}.runTaskLater(SiegeBattleMain.siegeBattleMain, 20 * 40);
	}

	public boolean isSiegePlayer(Player p) {
		boolean bool = false;
		SiegeTeam red = getRedTeam();
		SiegeTeam blue = getBlueTeam();
		if (red.isMember(p) || blue.isMember(p)) bool = true;
		return bool;
	}

	public SiegePlayer getSiegePlayer(Player p) {
		SiegePlayer sp = null;
		SiegeTeam red = getRedTeam();
		SiegeTeam blue = getBlueTeam();
		if (isSiegePlayer(p)) {
			sp = red.isMember(p) ? red.getMember(p) : blue.getMember(p);
		}
		return sp;
	}

	public void coreDamage(SiegePlayer p, SiegeTeam t) {
		SiegeInformationBar allyBar = p.getTeam().getInfoBar();
		t.setCore(t.getCore() - 1);

		//ボスバー変更処理
		allyBar.setProgress(t.getCore()/100.0);
		p.getTeam().barReflesh(t);
		t.barReflesh(p.getTeam());

		//インフォメーション処理
		Bukkit.broadcastMessage(p.toString());
		Bukkit.broadcastMessage(t.toString() + ChatColor.GOLD +  " CORE DAMAGE : 1");
		for (SiegePlayer sp : t.getSiegePlayerList().getPlayerList()) {
			sp.getPlayer().sendMessage(ChatColor.YELLOW + "=== 自陣のCoreが攻撃されとります ===");
		}

		//破壊サウンド
		p.getPlayer().getWorld().playSound(p.getPlayer().getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 0.3F, 1F);
		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.playSound(pl.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 1F);
		}

		//終了処理
		if (t.getCore() <= 0) {
			end(t);
		}
	}

	public void displayLeaderBoard() {
		ArrayList<SiegePlayer> players = getAllPlayer();

		/* コアダメージソート */
		Collections.sort(players, (sp1, sp2) -> sp1.getStats().getCoreDamageDealt() - sp2.getStats().getCoreDamageDealt());
		Collections.reverse(players);

		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(ChatColor.GOLD + "- Core Damage Leaders -");
		Bukkit.broadcastMessage("");
		for (int i = 0; i < 5; i++) {
			if (players.size() <= i) break;
			SiegePlayer sp = players.get(i);
			Bukkit.broadcastMessage(sp.getTeam().getColor() + sp.getPlayer().getDisplayName() + ": "
			+ sp.getStats().getCoreDamageDealt() + " Damage.");
		}
		Bukkit.broadcastMessage("==================");
		Bukkit.broadcastMessage("");
	}

	public void displayOwnScore(SiegePlayer sp) {
		Player p = sp.getPlayer();
		ChatColor c = sp.getTeam().getColor();
		p.sendMessage("");
		p.sendMessage("==================");
		p.sendMessage(ChatColor.GOLD + "- Your Score -");
		p.sendMessage("");
		p.sendMessage(c + "CoreDamage: " + sp.getStats().getCoreDamageDealt());
		p.sendMessage(c + "Kill: " + sp.getStats().getKillCount());
		p.sendMessage(c + "Death: " + sp.getStats().getDeathCount());
		p.sendMessage("==================");
		p.sendMessage("");
	}

	public ArrayList<SiegePlayer> getAllPlayer() {
		ArrayList<SiegePlayer> splist = new ArrayList<>();
		for (SiegePlayer red : this.getRedTeam().getSiegePlayerList().getPlayerList()) {
			splist.add(red);
		}
		for (SiegePlayer blue : this.getBlueTeam().getSiegePlayerList().getPlayerList()) {
			splist.add(blue);
		}
		return splist;
	}

	public void coreEffect(Location loc) {
		Location l = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
		l.add(0.5, 0.5, 0.5);
		l.getWorld().spawnParticle(Particle.LAVA, l, 3);
		l.getWorld().spawnParticle(Particle.CLOUD, l, 20);
		l.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, l, 5);
	}

	public void teamJoinAnnounce(SiegeTeam team) {
		for (SiegePlayer sp : team.getSiegePlayerList().getPlayerList()) {
			Player p = sp.getPlayer();
			p.sendMessage(team.getColor() + "貴方は" + team.getTeamName() + "に所属しました");
		}
	}

	public void addLeaver(Player p, SiegeTeam team) {
		leaver.put(p, team);
	}

	public void removeLeaver(Player p) {
		leaver.remove(p);
	}

	public SiegeTeam getLeaverTeam(Player p) {
		return leaver.get(p);
	}

	public void clearLeaver() {
		leaver.clear();
	}

	public void disbandTeam() {
		this.red.unregister();
		this.blue.unregister();
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		// 0 = kaisi mae
		// 1 = kaisi junbi
		// 2 = kaisi muteki
		// 3 = kaisi freedom
		this.phase = phase;
	}

	public SiegeTeam getRedTeam() {
		return redTeam;
	}

	public void setRedTeam(SiegeTeam redTeam) {
		this.redTeam = redTeam;
	}

	public SiegeTeam getBlueTeam() {
		return blueTeam;
	}

	public void setBlueTeam(SiegeTeam blueTeam) {
		this.blueTeam = blueTeam;
	}

	public SiegeStage getSiegeStage() {
		return siegeStage;
	}

	public void setSiegeStage(SiegeStage siegeStage) {
		this.siegeStage = siegeStage;
	}

	public ArrayList<SiegePlayer> getJoinedPlayer() {
		ArrayList<SiegePlayer> players = new ArrayList<SiegePlayer>();
		players.addAll(getRedTeam().getSiegePlayerList().getPlayerList());
		players.addAll(getBlueTeam().getSiegePlayerList().getPlayerList());
		return players;
	}

	public boolean isLeaver(Player p) {
		return leaver.containsKey(p);
	}
}
