package Siege.SiegeEvent;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class FallDamageEvent implements Listener{

	@EventHandler
	public void onFallen (EntityDamageEvent e) {
		Entity ent = e.getEntity();
		if (ent instanceof Player) {
			DamageCause cause = e.getCause();
			
			if (!(cause.equals(DamageCause.FALL) || cause.equals(DamageCause.FLY_INTO_WALL))) {
				return;
			}
			
			//ダメージランダム生成
			Random rnd = new Random();
			double d = rnd.nextDouble() * 6;
			if (d >= e.getDamage()) {
				d = e.getDamage();
			}
			e.setDamage(d);
		}
	}
	
}
