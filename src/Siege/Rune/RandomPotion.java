package Siege.Rune;

import static Lib.Parameters.*;
import static Siege.Rune.PotionColor.*;
import static Siege.Rune.PotionName.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum RandomPotion {
	REGE(REGE_NAME, PotionEffectType.REGENERATION, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, REGE_COLOR),
	STRE(STRE_NAME, PotionEffectType.INCREASE_DAMAGE, STRE_MAXLEVEL, STRE_MAXDUR, STRE_COLOR),
	WEAK(WEAK_NAME, PotionEffectType.WEAKNESS, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, WEAK_COLOR),
	RESI(RESI_NAME, PotionEffectType.DAMAGE_RESISTANCE, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, RESI_COLOR),
	FIRE(FIRE_NAME, PotionEffectType.FIRE_RESISTANCE, 0, NORMALPOT_MAXDUR, FIRE_COLOR),
	POIS(POIS_NAME, PotionEffectType.POISON, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, POIS_COLOR),
	WITH(WITH_NAME, PotionEffectType.WITHER, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, WITH_COLOR),
	SPEE(SPEE_NAME, PotionEffectType.SPEED, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, SPEE_COLOR),
	SLOW(SLOW_NAME, PotionEffectType.SLOW, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, SLOW_COLOR),
	HAST(HAST_NAME, PotionEffectType.FAST_DIGGING, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, HAST_COLOR),
	FREQ(FREQ_NAME, PotionEffectType.SLOW_DIGGING, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, FREQ_COLOR),
	BLIN(BLIN_NAME, PotionEffectType.BLINDNESS, 0, NORMALPOT_MAXDUR, BLIN_COLOR),
	NIGH(NIGH_NAME, PotionEffectType.NIGHT_VISION, 0, NORMALPOT_MAXDUR, NIGH_COLOR),
	HEAL(HEAL_NAME, PotionEffectType.HEAL, NORMALPOT_MAXLEVEL, INSTANT_MAXDUR, HEAL_COLOR),
	HARM(HARM_NAME, PotionEffectType.HARM, NORMALPOT_MAXLEVEL, INSTANT_MAXDUR, HARM_COLOR),
	JUMP(JUMP_NAME, PotionEffectType.JUMP, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, JUMP_COLOR),
	NAUS(NAUS_NAME, PotionEffectType.CONFUSION, 0, NORMALPOT_MAXDUR, NAUS_COLOR),
	SWIM(SWIM_NAME, PotionEffectType.WATER_BREATHING, 0, NORMALPOT_MAXDUR, SWIM_COLOR),
	HUNG(HUNG_NAME, PotionEffectType.HUNGER, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, HUNG_COLOR),
	GLOW(GLOW_NAME, PotionEffectType.GLOWING, 0, NORMALPOT_MAXDUR, GLOW_COLOR),
	LEVI(LEVI_NAME, PotionEffectType.LEVITATION, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, LEVI_COLOR),
	LUCK(LUCK_NAME, PotionEffectType.LUCK, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, LUCK_COLOR),
	BADL(BADL_NAME, PotionEffectType.UNLUCK, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, BADL_COLOR),
	FEAT(FEAT_NAME, PotionEffectType.SLOW_FALLING, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, FEAT_COLOR),
	DOLP(DOLP_NAME, PotionEffectType.DOLPHINS_GRACE, NORMALPOT_MAXLEVEL, NORMALPOT_MAXDUR, DOLP_COLOR),
	OMEN(OMEN_NAME, PotionEffectType.BAD_OMEN, 0, NORMALPOT_MAXDUR, OMEN_COLOR),
	HERO(HERO_NAME, PotionEffectType.HERO_OF_THE_VILLAGE, 0, NORMALPOT_MAXDUR, HERO_COLOR),
	;

	private String itemName;
	private PotionEffectType type;
	private int maxLevel;
	private int maxDuration;
	private Color color;

	private RandomPotion(String itemName, PotionEffectType type, int maxLevel, int maxDuration, Color color) {
		this.itemName = itemName;
		this.type = type;
		this.maxLevel = maxLevel;
		this.maxDuration = maxDuration;
		this.color = color;
	}

	public ItemStack toPotionItem() {
		ItemStack potion = new ItemStack(Material.POTION);
		PotionMeta meta = (PotionMeta)potion.getItemMeta();

		int dur = new Random().nextInt(maxDuration) + 1;

		int lv = new Random().nextInt(maxLevel + 1) - 1;

		meta.setDisplayName(itemName); //TODO 名前の設定
		meta.addCustomEffect(new PotionEffect(type, dur, lv), false); //TODO ポーション生成処理をちゃんとする
		meta.setColor(color); //TODO 色を入れる未定

		potion.setItemMeta(meta);
		return potion;
	}

	public static ItemStack getRandom() {
		ArrayList<RandomPotion> ranp = new ArrayList<>();
		for (RandomPotion rp : RandomPotion.values()) {
			ranp.add(rp);
		}
		Collections.shuffle(ranp);
		return ranp.get(0).toPotionItem();
	}
}
