package Lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class RndFirework {
	private RndFirework() {}

	public static void spawn(Location l) {
		Firework fire = (Firework)l.getWorld().spawnEntity(l, EntityType.FIREWORK);
		randomEffect(fire);
	}

	public static void randomEffect(Firework fire) {
		FireworkMeta meta = fire.getFireworkMeta();
		meta.setPower(0);
		FireworkEffect eff = FireworkEffect.builder().withColor(getRandomColor()).with(getFireType()).flicker(getRandomBool()).trail(getRandomBool()).build();
		meta.addEffect(eff);
		fire.setFireworkMeta(meta);
	}

	public static boolean getRandomBool() {
		Random rnd = new Random();
		return rnd.nextBoolean();
	}

	public static FireworkEffect.Type getFireType() {
		ArrayList<FireworkEffect.Type> types = new ArrayList<>();
		for (FireworkEffect.Type type : FireworkEffect.Type.values()) {
			types.add(type);
		}
		Collections.shuffle(types);
		return types.get(0);
	}

	public static Color getRandomColor() {
		int blue = new Random().nextInt(255);
		int green = new Random().nextInt(255);
		int red = new Random().nextInt(255);

		return Color.fromBGR(blue, green, red);
	}
}
