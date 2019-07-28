package Siege.SiegeEvent;

import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;

public class PlayerAttackWithHandEvent implements Listener{
	
	public final double DEFAULT_ATTACK_SPEED = 4.0;
	
	@EventHandler
	public void onAttacked(PlayerAnimationEvent e) {
		if( e.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) {
			e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
		}
	}
}
