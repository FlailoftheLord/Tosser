package me.flail.tosser.core.tossables;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.flail.tosser.Tosser;
import me.flail.tosser.core.Tossable;

public class BlockTossable extends Tossable implements Listener {

	public BlockTossable(Tosser plugin) {
		super(plugin);

	}

	public void throwFrom(Player player, Material type) {
		Snowball projectile = player.launchProjectile(Snowball.class);
		BlockData rockData = plugin.server.createBlockData(type);
		FallingBlock rock = projectile.getWorld().spawnFallingBlock(projectile.getLocation(), rockData);
		rock.setDropItem(true);

		rock.setVelocity(projectile.getVelocity().multiply((double) plugin.settings().get("Tossables.Blocks.Strength", 1.0)));
		projectile.remove();

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Action action = event.getAction();
		Player player = event.getPlayer();

		if (action.equals(Action.RIGHT_CLICK_AIR)) {
			Material material = player.getInventory().getItemInMainHand().getType();

			if (material.equals(Material.TNT) && (boolean) plugin.settings().get("Tossables.Tnt.Enabled", true)) {
				return;
			}

			if ((material != null) && material.isBlock()) {
				if (plugin.blockWhitelist.contains(material)) {
					return;
				}

				throwFrom(player, material);
				if (!player.getGameMode().equals(GameMode.CREATIVE)) {

					player.getInventory().removeItem(new ItemStack(material, 1));
				}

			}

		}

	}

}
