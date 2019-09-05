package Siege.BonusChest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class BonusChestLoot {

	public static ItemStack getLoot() {
		Random rnd = new Random();
		if (rnd.nextInt(100) < 30) {
			Random rare = new Random();
			int rarelity = rare.nextInt(1000);
			if (rarelity < 1) {
				return getLegendaryLoot();
			} else if (rarelity < 100) {
				return getEpicLoot();
			} else if ( rarelity < 350) {
				return getRareLoot();
			} else {
				return getNormalLoot();
			}
		}
		return null;
	}

	public static ItemStack getLegendaryLoot() {
		ArrayList<Legendary> list = new ArrayList<>();
		for (Legendary l : Legendary.values()) {
			list.add(l);
		}
		Collections.shuffle(list);
		return list.get(0).toItemStack();
	}

	public static ItemStack getEpicLoot() {
		return null;
	}

	public static ItemStack getRareLoot() {
		return null;
	}

	public static ItemStack getNormalLoot() {
		return null;
	}
}
