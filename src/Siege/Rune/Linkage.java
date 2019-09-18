package Siege.Rune;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;

public class Linkage {

	private SiegePlayer owner;
	private Block block;
	private Material before;

	private static ArrayList<Linkage> activeLinkageList = new ArrayList<>();

	public static final String LINKAGE_SUFFIX = "'s Teleporter";

	public Linkage(SiegePlayer owner, Block block, Material before) {
		this.owner = owner;
		this.block = block;
		this.before = before;
	}

	public static void place(Linkage linkage) {
		activeLinkageList.add(linkage);
		linkage.getBlock().getWorld().playSound(linkage.getBlock().getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1F, 0.5F);
		linkage.getBlock().setType(Material.SPAWNER);
	}

	public void remove() {
		block.setType(before);
		block.getWorld().playSound(block.getLocation(), Sound.BLOCK_SHULKER_BOX_CLOSE, 1F, 0.5F);
		activeLinkageList.remove(this);
	}

	public SiegeTeam getTeam() {
		return owner.getTeam();
	}

	public SiegePlayer getOwner() {
		return owner;
	}

	public void setOwner(SiegePlayer owner) {
		this.owner = owner;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Material getBefore() {
		return before;
	}

	public void setBeforeBlock(Material before) {
		this.before = before;
	}

	public static ArrayList<Linkage> getList() {
		return activeLinkageList;
	}

	public static ArrayList<Linkage> getListAsTeam(SiegeTeam team) {
		ArrayList<Linkage> teamLinkage = new ArrayList<>();
		for (Linkage linkage : activeLinkageList) {
			if (linkage.getOwner().getTeam().equals(team)) teamLinkage.add(linkage);
		}
		return teamLinkage;
	}

	public ItemStack toItemStack(Location you) {
		// TODO 自動生成されたメソッド・スタブ
		Material mat = Material.RED_STAINED_GLASS;
		if (you.distance(block.getLocation()) < 200) mat = Material.CYAN_STAINED_GLASS;
		if (you.distance(block.getLocation()) < 300) mat = Material.GREEN_STAINED_GLASS;
		if (you.distance(block.getLocation()) < 400) mat = Material.LIME_STAINED_GLASS;
		if (you.distance(block.getLocation()) < 500) mat = Material.YELLOW_STAINED_GLASS;
		if (you.distance(block.getLocation()) < 600) mat = Material.ORANGE_STAINED_GLASS;
		if (you.distance(block.getLocation()) < 700) mat = Material.RED_STAINED_GLASS;

		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(owner.getPlayer().getDisplayName() + LINKAGE_SUFFIX);
		ArrayList<String> lore = new ArrayList<>();
		lore.add(owner.getTeam().getColor() + "Owner: " + owner.getPlayer().getDisplayName());
		lore.add(owner.getTeam().getColor() + "Distance: " + (int)(block.getLocation().distance(you)));
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static Linkage getLinkage(ItemStack item) {
		Linkage link = null;
		ItemMeta meta = item.getItemMeta();
		String name = meta.getDisplayName();
		for (Linkage l : activeLinkageList) {
			if (name.contains(l.owner.getPlayer().getDisplayName())) {
				link = l;
			}
		}
		return link;
	}

	public void teleport(Player p) {
		Location l = new Location(block.getWorld(), block.getX()+0.5, block.getY()+0.5, block.getZ()+0.5);
		Location you = p.getLocation();
		giveDebuff(p, l.distance(you));
		p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1F, 1F);
		p.teleport(l);
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1F, 1F);
	}

	public void giveDebuff(Player p, double distance) {
		distance = distance > 600 ? 600 : distance;
		PotionEffectType[] type = new PotionEffectType[] {
				PotionEffectType.SLOW,
				PotionEffectType.SLOW_DIGGING,
				PotionEffectType.WEAKNESS};

		for(PotionEffectType pot : type) {
			if (p.hasPotionEffect(pot)) {
				p.removePotionEffect(pot);
			}
			p.addPotionEffect(new PotionEffect(pot, (int) (20 * (distance/10)), 0, false, false), false);
		}
	}
}
