package Siege.SiegeEvent;

import static Lib.ConstStrings.*;
import static Lib.ItemFactory.*;
import static Lib.Parameters.*;
import static org.bukkit.Material.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import Siege.SiegeShop.NormalShop;

public class ShopItemInteractEvent implements Listener {

	@EventHandler
	public void onClicked(InventoryClickEvent e) {
		HumanEntity human = e.getWhoClicked();

		Player p = human instanceof Player ? Bukkit.getPlayer(human.getName()) : null ;
		if (p == null) { return; }

		InventoryView inv = p.getOpenInventory();
		String iname = inv.getTitle();

		if (!iname.equalsIgnoreCase(SHOP_NORMAL_NAME)) return;
		e.setCancelled(true);

		ItemStack clicked = e.getCurrentItem();

		if (clicked.getType().equals(getRedStoneShopIcon().getType())) {
			p.openInventory(NormalShop.getRedInventory());
		} else if (clicked.getType().equals(getLapisShopIcon().getType())) {
			p.openInventory(NormalShop.getLapInventory());
		} else if (clicked.getType().equals(getEmeraldShopIcon().getType())) {
			p.openInventory(NormalShop.getEmeInventory());
		} else if (clicked.getType().equals(getDiamondShopIcon().getType())) {
			p.openInventory(NormalShop.getDiaInventory());
		}
		//ハサミの場合
		if (clicked.getType().equals(Material.SHEARS)) {
			ItemMeta meta = clicked.getItemMeta();
			String itemName = meta.getDisplayName();
			//エンチャハサミと名前が同じだったら
			// - NameListに含まれていたら買えるようにする。TODO
			if (itemName.equals(getEnchantedShears().getItemMeta().getDisplayName())) {
				//買えた場合
				if (purchaseItem(p, REDSHOP_SHEARS_COST, REDSTONE_BLOCK)) {
					//アイテムを渡す処理
				//買えなかった場合
				} else {
					//だめなときの処理
					youCantBuy(p);
				}
			}
		}
	}

	private static boolean purchaseItem(Player p, int cost, Material mat) {
		boolean flag = false;
		Inventory inv = p.getInventory();
		int have = 0;
		//マテリアルがなかったらリターン
		if (!inv.contains(mat)) return flag;

		//アイテムの個数をカウント
		for (ItemStack item : inv.getContents()) {
			if(item.getType().equals(mat)) {
				have += item.getAmount();
			}
		}
		//足りなかったら即リターン
		if (have < cost) return flag;
		//コストの分だけインベントリからアイテムを引く
		for (int i=0;i < inv.getSize(); i++) {
			ItemStack cursor = inv.getItem(i);
			if (cursor.getType().equals(mat)) {
				/* ここから持っている量に応じて引く */
				int amount = cursor.getAmount();
				//1ItemStackで事足りる場合
				if (amount >= cost) {
					if (amount == cost) {
						//amountとコストが同じ場合
						inv.clear(i);
						flag = true;
						break;
					} else {
						//amountのほうが多い場合
						cursor.setAmount(amount - cost);
						flag = true;
						break;
					}
				//足りない場合
				} else {
					//コストを引いてアイテムをクリアする
					cost -= amount;
					inv.clear(i);
				}
			}
		}
		return flag;
	}

	public static void giveItem(Player p, ItemStack item) {
		Inventory inv = p.getInventory();
		inv.addItem(item);
		p.sendMessage(item.getItemMeta().getDisplayName() + SHOP_MESSAGE_BOUGHT_ITEM);
	}

	public static void youCantBuy(Player p) {
		p.sendMessage(SHOP_MESSAGE_YOUCANTBUY);
		p.closeInventory();
	}
}
