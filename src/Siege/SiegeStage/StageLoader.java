package Siege.SiegeStage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class StageLoader {

	public static SiegeStage loadUnkave() {
		String stageName = "Unkave";
		String worldName = "unkave";
		WorldCreator uk = new WorldCreator(worldName);
        World unk = uk.createWorld();
		SiegeStage stage = new SiegeStage(stageName,
				new Location(unk, 0, 61, -5.5), //redCore
				new Location(unk, -26, 45.5, 2), //redSpawnRight
				new Location(unk, 27, 45.5, 2), //redSpawnLeft
				new Location(unk, 0, 5.5, -3), //redSpawnCenter
				new Location(unk, 0, 61, 258), //blueCore
				new Location(unk, 27, 45.5, 250), //blueSpawnRight
				new Location(unk, -26, 45.5, 250), //blueSpawnLeft
				new Location(unk, 0, 5.5, 256), //blueSpawnCenter
				new Location(unk, 0, 30, 126) //Mid
				);
		return stage;
	}

	public static SiegeStage loadOrenoird() { //TODO STAGE名決める
		String stageName = "OreNoir'd";
		String worldName = "orenoird";
		WorldCreator wk = new WorldCreator(worldName);
		World unk = wk.createWorld();
		SiegeStage stage = new SiegeStage(stageName,
				new Location(unk, 0, 109, -228), //redCore
				new Location(unk, 0, 0, 0), //redSpawnRight
				new Location(unk, 0, 0, 0), //redSpawnLeft
				new Location(unk, 0.5, 7.5, -226.5), //redSpawnCenter
				new Location(unk, 0, 109, 250), //blueCore
				new Location(unk, 0, 0, 0), //blueSpawnRight
				new Location(unk, 0, 0, 0), //blueSpawnLeft
				new Location(unk, 0.5, 7.5, 249.5), //blueSpawnCenter
				new Location(unk, 0, 54, 11) //Mid
				);
		return stage;
	}

	public static SiegeStage loadTestStage() {
		SiegeStage stage = new SiegeStage("テストステージ",
				new Location(Bukkit.getWorld("world"), -10, 119, 0), //red Core
				new Location(Bukkit.getWorld("world"), -9, 117, 2), //red spawn right
				new Location(Bukkit.getWorld("world"), -9, 117, -2), //red spawn left
				new Location(Bukkit.getWorld("world"), -13, 122, 0), //red spawn center
				new Location(Bukkit.getWorld("world"), 4, 119, 0), //blue Core
				new Location(Bukkit.getWorld("world"), 3, 117, -2), //blue spawn right
				new Location(Bukkit.getWorld("world"), 3, 117, 2), //blue spawn left
				new Location(Bukkit.getWorld("world"), 7, 122, 0), //blue spawn center
				new Location(Bukkit.getWorld("world"), -2, 119, 0)); //mid
		return stage;
	}
}
