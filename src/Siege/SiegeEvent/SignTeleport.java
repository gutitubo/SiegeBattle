package Siege.SiegeEvent;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SignTeleport implements Listener{
	@EventHandler
	public void onSignCreate(SignChangeEvent e) {

		String line1 = e.getLine(0);
		//String line2 = e.getLine(1);
		if(e.getPlayer().isOp()){

			if (line1.equals("sietp")) {
				e.setLine(0, ChatColor.RED + "[GenT]");
			}
		}
	}

	@EventHandler
	public void Teleport(PlayerMoveEvent b){
		Player p = b.getPlayer();
		Location loc = p.getLocation();


		loc.setY(loc.getY() -0.5F);
		Material blockOn = loc.getWorld().getBlockAt(loc).getType();
		switch(blockOn) {
		case END_STONE:

			if(p !=null){
				Location sign = p.getLocation();
				sign.setY(sign.getY() -1.7F);
				Block bl = sign.getWorld().getBlockAt(sign);
				if(bl.getType() == Material.OAK_SIGN){

					if (bl !=null){

						Sign sign2 = (Sign) bl.getState();
						if(sign2 == null){
							return;
						} else {

							double line1 = Double.parseDouble(sign2.getLine(1).split(" ")[0]);
							double line2 = Double.parseDouble(sign2.getLine(2).split(" ")[0]);
							double line3 = Double.parseDouble(sign2.getLine(3).split(" ")[0]);
							String line4 = sign2.getLine(0);
							if (line4.contains(ChatColor.RED + "[GenT]")) {
								Location world = p.getPlayer().getLocation();
								World world2 = world.getWorld();

								Location l = new Location(world2, line1 + 0.5D, line2 + 0.5D, line3 + 0.5D);
								l.setPitch(p.getLocation().getPitch());
								l.setYaw(p.getLocation().getYaw());

								p.teleport(l);
							}
						}
					}
				}
			}

			break;
		default:
			break;
		}
	}
}
