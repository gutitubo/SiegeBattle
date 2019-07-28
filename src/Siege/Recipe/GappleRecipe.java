package Siege.Recipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

@SuppressWarnings("deprecation")
public class GappleRecipe {
	
	public static void addRecipe() {
		ItemStack gapple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
	
		ShapedRecipe gapple_r = new ShapedRecipe(gapple);
		 
		gapple_r.shape("GGG","GAG","GGG");
		 
		gapple_r.setIngredient('G', Material.GOLD_BLOCK);
		gapple_r.setIngredient('A', Material.APPLE);
		 
		Siege.SiegeBattleMain.siegeBattleMain.getServer().addRecipe(gapple_r);
	}
	
}
