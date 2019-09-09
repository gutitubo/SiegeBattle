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
			int rarelity = rare.nextInt(10000);
			if (rarelity < 1) {
				return getLegendaryLoot();
			} else if (rarelity < 1000) {
				return getEpicLoot();
			} else if ( rarelity < 3500) {
				return getRareLoot();
			} else {
				return getNormalLoot();
			}
		} else {
			return null;
		}
	}

	private static ItemStack getLegendaryLoot() {
		ArrayList<Legendary> list = new ArrayList<>();
		for (Legendary l : Legendary.values()) {
			list.add(l);
		}
		Collections.shuffle(list);
		return list.get(0).toItemStack();
	}

	private static ItemStack getEpicLoot() {
		ArrayList<Epic> list = new ArrayList<>();
		for (Epic l : Epic.values()) {
			list.add(l);
		}
		Collections.shuffle(list);
		return list.get(0).toItemStack();
	}

	private static ItemStack getRareLoot() {
		ArrayList<Rare> list = new ArrayList<>();
		for (Rare l : Rare.values()) {
			list.add(l);
		}
		Collections.shuffle(list);
		return list.get(0).toItemStack();
	}

	private static ItemStack getNormalLoot() {
		ArrayList<Normal> list = new ArrayList<>();
		for (Normal l : Normal.values()) {
			list.add(l);
		}
		Collections.shuffle(list);
		return list.get(0).toItemStack();
	}
}
