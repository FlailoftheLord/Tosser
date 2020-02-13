package me.flail.tosser.core.tossables;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.flail.tosser.Tosser;
import me.flail.tosser.core.Tossable;

public class TntTossable extends Tossable implements Listener {

	public TntTossable(Tosser plugin) {
		super(plugin);

	}

	public void throwFrom(Player player) {
		Snowball projectile = player.launchProjectile(Snowball.class);
		Entity tnt = projectile.getWorld().spawnEntity(projectile.getLocation(), EntityType.PRIMED_TNT);
		tnt.setVelocity(projectile.getVelocity().multiply((double) plugin.settings().get("Tossables.Tnt.Strength", 1.0)));
		projectile.remove();

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		Player player = event.getPlayer();

		if (action.equals(Action.RIGHT_CLICK_AIR)) {
			if (player.getInventory().getItemInMainHand().getType().equals(Material.TNT)) {

				throwFrom(player);
				if (!player.getGameMode().equals(GameMode.CREATIVE)) {

					player.getInventory().removeItem(new ItemStack(Material.TNT, 1));
				}

			}

		}

	}

}
