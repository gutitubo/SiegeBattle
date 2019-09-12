package Siege.Rune;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import Siege.SiegePlayer.SiegePlayer;
import Siege.SiegeTeam.SiegeTeam;

public class Linkage {

	private SiegePlayer owner;
	private Block block;
	private Material before;

	private static ArrayList<Linkage> activeLinkageList = new ArrayList<>();


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

	public ItemStack toItemStack() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
